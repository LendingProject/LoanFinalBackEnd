package com.aurionpro.loan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanSchemeResponseDto {
	private int schemeId;
	
	private String schemename;
	
	private double maxamount;

	private double minamount;

	private double interest;
	private boolean isdelete;
}
