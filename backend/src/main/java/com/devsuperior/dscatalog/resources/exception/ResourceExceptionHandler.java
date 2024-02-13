package com.devsuperior.dscatalog.resources.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.devsuperior.dscatalog.services.exceptions.DatabaseException;
import com.devsuperior.dscatalog.services.exceptions.ResourceNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status=HttpStatus.NOT_FOUND;
		StandardError error = new StandardError();
	    error.setTimestamp(Instant.now());
	    error.setStatus(status.value());
	    error.setError("Resource not Found");
	    error.setMessage(e.getMessage());
	    error.setPath(request.getRequestURI());
	    return ResponseEntity.status(status).body(error);
	}
	
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request) {
	    HttpStatus status=HttpStatus.BAD_REQUEST;
		StandardError error = new StandardError();
	    error.setTimestamp(Instant.now());
	    error.setStatus(status.value());
	    error.setError("Database exception");
	    error.setMessage(e.getMessage());
	    error.setPath(request.getRequestURI());
	    return ResponseEntity.status(status).body(error);
	}

}
