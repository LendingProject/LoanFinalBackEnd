package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AdminResponseDto {
	
	private int id; 
	private String firstName;
	
	private String lastName;
	
	private String email; 
	
	private String password;
}
