package com.example.diyrabbitmq.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhangzhuang
 * @since 2020-10-28
 */
@Component
public class PropertiesUtil {


	@Value("rabbitmq.uri")
	private String rabbitmqUri;


	public String getRabbitMqUri(){
		if (rabbitmqUri == null || "".equals(rabbitmqUri)) {
			throw new RuntimeException("rabbitmqUri值为空");
		}
		return rabbitmqUri;
	}

}
