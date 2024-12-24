package com.aurionpro.loan.service;

import java.util.List;

import com.aurionpro.loan.dto.ApproveLoanRequestDto;
import com.aurionpro.loan.dto.ApproveLoanResponseDto;
import com.aurionpro.loan.dto.EnquiryResponseDto;
import com.aurionpro.loan.dto.LoanOfficerProfileUpdateRequestDto;
import com.aurionpro.loan.dto.LoanOfficerProfileUpdateResponseDto;
import com.aurionpro.loan.dto.LoanOfficerRequestDto;
import com.aurionpro.loan.dto.LoanOfficerResponseDto;
import com.aurionpro.loan.dto.LoanRequestDto;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.RegistrationDto;
import com.aurionpro.loan.dto.RejectionRemarkRequestDto;
import com.aurionpro.loan.dto.RejectionRemarkResponseDto;
import com.aurionpro.loan.dto.ReplyEnquiryRequestDto;
import com.aurionpro.loan.dto.ReplyEnquiryResponseDto;

public interface LoanOfficerService {
	
	LoanOfficerResponseDto  addLoanOfficer(RegistrationDto registrationDto);
	LoanOfficerResponseDto  deleteLoanOfficer(int officerId);
	
	
	 PageResponse<LoanResponseDto> viewLoanRequests(int officerId, int pageNumber, int pageSize);
	
	// View loan requests assigned to the loan officer
    List<LoanResponseDto> viewLoanRequests(int officerId);

    // Approve a loan
    ApproveLoanResponseDto approveLoan(ApproveLoanRequestDto approveLoanRequestDto);

    // Reject a loan
    RejectionRemarkResponseDto rejectLoan(RejectionRemarkRequestDto rejectionRemarkRequestDto);

    // Reply to customer queries
    ReplyEnquiryResponseDto replyToEnquiry(ReplyEnquiryRequestDto replyEnquiryRequestDto);

	
//loan officer profile update
//    LoanOfficerProfileUpdateResponseDto updateLoanOfficerProfile(int officerId,LoanOfficerProfileUpdateRequestDto loanOfficerProfileRequestDto);

}


