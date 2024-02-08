package com.practise.mapping.exception;

public class PersonNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PersonNotFoundException(String message) {
		super(message);
	}
	
	public PersonNotFoundException() {
		
	}

	
}
