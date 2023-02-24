package com.symbolguys.stronghold.manor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan
public class ManorApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManorApplication.class, args);
	}

}