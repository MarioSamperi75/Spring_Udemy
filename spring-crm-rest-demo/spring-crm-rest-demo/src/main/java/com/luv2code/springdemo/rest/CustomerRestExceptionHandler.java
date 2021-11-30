package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomerRestExceptionHandler {
 
	// Add an exceptionhandler for CustomerNotFoundException
	
	@ExceptionHandler
	public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc){
		
		
		// Create CustomerErrorResponse
		CustomerErrorResponse error = new CustomerErrorResponse(HttpStatus.NOT_FOUND.value(),
																exc.getMessage(), 
																System.currentTimeMillis()); 
		
		//return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// Add an exceptionhandler for all the exceptions
}
