package com.crud.locationlookup.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO{

	private int statusCode;
	
	private String message;
	
	private Object data;

}
