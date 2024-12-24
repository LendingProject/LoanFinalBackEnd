package com.aurionpro.loan.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RequiredDocumentsResponseDto {
	private int loanId;
	private String documentOneLink;
	
	private String documentTwoLink;
	
	private String documentThreeLink;
}
