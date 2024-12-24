package com.aurionpro.loan.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="RequiredDocuments")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class RequiredDocuments {
	@Id
	@Column(name="document_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name="documentOneLink")
	private String documentOneLink;
	@Column(name="documentTwoLink")
	private String documentTwoLink;
	@Column(name="documentThreeLink")
	private String documentThreeLink;
	
	@OneToOne
	@JoinColumn(name="loan_id")
	private LoanRequest loanrequest;
	
	
	
}
