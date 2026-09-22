package com.klu.usage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsageService41Application {

	public static void main(String[] args) {
		SpringApplication.run(UsageService41Application.class, args);
	}

}
