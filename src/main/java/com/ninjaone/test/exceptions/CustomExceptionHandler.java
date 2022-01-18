package com.ninjaone.test.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomExceptionHandler extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	private String message;

}
