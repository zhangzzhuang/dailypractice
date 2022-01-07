package com.example.diymysqlms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {


	public static String abc = "abc";
	@RequestMapping(value = "healthCheck")
	public String checkThread() {		
		return "0";
	}
}
