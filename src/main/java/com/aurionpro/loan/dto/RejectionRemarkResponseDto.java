package com.aurionpro.loan.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RejectionRemarkResponseDto {

	private int rejectionId;
	
	private String message;

	private int loanId;

	private int officerId;
}
