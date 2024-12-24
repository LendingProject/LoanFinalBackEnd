package com.aurionpro.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.loan.entity.RequiredDocuments;

public interface RequiredDocumentsRepository extends JpaRepository<RequiredDocuments, Integer> {

}
