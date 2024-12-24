package com.aurionpro.loan.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserException extends RuntimeException{
	private String message;
	public String getMessage() {
		return  message;
	}
}
