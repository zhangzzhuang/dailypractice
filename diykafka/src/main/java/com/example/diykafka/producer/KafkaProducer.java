package com.example.diykafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzhuang
 * @since 2021-12-02
 */
@Service
public class KafkaProducer {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate kafkaTemplate;

	public void sendMsgSync(String topic, String msg) throws InterruptedException, ExecutionException, TimeoutException {
		logger.info("topic:{},send:{} ", topic, msg);
		kafkaTemplate.send(topic, msg).get(10, TimeUnit.SECONDS);
	}


	public void sendMagAsync(String topic, String msg,Integer partition) {
		kafkaTemplate.send(topic,partition,"2", msg).addCallback(new ListenableFutureCallback() {
			@Override
			public void onFailure(Throwable throwable) {
				logger.info("topic:{},send:{},error:{} ", topic, msg, throwable);
			}

			@Override
			public void onSuccess(Object o) {
				logger.info("success ! ! ! topic:{},send:{},", topic, msg);
			}
		});
	}
}
