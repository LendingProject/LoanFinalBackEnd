package com.aurionpro.loan.dto;

import com.aurionpro.loan.entity.Loanstatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanResponseDto {
	 private int loanid; // ID of the loan
	    private int user_id; // User ID
	    private int loanscheme_id; // Loan scheme ID
		private int loanOfficeId;
	    private double totalRepayAmount; // The total amount to be repaid
	    private double loanamount; // The loan amount
	    private int time; // The loan duration
	    private Loanstatus loanstatus; // Loan status (approved, pending, etc.)
	    private boolean closed; // Whether the loan is closed or still open
	    
	    // Calculated fields
	    private double simpleInterest; // The interest calculated for the loan
	    private double monthlyRepayment; // Monthly repayment amount for the loan
	
	
	

}
