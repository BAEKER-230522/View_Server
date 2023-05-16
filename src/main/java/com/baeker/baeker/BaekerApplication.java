package com.baeker.baeker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BaekerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaekerApplication.class, args);
	}

}
