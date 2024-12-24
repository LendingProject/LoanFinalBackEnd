package com.aurionpro.loan.dto;

import com.aurionpro.loan.entity.Loanstatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanResponseDto {
	private int loanid;
	private int userId;
	private int schemeId;
	private double amount;
	private double loanAount;
	private int time;
	private Loanstatus loanstatus;
	private boolean closed;

}
