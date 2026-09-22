package com.klu.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class AuthService41Application {

	public static void main(String[] args) {
		SpringApplication.run(AuthService41Application.class, args);
	}

}
