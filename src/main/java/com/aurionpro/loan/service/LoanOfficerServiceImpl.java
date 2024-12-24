package com.aurionpro.loan.service;

import com.aurionpro.loan.dto.*;
import com.aurionpro.loan.entity.*;
import com.aurionpro.loan.repository.*;
import com.aurionpro.loan.service.LoanOfficerService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoanOfficerServiceImpl implements LoanOfficerService {

    @Autowired
    private LoanOfficerRepository loanOfficerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private RejectionRemarkRepository rejectionRemarkRepository;

    @Autowired
    private EnquiryRepository enquiryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // View loan requests assigned to loan officer (with pagination)
    @Override
    public PageResponse<LoanResponseDto> viewLoanRequests(int officerId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<LoanRequest> loanRequestPage = loanRequestRepository.findByLoanofficerId(officerId, pageable);

        List<LoanResponseDto> loanResponses = loanRequestPage.getContent().stream()
                .map(request -> modelMapper.map(request, LoanResponseDto.class))
                .collect(Collectors.toList());

        return new PageResponse<>(
                loanRequestPage.getTotalElements(),
                loanRequestPage.getTotalPages(),
                pageSize,
                loanResponses,
                loanRequestPage.isLast()
        );
    }

    // Approve a loan request
    @Override
    public ApproveLoanResponseDto approveLoan(ApproveLoanRequestDto approveLoanRequestDto) {
        LoanRequest loanRequest = loanRequestRepository.findById(approveLoanRequestDto.getLoanId())
                .orElseThrow(() -> new IllegalArgumentException("Loan request not found"));

        loanRequest.setLoanstatus(Loanstatus.APPROVED);
        loanRequestRepository.save(loanRequest);

        return new ApproveLoanResponseDto(
                loanRequest.getId(),
                Loanstatus.APPROVED.name(),
                "Loan approved successfully"
        );
    }

    @Override
    public RejectionRemarkResponseDto rejectLoan(RejectionRemarkRequestDto rejectionRemarkRequestDto) {
        // Fetch the loan request
       Optional<LoanOfficer> optionalLoanOfficer = loanOfficerRepository.findById(rejectionRemarkRequestDto.getOfficerId());
       if(optionalLoanOfficer.isEmpty())
    	   throw new RuntimeException("Officer not founded");
       LoanOfficer dbOfficer = optionalLoanOfficer.get(); 
       
       Optional<LoanRequest> optionalLoanRequest = loanRequestRepository.findById(rejectionRemarkRequestDto.getLoanId());
       if(optionalLoanRequest.isEmpty())
    	   throw new RuntimeException("Officer not founded");
       LoanRequest dbLoanRequest = optionalLoanRequest.get(); 
       
       dbLoanRequest.setLoanstatus(Loanstatus.REJECTED);
        
       RejectionRemark rejectionRemark =  new RejectionRemark();
       rejectionRemark.setLoanofficerremarks(dbOfficer);
       rejectionRemark.setLoanrequest(dbLoanRequest);
       rejectionRemark.setMessage(rejectionRemarkRequestDto.getMessage());
       
       loanRequestRepository.save(dbLoanRequest);
       rejectionRemarkRepository.save(rejectionRemark);
       
       RejectionRemarkResponseDto response = new RejectionRemarkResponseDto();
       response.setOfficerId(dbOfficer.getId());
       response.setLoanId(dbLoanRequest.getId());
       response.setMessage(rejectionRemarkRequestDto.getMessage());
       
       return response;
       
       
    }


    @Override
    public ReplyEnquiryResponseDto replyToEnquiry(ReplyEnquiryRequestDto replyEnquiryRequestDto) {
        // Extract enquiryId and response from the DTO
        int enquiryId = replyEnquiryRequestDto.getEnquiryId();
        String response = replyEnquiryRequestDto.getResponse();

       
        Enquiry enquiry = enquiryRepository.findById(enquiryId)
                .orElseThrow(() -> new IllegalArgumentException("Enquiry not found"));

        
        enquiry.setResponse(response);
        enquiry.setStatus("RESOLVED");
        enquiryRepository.save(enquiry);

        return new ReplyEnquiryResponseDto(
                enquiry.getId(),
                enquiry.getQuestion(),
                enquiry.getResponse(),
                enquiry.getStatus()
        );
    } 
    
	@Override
	public List<LoanResponseDto> viewLoanRequests(int officerId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
//
//	@Override
//	public LoanOfficerProfileUpdateResponseDto updateLoanOfficerProfile(int officerId,
//			LoanOfficerProfileUpdateRequestDto officerDto) {
//		LoanOfficer officer=loanOfficerRepository.findById(officerId).orElseThrow(()->new RuntimeException("Loan Officer not found with id: "+officerId));
//		officer.setFirstName(officerDto.getFirstName());
//		officer.setLastName(officerDto.getLastName());
//		
//		if(officerDto.getPassword()!=null && !officerDto.getPassword().isBlank()) {
//			officer.setPassword(officerDto.getPassword());
//		}
//		officer.setPancardNumber(officerDto.getPancardNumber());
//		officer.setDob(officerDto.getDob());
//		officer.setContactNumber(officerDto.getContactNumber());
//		officer.setGender(officerDto.getGender());
//		LoanOfficer updateOfficer=loanOfficerRepository.save(officer);
//		
//		return modelMapper.map(updateOfficer, LoanOfficerProfileUpdateResponseDto.class);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


