package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Data
public class LoanRequestDetailsDTO {
	  private String firstName;
	    private String lastName;
	    private double loanAmount;
	    private String loanSchemeName;
	    private int time;
}
