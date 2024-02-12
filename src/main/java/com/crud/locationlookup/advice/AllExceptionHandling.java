package com.crud.locationlookup.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.crud.locationlookup.dto.ResponseDTO;
import com.crud.locationlookup.exception.CityAlreadyAssignedException;
import com.crud.locationlookup.exception.CityNotFoundException;
import com.crud.locationlookup.exception.CountryNotFoundException;
import com.crud.locationlookup.exception.StateAlreadyAssignedException;
import com.crud.locationlookup.exception.StateNotFoundException;

@RestControllerAdvice
public class AllExceptionHandling {

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO countryNotFoundException(CountryNotFoundException e) {
		return new ResponseDTO(404,"error",e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO stateNotFoundException(StateNotFoundException e) {
		return new ResponseDTO(404,"error",e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO stateAlreadyAssignedException(StateAlreadyAssignedException e) {
		return new ResponseDTO(404,"error",e.getMessage());
	}
	
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO cityNotFoundException(CityNotFoundException e) {
		return new ResponseDTO(404,"error",e.getMessage());
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler
	public ResponseDTO cityAlreadyAssignedException(CityAlreadyAssignedException e) {
		return new ResponseDTO(404,"error",e.getMessage());
	}
}
