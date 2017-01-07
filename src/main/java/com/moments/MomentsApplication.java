package com.moments;

import com.moments.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MomentsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MomentsApplication.class, args);
	}
}
