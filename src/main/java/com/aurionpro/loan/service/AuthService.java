package com.aurionpro.loan.service;


import com.aurionpro.loan.dto.LoginRequestDto;
import com.aurionpro.loan.dto.RegistrationDto;
import com.aurionpro.loan.dto.UserResponseDto;



public interface AuthService {

	UserResponseDto   Register(RegistrationDto registrationDto);
	String login(LoginRequestDto loginDto); 
}
