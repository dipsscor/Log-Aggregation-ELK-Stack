package com.logaggregator.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.logaggregator.*"})
public class LogAggregationElkStackApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogAggregationElkStackApplication.class, args);
	}

}
