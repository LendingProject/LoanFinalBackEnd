package com.aurionpro.loan.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.aurionpro.loan.dto.EmiRequestDto;
import com.aurionpro.loan.dto.EmiResponseDto;
import com.aurionpro.loan.dto.EnquiryRequestDto;
import com.aurionpro.loan.dto.EnquiryResponseDto;
import com.aurionpro.loan.dto.LoanRequestDto;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.LoanSchemeResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.PageResponseDto;
import com.aurionpro.loan.dto.RequiredDocumentsRequestDto;
import com.aurionpro.loan.dto.RequiredDocumentsResponseDto;
import com.aurionpro.loan.dto.UserAdminViewResponse;
import com.aurionpro.loan.dto.UserRequestDto;
import com.aurionpro.loan.dto.UserResponseDto;

public interface UserService {

UserResponseDto addUser(UserRequestDto userRequestDto);
	
	PageResponseDto<LoanSchemeResponseDto> getAllLoanScheme(int pageSize ,int pageNumber);
	
	LoanResponseDto applyLoan(LoanRequestDto loanRequestDto);
	
	
	//for viewing applied loans : PageResponse<LoanScheme> getAllLoanScheme(int pageSize ,int pageNumber);
	
	
	EmiResponseDto emiPayment(EmiRequestDto emiRequestDto);
	
	PageResponseDto<EmiResponseDto> getAllEmis(int pageSize ,int pageNumber);//Previous Emi Payment records via id
	
	EnquiryResponseDto submitQueries(EnquiryRequestDto enquiryRequestDto);
	
	PageResponseDto<EnquiryResponseDto> getAllQueries(int pageSize ,int pageNumber);
	
	RequiredDocumentsResponseDto addFileToDatabase(RequiredDocumentsRequestDto requiredDocumentsRequestDto);

	RequiredDocumentsResponseDto uploadFile(MultipartFile file) throws IOException;

	PageResponse<UserAdminViewResponse> getAllUser(int pageSize ,int pageNumber);
	PageResponse<UserAdminViewResponse> getUserByFirstName(int pageSize ,int pageNumber ,String firstName);
	UserAdminViewResponse getUserByEmail(String email);
	UserAdminViewResponse getUserById(int Id);
	
	
	
}
