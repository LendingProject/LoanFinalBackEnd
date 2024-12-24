package com.aurionpro.loan.dto;

import java.sql.Date;

import com.aurionpro.loan.entity.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserResponseDto {
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String pancardNumber;
	private Date dob;
	private long contactNumber;
	private Gender gender;
	private int LoginId;
	private boolean deleted;
}
