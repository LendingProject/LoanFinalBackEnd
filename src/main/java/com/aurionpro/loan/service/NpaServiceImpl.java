package com.aurionpro.loan.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.dto.MissedPaymentDto;
import com.aurionpro.loan.dto.NpaUserDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.NPA;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.MissedPayementRepository;
import com.aurionpro.loan.repository.NpaRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class NpaServiceImpl {
	@Autowired
	 private final MissedPayementRepository missedPaymentRepository;
	@Autowired
	    private final NpaRespository npaRepository;
	    
	    
	    public boolean isUserInNpa(User user) {
	        NPA npa = npaRepository.findByUserAndDeletedFalse(user);
	        return (npa != null);
	    }

	    /**
	     * This scheduled method will run periodically (e.g. once a day)
	     * and check for users that have 3 or more active missed payments.
	     */
	    @Scheduled(cron = "0 0 0 * * ?") 
	    public void checkAndMarkNpaUsers() {
	      
	        List<MissedPayment> allMissed = missedPaymentRepository.findAll();

	       
	        allMissed.stream()
	                 .filter(mp -> !mp.isPaid() && !mp.isDeleted())
	                 .map(MissedPayment::getUser)
	                 .distinct()
	                 .forEach(user -> {
	                     long missedCount = missedPaymentRepository
	                         .findByUserAndIsPaidFalseAndDeletedFalse(user).size();
	                     
	                     if(missedCount >= 3) {
	                         // Mark user as NPA if not already in it
	                    	 NPA npa = npaRepository.findByUserAndDeletedFalse(user);
	                         if(npa == null) {
	                             npa = new NPA();
	                             npa.setUser(user);
	                             npa.setNpaDate(Date.valueOf(LocalDate.now()));
	                             npa.setDeleted(false);
	                             npaRepository.save(npa);
	                         }
	                     }
	                 });
	    }

	    /**
	     * If user settles missed payments, remove them (soft delete them), 
	     * also soft delete or remove NPA entry.
	     */
	    public void removeUserFromNpa(User user) {
	    	NPA existingNpa = npaRepository.findByUserAndDeletedFalse(user);
	        if(existingNpa != null) {
	          
	            existingNpa.setDeleted(true);
	            npaRepository.save(existingNpa);
	        }
	       
	    }
	    
	    public PageResponse<NpaUserDto> getAllNpaUsersWithMissedPayments(int pageNumber, int pageSize) {
	        // Build a Pageable object
	        Pageable pageable = PageRequest.of(pageNumber, pageSize);

	        // 1) Fetch a Page<Npa> from the repository
	        Page<NPA> npaPage = npaRepository.findByDeletedFalse(pageable);

	        // 2) Convert each NPA record -> NpaUserDto
	        List<NpaUserDto> dtoList = npaPage.getContent().stream().map(npa -> {
	            User user = npa.getUser();
	            // fetch missed payments for the user (active = not paid, not deleted),
	            // or any logic you want
	            List<MissedPayment> missedPayments = missedPaymentRepository
	                    .findByUserAndIsPaidFalseAndDeletedFalse(user);

	            // map missed payments to MissedPaymentDto
	            List<MissedPaymentDto> missedPaymentDtos = missedPayments.stream().map(mp -> {
	                MissedPaymentDto dto = new MissedPaymentDto();
	                dto.setId(mp.getId());
	                dto.setEmiAmount(mp.getEmiAmount());
	                dto.setDueDate(mp.getDueDate());
	                dto.setPaid(mp.isPaid());
	                dto.setDeleted(mp.isDeleted());
	                return dto;
	            }).toList();

	            // build the NpaUserDto
	            NpaUserDto npaUserDto = new NpaUserDto();
	            npaUserDto.setUserId(user.getId());
	            npaUserDto.setFirstName(user.getFirstName());
	            npaUserDto.setLastName(user.getLastName());
	            npaUserDto.setMissedPayments(missedPaymentDtos);

	            return npaUserDto;
	        }).toList();

	        // 3) Build the PageResponse
	        PageResponse<NpaUserDto> response = new PageResponse<>();
	        response.setTotalElements(npaPage.getTotalElements());
	        response.setTotalPages(npaPage.getTotalPages());
	        response.setPageSize(npaPage.getSize());
	        response.setContents(dtoList);
	        response.setLastPage(npaPage.isLast());

	        return response;
	    }
	    
	}

