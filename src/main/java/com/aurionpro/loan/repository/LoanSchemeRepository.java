package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanScheme;

public interface LoanSchemeRepository extends JpaRepository<LoanScheme, Integer>{
	//List<LoanScheme> findBySchemeName(String schemeName);
}
