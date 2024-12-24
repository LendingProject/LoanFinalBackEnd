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
	public LoanSchemeResponseDto deleteLoanScheme(int loanSchemeId) {
		Optional<LoanScheme> optionalLoanScheme = loanSchemeRepository.findById(loanSchemeId);
		if(optionalLoanScheme.isEmpty())
			throw new RuntimeException("Cannot find LoanScheme with  Id: "+loanSchemeId);
		
		LoanScheme dbLoanScheme = optionalLoanScheme.get();
		dbLoanScheme.setDeleted(true);
			return modelMapper.map(dbLoanScheme, LoanSchemeResponseDto.class);
	}

	@Override
	public LoanSchemeResponseDto updateLoanScheme(UpdateLoanSchemeDto updateLoanSchemeDto) {
		Optional<LoanScheme> optionalLoanScheme = loanSchemeRepository.findById(updateLoanSchemeDto.getLoanSchemeId());
		if(optionalLoanScheme.isEmpty())
			throw new RuntimeException("Cannot find LoanScheme with  Id: "+updateLoanSchemeDto.getLoanSchemeId());
		
		LoanScheme dbLoanScheme = optionalLoanScheme.get();
		
		if(updateLoanSchemeDto.getField().equals("schemename")) 
			dbLoanScheme.setSchemename(updateLoanSchemeDto.getSchemename());	
		if(updateLoanSchemeDto.getField().equals("maxamount")) 
			dbLoanScheme.setMaxamount(updateLoanSchemeDto.getMaxamount());	
		
		if(updateLoanSchemeDto.getField().equals("minamount")) 
			dbLoanScheme.setMinamount(updateLoanSchemeDto.getMinamount());	
		if(updateLoanSchemeDto.getField().equals("interest")) 
			dbLoanScheme.setInterest(updateLoanSchemeDto.getInterest());	
		return modelMapper.map(dbLoanScheme, LoanSchemeResponseDto.class);
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
}
