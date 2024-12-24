package com.aurionpro.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.LoanOfficer;

public interface LoanOfficerRepository extends JpaRepository<LoanOfficer, Integer> {

}
