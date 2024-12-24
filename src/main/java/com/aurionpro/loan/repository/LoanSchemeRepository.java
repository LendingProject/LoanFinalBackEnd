package com.aurionpro.loan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanScheme;

public interface LoanSchemeRepository extends JpaRepository<LoanScheme, Integer>{
	//List<LoanScheme> findBySchemeName(String schemeName);
	Page<LoanScheme> findBySchemename(Pageable pageable ,String schemeName);
}
