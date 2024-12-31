package com.aurionpro.loan.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.NPA;
import com.aurionpro.loan.entity.User;

public interface NpaRespository extends JpaRepository<NPA, Integer>{
	  // Find the NPA record by user and not deleted
    NPA findByUserAndDeletedFalse(User user);
    
    List<NPA> findByDeletedFalse();
    Page<NPA> findByDeletedFalse(Pageable pageable);
}
