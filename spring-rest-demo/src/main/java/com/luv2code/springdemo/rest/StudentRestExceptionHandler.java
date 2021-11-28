package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

	// add exception handling code here
	// it will be available for all the rest controllers!
	
	// Add an exception handler using @ExceptionHandler
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
		// create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimestamp(System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);	 
	}
	
	// Add an exception handler using @ExceptionHandler
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
		// create a StudentErrorResponse
		StudentErrorResponse error = new StudentErrorResponse();
		
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		// we can set the message we like, if we didn't like getMessage
		error.setMessage(exc.getMessage());
		
		error.setTimestamp(System.currentTimeMillis());
		
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);	 
	}
	
}
