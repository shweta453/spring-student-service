package com.example.springstudentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.springstudentservice"})
public class SpringStudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringStudentServiceApplication.class, args);
	}

}
