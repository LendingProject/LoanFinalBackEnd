package com.aurionpro.loan.dto;

import java.sql.Date;

import com.aurionpro.loan.entity.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RegistrationDto {
	@NotNull
	@NotBlank
	@Email
	private String username;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" ,message = "Enter valid password")
	private String password;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$" , message="Enter valid First name ")
	private String firstName;
	@NotNull
	@NotBlank
	@Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$" , message="Enter valid last name")
	private String lastName;
	@Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$" , message = "Enter Valid pancardNumber")
	@NotNull
	@NotBlank
	private String pancardNumber;
	@NotNull
	@NotBlank
	private Date dob;
	@Pattern(regexp = "^[7-9]{1}[0-9]{9}$",message = "Enter Valid Contact Number")
	@NotNull
	@NotBlank
	private long contactNumber;
	private Gender gender;
}
