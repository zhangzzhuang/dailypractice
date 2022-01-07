package com.example.sortagorithm;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SortagorithmApplication {

	public static void main(String[] args) {

		new SpringApplicationBuilder().sources(SortagorithmApplication.class).listeners(new BasiApplicationListener()).run(args);

	}

}
