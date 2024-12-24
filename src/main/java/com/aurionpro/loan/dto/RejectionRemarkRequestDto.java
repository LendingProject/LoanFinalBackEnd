	package com.aurionpro.loan.dto;



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RejectionRemarkRequestDto {
	@NotNull
	@NotBlank
	private String message;
	@NotNull
	@NotBlank
	private int loanId;
	@NotNull
	@NotBlank
	private int officerId;
}
