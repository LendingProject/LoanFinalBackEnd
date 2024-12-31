package com.aurionpro.loan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.dto.AdminViewLoanRequestDto;
import com.aurionpro.loan.dto.LoanOfficerReportDto;
import com.aurionpro.loan.dto.LoanRequestDetailsDTO;
import com.aurionpro.loan.dto.LoanRequestDto;
import com.aurionpro.loan.dto.LoanRequestReportDto;
import com.aurionpro.loan.dto.LoanResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.LoanOfficer;
import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.Loanstatus;
import com.aurionpro.loan.repository.LoanOfficerRepository;
import com.aurionpro.loan.repository.LoanRequestRepository;

@Service
public class LoanRequestServiceImpl implements LoanRequestService{
	
	@Autowired
	private LoanRequestRepository loanRequestRepository;
    @Autowired 
    private ModelMapper modelMapper; 
    
    @Autowired
    private LoanOfficerRepository loanOfficerRepository;
	
	@Override
	public PageResponse<LoanResponseDto> getAllLoanRequest(int pageNumber, int pageSize) {
		Pageable pageable =  PageRequest.of(pageSize, pageNumber);
		Page<LoanRequest> pageLoanRequest =  loanRequestRepository.findAll(pageable);
		List<LoanRequest> dbLoanRequest =  pageLoanRequest.getContent();	
		List<LoanResponseDto> loanRequests =  new ArrayList<>();
		dbLoanRequest.forEach((loanRequest)->{
			LoanResponseDto loanResponseDto = modelMapper.map(loanRequest, LoanResponseDto.class);
			loanResponseDto.setLoanid(loanRequest.getId()); 
			loanResponseDto.setLoanOfficeId(loanRequest.getLoanofficer().getId());  
			loanResponseDto.setLoanamount(loanRequest.getLoanamount()); 
			loanResponseDto.setLoanscheme_id(loanRequest.getLoanscheme().getId());
			loanRequests.add(loanResponseDto);
			
		});
		PageResponse<LoanResponseDto> response =  new PageResponse<LoanResponseDto>(); 
		response.setContents(loanRequests);
		response.setLastPage(pageLoanRequest.isLast());
		response.setPageSize(pageLoanRequest.getSize());
		response.setTotalElements(pageLoanRequest.getTotalElements());
		response.setTotalPages(pageLoanRequest.getTotalPages());

		return response;
	}

	@Override
	public LoanResponseDto getLoanRequestByLoanId(int LoanId) {
		Optional<LoanRequest> optionalLoanRequest = loanRequestRepository.findById(LoanId);

		if(optionalLoanRequest.isEmpty())
			throw new RuntimeException("Cannot find loan with id: "+LoanId);
		LoanRequest dbLoanRequest =  optionalLoanRequest.get();
		 LoanResponseDto responseDto = modelMapper.map(dbLoanRequest, LoanResponseDto.class);
		 responseDto.setLoanid(dbLoanRequest.getId()); 
		    responseDto.setLoanOfficeId(dbLoanRequest.getLoanofficer().getId());  
		    responseDto.setLoanamount(dbLoanRequest.getLoanamount()); 
		    responseDto.setLoanscheme_id(dbLoanRequest.getLoanscheme().getId());
		return responseDto;
	}

	@Override
	public PageResponse<LoanResponseDto> getLoanRequestBySchemeName(int pageNumber, int pageSize,String schemeName) {
		Pageable pageable =  PageRequest.of(pageNumber, pageSize);
		Page<LoanRequest> pageLoanRequest =  loanRequestRepository.findByLoanscheme_Schemename(pageable,schemeName);
		List<LoanRequest> dbLoanRequest =  pageLoanRequest.getContent();	
		List<LoanResponseDto> loanRequests =  new ArrayList<>();
		dbLoanRequest.forEach((loanRequest)->{
			LoanResponseDto loanResponseDto = modelMapper.map(loanRequest, LoanResponseDto.class);
			loanResponseDto.setLoanid(loanRequest.getId()); 
			loanResponseDto.setLoanOfficeId(loanRequest.getLoanofficer().getId());  
			loanResponseDto.setLoanamount(loanRequest.getLoanamount()); 
			loanResponseDto.setLoanscheme_id(loanRequest.getLoanscheme().getId());
			loanRequests.add(loanResponseDto);
			
		});
		PageResponse<LoanResponseDto> response =  new PageResponse<LoanResponseDto>(); 
		response.setContents(loanRequests);
		response.setLastPage(pageLoanRequest.isLast());
		response.setPageSize(pageLoanRequest.getSize());
		response.setTotalElements(pageLoanRequest.getTotalElements());
		response.setTotalPages(pageLoanRequest.getTotalPages());

		return response;
	}

	@Override
	public PageResponse<LoanResponseDto> getLoanRequestStatus(int pageNumber, int pageSize, Loanstatus status) {
		Pageable pageable =  PageRequest.of(pageNumber, pageSize);
		Page<LoanRequest> pageLoanRequest =  loanRequestRepository.findByLoanstatus(status,pageable);
		List<LoanRequest> dbLoanRequest =  pageLoanRequest.getContent();	
		List<LoanResponseDto> loanRequests =  new ArrayList<>();
		dbLoanRequest.forEach((loanRequest)->{
			LoanResponseDto loanResponseDto = modelMapper.map(loanRequest, LoanResponseDto.class);
			loanResponseDto.setLoanid(loanRequest.getId()); 
			loanResponseDto.setLoanOfficeId(loanRequest.getLoanofficer().getId());  
			loanResponseDto.setLoanamount(loanRequest.getLoanamount()); 
			loanResponseDto.setLoanscheme_id(loanRequest.getLoanscheme().getId());
			loanRequests.add(loanResponseDto);
			
		});
		PageResponse<LoanResponseDto> response =  new PageResponse<LoanResponseDto>(); 
		response.setContents(loanRequests);
		response.setLastPage(pageLoanRequest.isLast());
		response.setPageSize(pageLoanRequest.getSize());
		response.setTotalElements(pageLoanRequest.getTotalElements());
		response.setTotalPages(pageLoanRequest.getTotalPages());

		return response;
	}

	@Override
	public LoanResponseDto getLoanRequestByUserEmail(String email) {
		LoanRequest LoanRequest =  loanRequestRepository.findByUser_Email(email);
		if(LoanRequest ==  null)
			throw new RuntimeException("Cannot find Loan With emial: "+email);
		return modelMapper.map(LoanRequest, LoanResponseDto.class);
	}

	@Override
	public PageResponse<AdminViewLoanRequestDto> viewLoanReequestByadmin(int pageNumber ,int pageSize) {
	
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        
       
        Page<Object[]> results = loanRequestRepository.findLoanRequestDetails(pageable);
        
    
        List<AdminViewLoanRequestDto> loanRequestDetails = new ArrayList<>();
        for (Object[] result : results) {
        	AdminViewLoanRequestDto loanRequestDTO = new AdminViewLoanRequestDto(
                (Double) result[0],    // loanamount
                (Integer) result[1],   // time
                (String) result[2],    // scheme name
                (String) result[3],    // user's first name
                (String) result[4],    // user's last name
                (String) result[5],    // loan officer's first name
                (String) result[6]     // loan officer's last name
            );
            loanRequestDetails.add(loanRequestDTO);
        }
        
        // Return the PageResponse containing the paginated data
        PageResponse<AdminViewLoanRequestDto> pageResponse = new PageResponse<>();
        pageResponse.setTotalElements(results.getTotalElements());
        pageResponse.setTotalPages(results.getTotalPages());
        pageResponse.setPageSize(results.getSize());
        pageResponse.setContents(loanRequestDetails);
        pageResponse.setLastPage(results.isLast());
        
        return pageResponse;
	}

	
	@Override
	    public PageResponse<LoanOfficerReportDto> getLoanStatusReport(int pageNumber , int pageSize) {
		
	     Pageable pageable = PageRequest.of(pageNumber, pageSize);
	     
	     Page<LoanOfficer> loanOfficersPage = loanOfficerRepository.findAll(pageable);
	     
	     List<LoanOfficerReportDto> loanOfficerReportDtos = new ArrayList<>();
	     
	     for (LoanOfficer officer : loanOfficersPage.getContent()) {
	        
	         List<LoanRequestReportDto> approvedLoans = loanRequestRepository.findLoanRequestsByStatus(officer.getId(), Loanstatus.APPROVED);
	         List<LoanRequestReportDto> rejectedLoans = loanRequestRepository.findLoanRequestsByStatus(officer.getId(), Loanstatus.REJECTED);
	         List<LoanRequestReportDto> pendingLoans = loanRequestRepository.findLoanRequestsByStatus(officer.getId(), Loanstatus.PENDING);

	       
	         LoanOfficerReportDto loanOfficerReportDto = new LoanOfficerReportDto(
	             officer.getFirstName(),
	             officer.getLastName(),
	             (long) approvedLoans.size(),
	             (long) rejectedLoans.size(),
	             (long) pendingLoans.size(),
	             approvedLoans,
	             rejectedLoans,
	             pendingLoans
	         );

	         loanOfficerReportDtos.add(loanOfficerReportDto);
	     }

	     PageResponse<LoanOfficerReportDto> pageResponse = new PageResponse<>();
	     pageResponse.setTotalElements(loanOfficersPage.getTotalElements());
	     pageResponse.setTotalPages(loanOfficersPage.getTotalPages());
	     pageResponse.setPageSize(pageable.getPageSize());
	     pageResponse.setContents(loanOfficerReportDtos);
	     pageResponse.setLastPage(loanOfficersPage.isLast());

	 
	     return pageResponse;

	       
	    }
	
	 public PageResponse<LoanRequestDetailsDTO> getLoanRequestDetails(int pageNumber , int pageSize) {
		  Pageable pageable = PageRequest.of(pageNumber, pageSize);
	        Page<LoanRequestDetailsDTO> pagedResult = loanRequestRepository.findLoanRequestDetails1(pageable);

	        return new PageResponse<>(
	            pagedResult.getTotalElements(),
	            pagedResult.getTotalPages(),
	            pagedResult.getSize(),
	            pagedResult.getContent(),
	            pagedResult.isLast()
	        );
	    }
	
}
