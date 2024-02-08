package com.practise.mapping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.practise.mapping.dto.ResponseDto;
import com.practise.mapping.exception.AccountNotFoundException;
import com.practise.mapping.exception.PersonNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlerController {

	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {PersonNotFoundException.class})
	public ResponseDto PersonNotFoundExceptionHandler (PersonNotFoundException ex) {
		return new ResponseDto(404,"Person not Found", ex.toString());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {AccountNotFoundException.class})
	public ResponseDto AccountNotFoundExceptionHandler (AccountNotFoundException ex) {
		return new ResponseDto(404,"Account not Found", ex.toString());
	}
	
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value= {EntityNotFoundException.class})
	public ResponseDto EntityNotFoundExceptionHandler (EntityNotFoundException ex) {
		return new ResponseDto(404,"The Entity was not Present", ex.toString()); 
	}
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value= {Exception.class})
	public ResponseDto GeneralExceptionHandler (Exception ex) {
		return new ResponseDto(500,"Exception Found", ex.getStackTrace());
	}
	
}
