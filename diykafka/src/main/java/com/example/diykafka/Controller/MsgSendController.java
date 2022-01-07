package com.example.diykafka.Controller;

import com.example.diykafka.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangzhuang
 * @since 2021-12-02
 */
@RestController
@RequestMapping(value = "msg")
public class MsgSendController {

	@Autowired
	KafkaProducer kafkaProducer;

	@RequestMapping(value = "send")
	public void send(@RequestParam("topic") String topic , @RequestParam("msg") String msg) throws InterruptedException, ExecutionException, TimeoutException {
		kafkaProducer.sendMsgSync(topic,msg);
	}

	@RequestMapping(value = "sendA")
	public void sendA(@RequestParam("topic") String topic , @RequestParam("msg") String msg,@RequestParam("partition") Integer partition) {
		kafkaProducer.sendMagAsync(topic,msg,partition);
	}
}
