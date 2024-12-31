package com.aurionpro.loan.entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="emis")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Emi {
	
	@Id
	@Column(name="emi_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	@Column(name="amount")
	private double amount;
	@Column(name="date")
	private Date date;
  
	@ManyToOne
	@JoinColumn(name="loan_id")
	private LoanRequest loanrequest;
}
