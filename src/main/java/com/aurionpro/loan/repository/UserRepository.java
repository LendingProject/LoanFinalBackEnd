package com.aurionpro.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
