package com.aurionpro.loan.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.loan.dto.MissedPaymentDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.LoanRequestRepository;
import com.aurionpro.loan.repository.UserRepository;
import com.aurionpro.loan.service.MIssedPayementServiceImpl;
import com.aurionpro.loan.service.NpaServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/loanapp/missed-payments")
@RequiredArgsConstructor
public class MissedPaymentController {
	@Autowired
	  private final MIssedPayementServiceImpl missedPaymentService;
	@Autowired
	    private final NpaServiceImpl npaService;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private LoanRequestRepository loanRequestRepository;
	
	@Autowired
	private ModelMapper mapper;
		    
	    @PostMapping("/record")
	    public ResponseEntity<MissedPaymentDto>  recordMissedPayment(
	            @RequestParam("userId") int userId,
	            @RequestParam("loanId") int loanId,
	            @RequestParam("emiAmount") double emiAmount) {
	    	User users = userRepository.findById(userId)
	                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

	        // 2) Fetch the LoanRequest by loanId
	        LoanRequest loanRequest = loanRequestRepository.findById(loanId)
	                .orElseThrow(() -> new RuntimeException("Loan request not found with ID: " + loanId));

	        // 3) Delegate to the service layer to create & save the MissedPayment record
	        MissedPayment missedPayment = missedPaymentService.recordMissedPayment(users, loanRequest, emiAmount);

	        MissedPaymentDto dto = mapper.map(missedPayment, MissedPaymentDto.class);
	        
	        // 4) Return the new MissedPayment in the response
	        return ResponseEntity.ok(dto);
	    }

	    @GetMapping("/{userId}")

	    public ResponseEntity<PageResponse<MissedPaymentDto>> getMissedPaymentsByUser(
	            @PathVariable int userId,
	            @RequestParam(defaultValue = "0") int pageNumber,
	            @RequestParam(defaultValue = "10") int pageSize) {

	        PageResponse<MissedPaymentDto> response =
	                missedPaymentService.getMissedPaymentsByUser(userId, pageNumber, pageSize);
	        return ResponseEntity.ok(response);
	    }

	    @PostMapping("/settle/{userId}")
	    public void settleMissedPayments(@PathVariable("userId") User user) {
	        missedPaymentService.settleMissedPaymentsForUser(user);
	        // Also remove user from NPA if present
	        npaService.removeUserFromNpa(user);
	    }
	}


