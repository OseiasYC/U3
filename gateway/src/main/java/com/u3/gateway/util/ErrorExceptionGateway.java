package com.u3.gateway.util;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@RestControllerAdvice
public class ErrorExceptionGateway implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	@ExceptionHandler(ExpiredJwtException.class)
	public Map<String, Object> ExceptionHandlingWebHandler() {

		String message = "Expired Token!";
		Map<String, Object> errors = new HashMap<>();
		;
		errors.put("error", message);
		errors.put("code", HttpStatus.UNAUTHORIZED.value());

		return errors;
	}
}