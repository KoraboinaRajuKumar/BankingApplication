package com.infy.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptions {

	// handle spesific exceptions => AccountException
	@ExceptionHandler(AccountException.class)
	public ResponseEntity<ErrorDetails> handleAccountException(AccountException accountException, WebRequest request) {

		ErrorDetails details = new ErrorDetails(

				LocalDateTime.now(), 
				accountException.getMessage(), 
				request.getDescription(false), 
				"Account_NOT_FOUND");
		return new ResponseEntity<ErrorDetails>(details, HttpStatus.NOT_FOUND);

	}
}
