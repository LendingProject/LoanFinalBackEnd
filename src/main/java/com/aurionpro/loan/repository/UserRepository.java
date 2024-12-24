package com.aurionpro.loan.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	Page<User> findByFirstName(Pageable pageable,String firstName);
	User findByEmail(String email);
}
