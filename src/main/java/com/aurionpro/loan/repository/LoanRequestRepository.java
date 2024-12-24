package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanRequest;

public interface LoanRequestRepository extends JpaRepository<LoanRequest, Integer>{

	 Page<LoanRequest> findByLoanofficerId(int officerId, Pageable pageable);

}
