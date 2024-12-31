package com.aurionpro.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.Emi;

public interface EmiRepository extends JpaRepository<Emi, Long> {

}
