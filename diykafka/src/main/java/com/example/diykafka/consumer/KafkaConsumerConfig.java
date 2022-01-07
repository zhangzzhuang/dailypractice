package com.example.diykafka.consumer;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author basi
 * @author zhangzhuang
 * @since 2021-11-19
 */
@Configuration
@EnableKafka
public class KafkaConsumerConfig {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Value("${kafka.consumer.servers}")
	private String servers;
	@Value("${kafka.consumer.enable.auto.commit}")
	private boolean enableAutoCommit;
	@Value("${kafka.consumer.session.timeout}")
	private String sessionTimeout;
	@Value("${kafka.consumer.auto.commit.interval}")
	private String autoCommitInterval;
	@Value("${kafka.consumer.group.id}")
	private String groupId;
	@Value("${kafka.consumer.auto.offset.reset}")
	private String autoOffsetReset;
	@Value("${kafka.consumer.concurrency}")
	private int concurrency;

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String,String>> kafkaListenerContainerFactory(){
		ConcurrentKafkaListenerContainerFactory<String,String> factory = new ConcurrentKafkaListenerContainerFactory();
		//设置消费者工厂
		factory.setConsumerFactory(consumerFactory());
		//消费者组中线程数量
		factory.setConcurrency(concurrency);
		//拉取超时时间
		factory.getContainerProperties().setPollTimeout(1500);
		//当使用批量监听器时需要设置为true
		factory.setBatchListener(true);
		// 将单条消息异常处理器添加到参数中
		factory.setErrorHandler((ConsumerAwareErrorHandler) (e, consumerRecord, consumer) -> {
			logger.info("单条消息异常,e:{},consumerRecord:{},consumer:{}",e,consumerRecord,consumer);
		});
		// 将批量消息异常处理器添加到参数中
		factory.setBatchErrorHandler((e, consumerRecords) -> {
			logger.info("批量消息异常,e:{},consumerRecords:{}",e,consumerRecords);
		});
		// 将过滤器添添加到参数中
		factory.setRecordFilterStrategy( consumerRecord -> {
			// 设置过滤器，只接收消息内容中包含 "test" 的消息
			String value = consumerRecord.value();
			if (value != null && value.contains("test")) {
				System.err.println(consumerRecord.value());
				// 返回 false 则接收消息
				return false;
			}
			// 返回 true 则抛弃消息
			return true;
		});
		return factory;
	}

	public ConsumerFactory<String,String> consumerFactory() {
		return new DefaultKafkaConsumerFactory(consumerConfigs());
	}

	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		//kafka地址
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
		//是否自动提交offset偏移量(默认true)
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, enableAutoCommit);
		//自动提交的频率(ms)
		props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, autoCommitInterval);
		//Session超时设置
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, sessionTimeout);

		props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
		// offset偏移量规则设置：
		// (1)、earliest：当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，从头开始消费
		// (2)、latest：当各分区下有已提交的offset时，从提交的offset开始消费；无提交的offset时，消费新产生的该分区下的数据
		// (3)、none：topic各分区都存在已提交的offset时，从offset后开始消费；只要有一个分区不存在已提交的offset，则抛出异常
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);
		//消费指定每次最大消费消息数量
		props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		return props;
	}

}
