package com.example.diyquartz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.diyquartz.dao")
public class DiyquartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiyquartzApplication.class, args);
	}

}
