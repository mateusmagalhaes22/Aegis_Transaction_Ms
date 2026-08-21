package com.mateus.aegisTransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AegisTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AegisTransactionApplication.class, args);
	}

}
