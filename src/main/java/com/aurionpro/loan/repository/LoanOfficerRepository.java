package com.aurionpro.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanOfficer;

public interface LoanOfficerRepository extends JpaRepository<LoanOfficer, Integer> {
	Optional<LoanOfficer> existsByEmail(String email);
}
