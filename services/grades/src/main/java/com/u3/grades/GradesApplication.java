package com.u3.grades;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GradesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GradesApplication.class, args);
		System.out.println("\u001B[32m" + "Grades service on: 8083." + "\u001B[0m");
	}

}
