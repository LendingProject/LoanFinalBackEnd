package com.aurionpro.loan.controller;

import com.aurionpro.loan.dto.*;
import com.aurionpro.loan.service.LoanOfficerService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loanofficers")
public class LoanOfficerController {

    @Autowired
    private LoanOfficerService loanOfficerService;

    // View loan requests assigned to loan officer
    @GetMapping("/{officerId}/loanrequests")
    public ResponseEntity<PageResponse<LoanResponseDto>> viewLoanRequests(
            @PathVariable int officerId, 
            @RequestParam int pageNumber, 
            @RequestParam int pageSize) {
        
        PageResponse<LoanResponseDto> loanRequests = loanOfficerService.viewLoanRequests(officerId, pageNumber, pageSize);
        return ResponseEntity.ok(loanRequests);
    }
    // Approve a loan
    @PostMapping("/approveloan")
    public ResponseEntity<ApproveLoanResponseDto> approveLoan(@RequestBody ApproveLoanRequestDto approveLoanRequestDto) {
        ApproveLoanResponseDto response = loanOfficerService.approveLoan(approveLoanRequestDto);
        return ResponseEntity.ok(response);
    }

    // Reject a loan
    @PostMapping("/rejectloan")
    public ResponseEntity<RejectionRemarkResponseDto> rejectLoan(@RequestBody RejectionRemarkRequestDto rejectionRemarkRequestDto) {
        RejectionRemarkResponseDto response = loanOfficerService.rejectLoan(rejectionRemarkRequestDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/replyenquiry")
    public ResponseEntity<ReplyEnquiryResponseDto> replyToEnquiry(@RequestBody ReplyEnquiryRequestDto replyEnquiryRequestDto) {
        ReplyEnquiryResponseDto enquiryResponse = loanOfficerService.replyToEnquiry(replyEnquiryRequestDto);
        return ResponseEntity.ok(enquiryResponse);
    }
//    //updation
//    @PutMapping("/{officerId}/profile")
//    public ResponseEntity<LoanOfficerProfileUpdateResponseDto> updateProfile(@PathVariable int officerId,@RequestBody @Valid LoanOfficerProfileUpdateRequestDto dto){
//    	return ResponseEntity.ok(loanOfficerService.updateLoanOfficerProfile(officerId, dto));
//    	
//    }
}