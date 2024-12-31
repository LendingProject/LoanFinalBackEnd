package com.aurionpro.loan.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class LoanOfficerReportDto {
	  private String officerFirstName;
	    private String officerLastName;
	    private Long approvedCount;
	    private Long rejectedCount;
	    private Long pendingCount;

	    private List<LoanRequestReportDto> approvedLoans;
	    private List<LoanRequestReportDto> rejectedLoans;
	    private List<LoanRequestReportDto> pendingLoans;
}
