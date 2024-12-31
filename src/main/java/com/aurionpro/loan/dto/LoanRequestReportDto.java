package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoanRequestReportDto {
	  private Double loanAmount;
	    private String schemeName;
	    private String userFirstName;
	    private String userLastName;
}
