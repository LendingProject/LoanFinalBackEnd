package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanRequest;
import com.aurionpro.loan.entity.Loanstatus;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer>{

	 Page<LoanRequest> findByLoanofficerId(int officerId, Pageable pageable);
	 Page<LoanRequest> findByLoanscheme_Schemename(Pageable pageable, String schemeName);
	   LoanRequest findByUser_Email( String schemeName);
	    Page<LoanRequest> findByLoanstatus(Loanstatus loanstatus, Pageable pageable);

	    Page<LoanRequest> findByLoanscheme_SchemenameAndLoanstatus(String loanschemeName, Loanstatus loanstatus, Pageable pageable);
	    Page<LoanRequest> findByLoanstatusAndClosed(Loanstatus loanstatus, boolean closed, Pageable pageable);

}
