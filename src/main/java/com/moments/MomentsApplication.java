package com.moments;

import com.moments.utils.AsyncJobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class MomentsApplication implements CommandLineRunner {

	@Autowired
	private AsyncJobs jobs;

	public static void main(String[] args) {
		SpringApplication.run(MomentsApplication.class, args);

	}

	@Override
	public void run(String... strings) throws Exception {
		jobs.addUserIdsToRedis();
	}
}
