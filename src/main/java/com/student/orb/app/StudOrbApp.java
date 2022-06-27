package com.student.orb.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@ComponentScan(basePackages = "com.student.orb.app")
@SpringBootApplication
public class StudOrbApp {
	
	private static Logger logger = LoggerFactory.getLogger(StudOrbApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(StudOrbApp.class, args);
		logger.info("Student ORB App Started !!");
	}

}
