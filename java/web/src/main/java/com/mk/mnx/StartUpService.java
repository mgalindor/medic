package com.mk.mnx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class StartUpService {

	public static void main(String[] args) {
		SpringApplication.run(StartUpService.class, args);
		// Esta bien chido este pedo
	}
}
