package com.aurionpro.loan.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class AdminRequestDto {
	

	@Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$" , message="Enter valid First name ")
	private String firstName;
	@Pattern(regexp = "^[A-Za-z]+(?:[ -'][A-Za-z]+)*$" , message="Enter valid last name")
	private String lastName;
	@Email
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$" ,message = "Enter valid password")
	private String password;
		
	

}
