package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class JWTResponseDto {

	
	private String accessToken;
	private String tokenType="Bearer";
}
