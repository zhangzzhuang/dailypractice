package com.example.diykafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Optional;

/**
 * @author zhangzhuang
 * @since 2021-12-02
 */
@Component
public class KafkaConsumer {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@KafkaListener(topics = {"first_topic", "second_topic"},topicPartitions = {@TopicPartition(topic = "first_topic",partitions = {"2"})})
	public void kafkaListener(List<ConsumerRecord> records) {
		try {
			long start = System.currentTimeMillis();

			for (ConsumerRecord record : records) {
				Optional<?> kafkaMessage = Optional.ofNullable(record.value());
				if (kafkaMessage.isPresent()) {
					String message = (String) kafkaMessage.get();
					logger.info("consumer on message:{}", record.toString());
				}
			}

			logger.info("批量提交到线程池耗时={}ms 本次取出数据={}", System.currentTimeMillis() - start, records.size());

		} catch (Exception e) {
			logger.error("kafka失败，当前失败的批次。data:{}", records);
		}
	}


}
