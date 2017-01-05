package com.moments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@SpringBootApplication
@Controller
public class MomentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MomentsApplication.class, args);
	}

	@RequestMapping(value = "/homepage", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
}
