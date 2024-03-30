package com.u3.requests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RequestsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestsApplication.class, args);
		System.out.println("\u001B[32m" + "Requests service on: 8085." + "\u001B[0m");
	}

}
