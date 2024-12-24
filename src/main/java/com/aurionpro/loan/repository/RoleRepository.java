package com.aurionpro.loan.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRole(String role);
}
