//package com.aurionpro.loan.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.stereotype.Service;
//
//import com.aurionpro.loan.dto.AdminRequestDto;
//import com.aurionpro.loan.dto.AdminResponseDto;
//import com.aurionpro.loan.dto.LoanOfficerRequestDto;
//import com.aurionpro.loan.dto.LoanOfficerResponseDto;
//import com.aurionpro.loan.dto.LoanSchemeRequestDto;
//import com.aurionpro.loan.dto.LoanSchemeResponseDto;
//import com.aurionpro.loan.dto.PageResponse;
//import com.aurionpro.loan.dto.RequiredDocumentsRequestDto;
//import com.aurionpro.loan.dto.RequiredDocumentsResponseDto;
//import com.aurionpro.loan.entity.Admin;
//import com.aurionpro.loan.entity.LoanOfficer;
//import com.aurionpro.loan.entity.LoanScheme;
//import com.aurionpro.loan.repository.AdminRepository;
//import com.aurionpro.loan.repository.LoanOfficerRepository;
//import com.aurionpro.loan.repository.LoanSchemeRepository;
//
//@Service
//public class AdminServiceImpl implements AdminService{
//	
//	@Autowired
//	private AdminRepository adminRepository;
//	@Autowired
//	private LoanOfficerRepository loanOfficerRepository;
//	
//	@Autowired
//	private LoanSchemeRepository loanSchemeRepository;
// 
//    @Autowired 
//    private ModelMapper modelMapper; 
//
//	@Override
//	public AdminResponseDto addAdmin(AdminRequestDto adminRequestDto) {
//		
//		Admin admin = modelMapper.map(adminRequestDto, Admin.class); 
//		 
//        
//        Admin savedAdmin = adminRepository.save(admin); 
// 
//        
//        return modelMapper.map(savedAdmin, AdminResponseDto.class); 
//	}
//
//	@Override
//	public LoanOfficerResponseDto addLoanOfficer(LoanOfficerRequestDto loanOfficerRequestDto) {
//		
//	LoanOfficer loanOfficer=modelMapper.map(loanOfficerRequestDto, LoanOfficer.class);
//	
//	LoanOfficer savedLoanOfficer=loanOfficerRepository.save(loanOfficer);
//	
//		return modelMapper.map(savedLoanOfficer, LoanOfficerResponseDto.class);
//	}
//
//	@Override
//	public LoanSchemeResponseDto addLoanScheme(LoanSchemeRequestDto loanSchemeRequestDto) {
//		 LoanScheme loanScheme = modelMapper.map(loanSchemeRequestDto, LoanScheme.class);
//	        LoanScheme savedLoanScheme = loanSchemeRepository.save(loanScheme);
//	        return modelMapper.map(savedLoanScheme, LoanSchemeResponseDto.class);
//	}
//
//
//
//
//	@Override
//	public PageResponse<LoanScheme> getAllLoanScheme(int pageSize, int pageNumber) {
//	    
//	    Pageable pageable = PageRequest.of(pageNumber, pageSize);
//	    Page<LoanScheme> loanSchemePage = loanSchemeRepository.findAll(pageable);
//   
//	    return new PageResponse<>(
//	        loanSchemePage.getTotalElements(),     
//	        loanSchemePage.getTotalPages(),          
//	        pageSize,                                
//	        loanSchemePage.getContent(),           
//	        loanSchemePage.isLast()                  
//	    );
//	}
//
//	
//	
//
//}
