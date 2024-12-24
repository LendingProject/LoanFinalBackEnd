package com.aurionpro.loan.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanRequestDto {
	@NotBlank
	@NotNull
	private int userId;
	@NotBlank
	@NotNull
	private int schemeId;
	@NotBlank
	@NotNull
	private double amount;
	@NotBlank
	@NotNull
	private int time;

}
