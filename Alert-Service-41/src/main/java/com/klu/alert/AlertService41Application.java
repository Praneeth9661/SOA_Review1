package com.klu.alert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AlertService41Application {

	public static void main(String[] args) {
		SpringApplication.run(AlertService41Application.class, args);
	}

}
