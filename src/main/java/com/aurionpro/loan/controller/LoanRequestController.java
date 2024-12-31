package com.aurionpro.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.AdminViewLoanRequestDto;
import com.aurionpro.loan.dto.LoanOfficerReportDto;
import com.aurionpro.loan.dto.LoanRequestDetailsDTO;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.Loanstatus;
import com.aurionpro.loan.service.LoanRequestService;

@RestController
@RequestMapping("/loanapp")
@CrossOrigin(origins = "http://localhost:4200")
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
	
	 @GetMapping("/details")
	    public ResponseEntity<PageResponse<AdminViewLoanRequestDto>> getLoanRequestDetails(
	            @RequestParam(defaultValue = "10") int pageSize,  // Default to page 0
	            @RequestParam(defaultValue = "0") int pageNumber   // Default to 10 items per page
	    ) {
	        return ResponseEntity.ok(service.viewLoanReequestByadmin( pageNumber,pageSize));
	    }
	
	 @GetMapping("/report")
	    public ResponseEntity<PageResponse<LoanOfficerReportDto>> getLoanStatusReport(
	            @RequestParam(value = "page", defaultValue = "0") int pageNumber,
	            @RequestParam(value = "size", defaultValue = "10") int pageSize) {
	        
	    
	        PageResponse<LoanOfficerReportDto> response = service.getLoanStatusReport(pageNumber, pageSize);

	      
	        return ResponseEntity.ok(response);
	    }
	 
	
	 
}
