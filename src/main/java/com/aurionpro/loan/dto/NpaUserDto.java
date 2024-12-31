package com.aurionpro.loan.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class NpaUserDto {
	private int userId;
    private String firstName;
    private String lastName;
    private List<MissedPaymentDto> missedPayments;
}
