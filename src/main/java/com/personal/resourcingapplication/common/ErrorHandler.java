package com.personal.resourcingapplication.common;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandler {
	
	@ExceptionHandler(AlreadyExistsException.class)
	public ResponseEntity handelException(AlreadyExistsException exp) {
		ResponseEntity entity = new ResponseEntity(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	
	@ExceptionHandler(NotExistException.class)
	public ResponseEntity handelNotExistException(NotExistException exp) {
		ResponseEntity entity = new ResponseEntity(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		return entity;
	}
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity handelInvalidRequest(RuntimeException exp) {
		ResponseEntity entity = new ResponseEntity(exp.getMessage(),HttpStatus.BAD_REQUEST);
		return entity;
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	  ResponseEntity handleConstraintViolationException(MethodArgumentNotValidException e) {
	    return new ResponseEntity("Request Body not valid " + e.getMessage().substring(e.getMessage().lastIndexOf("message")+7), HttpStatus.BAD_REQUEST);
	  }
}
