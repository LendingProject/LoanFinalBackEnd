package com.aurionpro.loan.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EmiRequestDto {
	private int loanId;
	private double amount;
	private Date date;
}
