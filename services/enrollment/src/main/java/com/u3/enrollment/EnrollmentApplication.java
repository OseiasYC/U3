package com.u3.enrollment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnrollmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentApplication.class, args);
		System.out.println("\u001B[32m" + "Enrollment service on: 8082." + "\u001B[0m");
	}

}
