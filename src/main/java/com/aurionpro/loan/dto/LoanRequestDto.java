package com.aurionpro.loan.dto;



import com.aurionpro.loan.entity.LoanScheme;
import com.aurionpro.loan.entity.Loanstatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanRequestDto {
	  @NotNull @NotBlank
	    private int user_id;
	    @NotNull @NotBlank
	    private int loanscheme_id; // Loan scheme ID
	    @NotNull @NotBlank
	    private double loanamount; // Amount of loan requested
	    @NotNull @NotBlank
	    private int time; // Loan duration (in months or years)
	    private double totalRepayAmount; // This can be calculated, not necessarily sent by user
	    private Loanstatus loanstatus; // Status of the loan
	    private boolean closed; // Whether the loan is closed

	    // Nested object: LoanScheme (optional for mapping)
	    private LoanScheme loanscheme;

}
