package com.u3.courses;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoursesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoursesApplication.class, args);
		System.out.println("\u001B[32m" + "Course service on: 8081." + "\u001B[0m");
	}

}
