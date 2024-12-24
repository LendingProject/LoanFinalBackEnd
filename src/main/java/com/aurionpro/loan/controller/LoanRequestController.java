package com.aurionpro.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.Loanstatus;
import com.aurionpro.loan.service.LoanRequestService;

@RestController
@RequestMapping("/loanapp")
public class LoanRequestController {

	@Autowired
	private LoanRequestService service;
	@GetMapping("/loanrequests")
	public ResponseEntity<LoanResponseDto> getLoanRequestByIdAndEmail(
			@RequestParam(required = false) int id){
		
		return  ResponseEntity.ok(service.getLoanRequestByLoanId(id));
	}
	
	@GetMapping("/loanrequests/all")
	public ResponseEntity<PageResponse<LoanResponseDto>> getAllLoanRequest(
			@RequestParam int pageNumber,
			@RequestParam int pageSize,
			@RequestParam(required = false) String SchemeName,
			@RequestParam(required = false) Loanstatus status ){
		// Check if schemeName or status is provided and call appropriate service method
	    
	    if (SchemeName != null) {
	        return ResponseEntity.ok(service.getLoanRequestBySchemeName(pageNumber,pageSize,SchemeName));
	    } else if (status != null) {
	        return ResponseEntity.ok(service.getLoanRequestStatus(pageNumber,pageSize,status));
	    }

	
	    return ResponseEntity.ok(service.getAllLoanRequest(pageSize,pageNumber));
	}
	
}
