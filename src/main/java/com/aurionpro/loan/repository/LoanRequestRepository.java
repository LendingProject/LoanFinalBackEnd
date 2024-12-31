package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aurionpro.loan.dto.LoanRequestDetailsDTO;
import com.aurionpro.loan.dto.LoanRequestReportDto;
import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.Loanstatus;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer>{

	 Page<LoanRequest> findByLoanofficerId(int officerId, Pageable pageable);
	 Page<LoanRequest> findByLoanscheme_Schemename(Pageable pageable, String schemeName);
	   LoanRequest findByUser_Email( String schemeName);
	    Page<LoanRequest> findByLoanstatus(Loanstatus loanstatus, Pageable pageable);

	    Page<LoanRequest> findByLoanscheme_SchemenameAndLoanstatus(String loanschemeName, Loanstatus loanstatus, Pageable pageable);
	    Page<LoanRequest> findByLoanstatusAndClosed(Loanstatus loanstatus, boolean closed, Pageable pageable);

	    @Query("SELECT lr.loanamount, lr.time, ls.schemename, u.firstName, u.lastName, lo.firstName, lo.lastName " +
	            "FROM LoanRequest lr " +
	            "JOIN lr.loanscheme ls " +
	            "JOIN lr.user u " +
	            "JOIN lr.loanofficer lo " +
	            "WHERE lr.closed = false") 
	    Page<Object[]> findLoanRequestDetails(Pageable pageable);
	    
	    
	    // Count loan requests by status (approved, rejected, pending)
	    @Query("SELECT COUNT(lr) FROM LoanRequest lr WHERE lr.loanofficer.id = :officerId AND lr.loanstatus = :status")
	    Long countLoanRequestsByStatus(@Param("officerId") int officerId, @Param("status") Loanstatus status);
	    
	    @Query("SELECT new com.aurionpro.loan.dto.LoanRequestReportDto(lr.loanamount, ls.schemename, u.firstName, u.lastName) "
	            + "FROM LoanRequest lr "
	            + "JOIN lr.loanscheme ls "
	            + "JOIN lr.user u "
	            + "WHERE lr.loanofficer.id = :officerId AND lr.loanstatus = :status")
	       List<LoanRequestReportDto> findLoanRequestsByStatus(int officerId, Loanstatus status);
	    
	  
//	        
//	        @Query("SELECT new com.aurionpro.loan.dto.LoanRequestDetailsDTO(u.firstName, u.lastName, ls.schemename, lr.repayamount, lr.loanstatus) " +
//	               "FROM LoanRequest lr " +
//	               "JOIN lr.user u " +
//	               "JOIN lr.loanscheme ls " +
//	               "WHERE lr.closed = false")
//	        Page<LoanRequestDetailsDTO> findLoanRequestDetails1(Pageable pageable);
	    
	    
	    @Query("SELECT new com.aurionpro.loan.dto.LoanRequestDetailsDTO(" +
	            "lr.user.firstName, " +
	            "lr.user.lastName, " +
	            "lr.loanamount, " +
	            "lr.loanscheme.schemename, " +
	            "lr.time) " +
	            "FROM LoanRequest lr " +
	            "WHERE lr.closed = false")
	    Page<LoanRequestDetailsDTO> findLoanRequestDetails1(Pageable pageable);
	    }


