package com.aurionpro.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Integer>{
	Optional<Login> findByUsername(String username);
	boolean existsByUsername(String username);
}
