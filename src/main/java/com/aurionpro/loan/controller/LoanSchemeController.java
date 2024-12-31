package com.aurionpro.loan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.LoanSchemeRequestDto;
import com.aurionpro.loan.dto.LoanSchemeResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.UpdateLoanSchemeDto;
import com.aurionpro.loan.service.LoanSchemeService;

@RestController
@RequestMapping("/loanapp")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanSchemeController {

	@Autowired
	private LoanSchemeService service;
	
	//Loan scheme
		@PostMapping("/loanschemes")
	    public ResponseEntity<LoanSchemeResponseDto> addLoanScheme(@RequestBody LoanSchemeRequestDto loanSchemeRequestDto) {
	        LoanSchemeResponseDto response = service.addLoanScheme(loanSchemeRequestDto);
	        return ResponseEntity.status(HttpStatus.CREATED).body(response);
	    }
		  @GetMapping("/loanschemes/{Id}")
		    public ResponseEntity<LoanSchemeResponseDto> getLoanSchemeById(@PathVariable int Id) {
		        LoanSchemeResponseDto response = service.getloanScehmeById(Id);
		        return ResponseEntity.ok(response);
		    }

	    @DeleteMapping("/loanschemes/{loanSchemeId}")
	    public ResponseEntity<LoanSchemeResponseDto> deleteLoanScheme(@PathVariable int loanSchemeId) {
	        LoanSchemeResponseDto response = service.deleteLoanScheme(loanSchemeId);
	        return ResponseEntity.ok(response);
	    }

	    @PutMapping("/loanschemes/{id}")
	    public ResponseEntity<LoanSchemeResponseDto> updateLoanScheme(@RequestBody UpdateLoanSchemeDto updateLoanSchemeDto, @PathVariable  int id) {
	        LoanSchemeResponseDto response = service.updateLoanScheme(updateLoanSchemeDto, id );
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/allloanschemes")
	    public ResponseEntity<PageResponse<LoanSchemeResponseDto>> getAllLoanSchemes(@RequestParam(defaultValue = "0") int pageNumber,
	                                                                                 @RequestParam(defaultValue = "10") int pageSize) {
	        PageResponse<LoanSchemeResponseDto> response = service.getAllLoanScheme(pageSize, pageNumber);
	        return ResponseEntity.ok(response);
	    }

	    @GetMapping("/loanschemes/search")
	    public ResponseEntity<PageResponse<LoanSchemeResponseDto>> getLoanSchemesByName(@RequestParam(defaultValue = "0") int pageNumber,
	                                                                                     @RequestParam(defaultValue = "10") int pageSize,
	                                                                                     @RequestParam String schemeName) {
	        PageResponse<LoanSchemeResponseDto> response = service.getLoanSchemeByName(pageSize, pageNumber, schemeName);
	        return ResponseEntity.ok(response);
	    }
}
