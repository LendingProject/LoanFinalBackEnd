package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ApproveLoanResponseDto {

	
	private int loanId;
    private String status; // APPROVED or REJECTED
    private String message;
}
