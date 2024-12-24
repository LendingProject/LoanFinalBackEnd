package com.aurionpro.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.JWTResponseDto;
import com.aurionpro.loan.dto.LoginRequestDto;
import com.aurionpro.loan.dto.RegistrationDto;
import com.aurionpro.loan.dto.UserResponseDto;
import com.aurionpro.loan.service.AuthService;

@RestController
@RequestMapping("/loanapp")
@CrossOrigin(origins = "http://localhost:4200")
public class LoginAndResgisterController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register")
	private ResponseEntity<UserResponseDto> registerUser(@RequestBody RegistrationDto registrationDto){
		return  ResponseEntity.ok(authService.Register(registrationDto));
	}
	@PostMapping("/login")
	private ResponseEntity<JWTResponseDto> LoginUser(@RequestBody LoginRequestDto loginDto){
		String token = authService.login(loginDto);
		JWTResponseDto jwtResponseDto = new JWTResponseDto();
		jwtResponseDto.setAccessToken(token);
		return ResponseEntity.ok(jwtResponseDto);
		
	}
}
