package com.aurionpro.loan.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class EnquiryRequestDto {
	@NotNull
	@NotBlank
     private int userId;
	@NotNull
	@NotBlank
	private String question;
	
}
