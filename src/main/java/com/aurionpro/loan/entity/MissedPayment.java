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
@Table(name = "missed_payments")
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class MissedPayment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "missed_payment_id")
	    private Long id;

	    @Column(name = "emi_amount")
	    private double emiAmount;

	    @Column(name = "due_date")
	    private Date dueDate;

	    @Column(name = "is_paid")
	    private boolean isPaid;

	    
	    @Column(name = "deleted")
	    private boolean deleted;

	    @ManyToOne
	    @JoinColumn(name = "loan_id")
	    private LoanRequest loanRequest;

	   
	    @ManyToOne
	    @JoinColumn(name = "user_id")
	    private User user;
	}