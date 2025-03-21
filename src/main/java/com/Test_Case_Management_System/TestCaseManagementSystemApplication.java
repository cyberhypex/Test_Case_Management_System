package com.Test_Case_Management_System;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class TestCaseManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestCaseManagementSystemApplication.class, args);
	}

}
