package com.aurionpro.loan.dto;

import com.aurionpro.loan.entity.Gender;

public class UserResponseDto {
	private int userId;
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
