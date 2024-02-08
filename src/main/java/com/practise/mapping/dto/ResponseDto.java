package com.practise.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	
	private int status;
	private String message;
	private Object data;
	
	public ResponseDto(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
}
