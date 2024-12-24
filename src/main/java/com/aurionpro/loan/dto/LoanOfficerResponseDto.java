package com.aurionpro.loan.dto;

import java.sql.Date;

import com.aurionpro.loan.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanOfficerResponseDto {
	private int officerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String pancardNumber;
	private String dob;
	private long contactNumber;
	private Gender gender;
	private boolean deleted;

}
