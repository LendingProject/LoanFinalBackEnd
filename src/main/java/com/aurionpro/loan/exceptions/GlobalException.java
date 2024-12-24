package com.aurionpro.loan.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.loan.errors.UserError;

@ControllerAdvice
public class GlobalException {
	 @ExceptionHandler
	 public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception)
	 {
	  Map<String,String> errors = new HashMap<>();
	  
	  exception.getBindingResult().getFieldErrors().forEach(
	    (error)->{
	     errors.put(error.getField(), error.getDefaultMessage());
	    });
	  
	  return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	  
	 }
	 
	 @ExceptionHandler
	public ResponseEntity<UserError> handleStudentException(UserException exception){
		  
		UserError error = new UserError();
		  error.setMessage(exception.getMessage());
		  error.setStatus(HttpStatus.NOT_FOUND.value());
		  error.setTimestamp(System.currentTimeMillis());
		  
		  ResponseEntity<UserError> response = new ResponseEntity<UserError>(error, HttpStatus.NOT_FOUND);
		    
		  return response;
		 }
	
	
	
}
