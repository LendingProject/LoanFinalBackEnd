package com.aurionpro.loan.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MissedPaymentDto {
	private Long id;
    private double emiAmount;
    private Date dueDate;
    private boolean paid;
    private boolean deleted;

    private int userId;
    private int loanId;
}
