package com.example.diyredpacket.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {
	
	@RequestMapping(value = "threadCheck")
	public String checkThread() {		
		return "0";
	}
}
