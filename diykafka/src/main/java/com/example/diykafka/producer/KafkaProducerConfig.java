package com.example.diykafka.producer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangzhuang
 * @since 2021-12-02
 */
@Configuration
@EnableKafka
public class KafkaProducerConfig {

	private Logger logger = LoggerFactory.getLogger(getClass());


	@Value("${kafka.producer.servers}")
	private String servers;
	@Value("${kafka.producer.retries}")
	private int retries;
	@Value("${kafka.producer.batch.size}")
	private int batchSize;
	@Value("${kafka.producer.linger}")
	private int linger;
	@Value("${kafka.producer.buffer.memory}")
	private int bufferMemory;
	@Value("${kafka.producer.max.block.ms}")
	private int blockMs;

	@Bean
	public KafkaTemplate<String, String> kafkaTemplate(){

		KafkaTemplate kafkaTemplate = new KafkaTemplate(producerFactory(),false);
		logger.info("kafkaTemplate init autoFlush false");
		return kafkaTemplate;
	}

	public ProducerFactory<String,String> producerFactory() {

		return new DefaultKafkaProducerFactory(producerConfigs());
	}

	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = new HashMap<>();
		//指定kafka集群地址
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,servers);
		// 重试次数，0为不启用重试机制
		props.put(ProducerConfig.RETRIES_CONFIG,retries);
		// 控制批处理大小，单位为字节
		props.put(ProducerConfig.BATCH_SIZE_CONFIG,batchSize);
		// 批量发送，延迟为1毫秒，启用该功能能有效减少生产者发送消息次数，从而提高并发量
		props.put(ProducerConfig.LINGER_MS_CONFIG,linger);
		// 生产者可以使用的总内存字节来缓冲等待发送到服务器的记录
		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG,bufferMemory);
		// 生产者空间不足时，send()被阻塞的时间，默认60s
		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, blockMs);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class);

		return props;
	}

}
