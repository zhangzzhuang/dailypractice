package com.example.diyredpacket;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.diyredpacket.dao"})
public class DiyredpacketApplication {

	public static void main(String[] args) {

		SpringApplication.run(DiyredpacketApplication.class, args);

	}

}
