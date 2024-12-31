package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AdminViewLoanRequestDto {

	 private double loanamount;
	    private int time;
	    private String schemeName;
	    private String userFirstName;
	    private String userLastName;
	    private String loanOfficerFirstName;
	    private String loanOfficerLastName;
}
