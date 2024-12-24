package com.aurionpro.loan.dto;




import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RequiredDocumentsRequestDto {
	@NotNull
	@NotBlank
	private int loanId;
	@NotNull
	@NotBlank
	private String documentOneLink;
	@NotNull
	@NotBlank
	private String documentTwoLink;
	@NotNull
	@NotBlank
	private String documentThreeLink;
}
