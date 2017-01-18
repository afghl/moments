package com.moments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MomentsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomentsApplication.class, args);
	}
}
