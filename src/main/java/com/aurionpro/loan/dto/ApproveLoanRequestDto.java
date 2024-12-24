package com.aurionpro.loan.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ApproveLoanRequestDto {

	@NotNull
    private int loanId;
    @NotNull
    private int officerId; //id of the officer approving the loan
}
