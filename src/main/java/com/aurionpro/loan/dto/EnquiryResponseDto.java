package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EnquiryResponseDto {

	
	private int userId;
	
	private int enquiryId;

	private String question;

	private String response;
	
}
