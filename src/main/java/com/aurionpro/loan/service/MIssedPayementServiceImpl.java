package com.aurionpro.loan.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.dto.MissedPaymentDto;
import com.aurionpro.loan.dto.PageResponse;
import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.repository.MissedPayementRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MIssedPayementServiceImpl {

	 private final MissedPayementRepository missedPaymentRepository;

	    // Record a missed payment
	    public MissedPayment recordMissedPayment(User user, LoanRequest loanRequest, double emiAmount) {
	        MissedPayment missedPayment = new MissedPayment();
	        missedPayment.setUser(user);
	        missedPayment.setLoanRequest(loanRequest);
	        missedPayment.setEmiAmount(emiAmount);
	        missedPayment.setDueDate(Date.valueOf(LocalDate.now()));
	        missedPayment.setPaid(false);
	        missedPayment.setDeleted(false);
	        return missedPaymentRepository.save(missedPayment);
	    }

//	    // Retrieve all active (not paid and not deleted) missed payments for user
//	    public List<MissedPayment> getActiveMissedPaymentsForUser(User user) {
//	        return missedPaymentRepository.findByUserAndIsPaidFalseAndDeletedFalse(user);
//	    }

	    // Mark missed payments as paid and/or delete them
	    public void settleMissedPaymentsForUser(User user) {
	        List<MissedPayment> missedPayments = missedPaymentRepository.findByUserAndIsPaidFalseAndDeletedFalse(user);
	        for (MissedPayment mp : missedPayments) {
	           
	            // missedPaymentRepository.delete(mp);

	          
	            mp.setDeleted(true);
	            mp.setPaid(true);
	            missedPaymentRepository.save(mp);
	        }
	    }
	    
	    
	    
	    public PageResponse<MissedPaymentDto> getMissedPaymentsByUser(
	            int userId, int pageNumber, int pageSize) {

	        Pageable pageable = PageRequest.of(pageNumber, pageSize);

	        // 1) Fetch the page
	        Page<MissedPayment> mpPage = missedPaymentRepository.findByUserIdAndDeletedFalse(userId, pageable);

	        // 2) Convert to MissedPaymentDto
	        List<MissedPaymentDto> dtos = mpPage.getContent().stream().map(mp -> {
	            MissedPaymentDto dto = new MissedPaymentDto();
	            dto.setId(mp.getId());
	            dto.setEmiAmount(mp.getEmiAmount());
	            dto.setDueDate(mp.getDueDate());
	            dto.setPaid(mp.isPaid());
	            dto.setDeleted(mp.isDeleted());
	            return dto;
	        }).toList();

	        // 3) Build PageResponse
	        PageResponse<MissedPaymentDto> response = new PageResponse<>();
	        response.setTotalElements(mpPage.getTotalElements());
	        response.setTotalPages(mpPage.getTotalPages());
	        response.setPageSize(mpPage.getSize());
	        response.setContents(dtos);
	        response.setLastPage(mpPage.isLast());

	        return response;
	    }
	}