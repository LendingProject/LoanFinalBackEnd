package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.MissedPayment;
import com.aurionpro.loan.entity.User;

public interface MissedPayementRepository extends JpaRepository<MissedPayment, Long>{
	 // Find all missed payments for a specific user that are not paid or not deleted
    List<MissedPayment> findByUserAndIsPaidFalseAndDeletedFalse(User user);
    
    
    Page<MissedPayment> findByUserIdAndDeletedFalse(int userId, Pageable pageable);

}
