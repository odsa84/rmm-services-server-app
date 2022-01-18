package com.ninjaone.test.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(RuntimeException.class)
	  public ResponseEntity<String> handleInternal(final RuntimeException ex) {
	    return ResponseEntity
	    .status(HttpStatus.INTERNAL_SERVER_ERROR)
	    .body(ex.getMessage());
	  }
	  
	  @ExceptionHandler(CustomExceptionHandler.class)
      public ResponseEntity<?> identityClientException(CustomExceptionHandler e) {
        return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(e.getMessage());
	}	

}
