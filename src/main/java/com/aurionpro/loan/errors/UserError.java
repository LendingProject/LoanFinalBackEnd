package com.aurionpro.loan.errors;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserError {
	private String message;
	 private int status;
	 private long timestamp;
}
