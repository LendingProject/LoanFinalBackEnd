package com.aurionpro.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

}
