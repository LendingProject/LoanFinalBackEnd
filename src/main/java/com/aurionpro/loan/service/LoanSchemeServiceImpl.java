package com.aurionpro.loan.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.dto.LoanSchemeRequestDto;
import com.aurionpro.loan.dto.LoanSchemeResponseDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.dto.UpdateLoanSchemeDto;
import com.aurionpro.loan.entity.LoanScheme;
import com.aurionpro.loan.repository.LoanSchemeRepository;



@Service
public class LoanSchemeServiceImpl implements LoanSchemeService{

	@Autowired
	private LoanSchemeRepository loanSchemeRepository;
    @Autowired 
    private ModelMapper modelMapper; 

    
    @Override
	public LoanSchemeResponseDto addLoanScheme(LoanSchemeRequestDto loanSchemeRequestDto) {
		 LoanScheme loanScheme = modelMapper.map(loanSchemeRequestDto, LoanScheme.class);
		 loanScheme.setIsdeleted(false);
	        LoanScheme savedLoanScheme = loanSchemeRepository.save(loanScheme);
	        return modelMapper.map(savedLoanScheme, LoanSchemeResponseDto.class);
	}




	@Override
	public PageResponse<LoanSchemeResponseDto> getAllLoanScheme(int pageSize, int pageNumber) {
		Pageable pageable =  PageRequest.of(pageNumber, pageSize);
		Page<LoanScheme> pageScheme =  loanSchemeRepository.findAll(pageable);
		List<LoanScheme> dbLoanScehme =  pageScheme.getContent();	
		List<LoanSchemeResponseDto> loanSchemes =  new ArrayList<>();
		dbLoanScehme.forEach((loanScheme)->{
			LoanSchemeResponseDto response = modelMapper.map(loanScheme, LoanSchemeResponseDto.class);

			   response.setIsdelete(loanScheme.isIsdeleted());
			loanSchemes.add(response);
		});
		PageResponse<LoanSchemeResponseDto> response =  new PageResponse<LoanSchemeResponseDto>(); 
		response.setContents(loanSchemes);
		response.setLastPage(pageScheme.isLast());
		response.setPageSize(pageScheme.getSize());
		response.setTotalElements(pageScheme.getTotalElements());
		response.setTotalPages(pageScheme.getTotalPages());
		
		return response;
	    

	}
	

	@Override
	public LoanSchemeResponseDto deleteLoanScheme(int loanSchemeId) {
		Optional<LoanScheme> optionalLoanScheme = loanSchemeRepository.findById(loanSchemeId);
		if(optionalLoanScheme.isEmpty())
			throw new RuntimeException("Cannot find LoanScheme with  Id: "+loanSchemeId);
		
		 LoanScheme dbLoanScheme = optionalLoanScheme.get();

		 
		 
		 dbLoanScheme.setIsdeleted(!dbLoanScheme.isIsdeleted());
	    loanSchemeRepository.save(dbLoanScheme);
	   
	    LoanSchemeResponseDto response = modelMapper.map(dbLoanScheme, LoanSchemeResponseDto.class);

	   response.setIsdelete(dbLoanScheme.isIsdeleted());

	    return response;
	    
			
	    
	}

	@Override
	public LoanSchemeResponseDto updateLoanScheme(UpdateLoanSchemeDto updateLoanSchemeDto,int id) {
		Optional<LoanScheme> optionalLoanScheme = loanSchemeRepository.findById(id);
		if(optionalLoanScheme.isEmpty())
			throw new RuntimeException("Cannot find LoanScheme with  Id: "+id);
		
		LoanScheme dbLoanScheme = optionalLoanScheme.get();
		
		dbLoanScheme.setInterest(updateLoanSchemeDto.getInterest());
		dbLoanScheme.setMaxamount(updateLoanSchemeDto.getMaxamount());
		dbLoanScheme.setMinamount(updateLoanSchemeDto.getMinamount());
		dbLoanScheme.setSchemename(updateLoanSchemeDto.getSchemename());
		
		loanSchemeRepository.save(dbLoanScheme);
		LoanSchemeResponseDto dto =  new LoanSchemeResponseDto();
		dto.setSchemeId(id);
		dto=modelMapper.map(dbLoanScheme, LoanSchemeResponseDto.class);
		
		return dto;
	}

	@Override
	public PageResponse<LoanSchemeResponseDto> getLoanSchemeByName(int pageSize, int pageNumber, String schemeName) {
	 
		Pageable pageable =  PageRequest.of(pageNumber, pageSize);
		Page<LoanScheme> pageScheme =  loanSchemeRepository.findBySchemename(pageable,schemeName);
		List<LoanScheme> dbLoanScehme =  pageScheme.getContent();	
		List<LoanSchemeResponseDto> loanSchemes =  new ArrayList<>();
		dbLoanScehme.forEach((loanScheme)->{
			LoanSchemeResponseDto loanSchemeResponseDto = modelMapper.map(loanScheme, LoanSchemeResponseDto.class);
			loanSchemes.add(loanSchemeResponseDto);
		});
		PageResponse<LoanSchemeResponseDto> response =  new PageResponse<LoanSchemeResponseDto>(); 
		response.setContents(loanSchemes);
		response.setLastPage(pageScheme.isLast());
		response.setPageSize(pageScheme.getSize());
		response.setTotalElements(pageScheme.getTotalElements());
		response.setTotalPages(pageScheme.getTotalPages());
		
		return response;
	}




	@Override
	public LoanSchemeResponseDto getloanScehmeById(int id) {
		Optional<LoanScheme> loanSchemeOptional = loanSchemeRepository.findById(id); 
		if(loanSchemeOptional.isEmpty())
			throw new RuntimeException("Cannot find LoanScheme with  Id: "+id);
		LoanScheme dbLoanScheme = loanSchemeOptional.get();
		return modelMapper.map(dbLoanScheme, LoanSchemeResponseDto.class);

	}
}
