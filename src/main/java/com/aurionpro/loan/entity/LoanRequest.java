package com.aurionpro.loan.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="loanrequests")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanRequest {
	@Id
	@Column(name="loan_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="repayamount")
	private double repayamount;;
	@Column(name="loanamount")
	private double loanamount;
	@Column(name="time")
    private int time;
	@Enumerated(EnumType.STRING)
	@Column(name="loanstatus")
	private Loanstatus loanstatus;
	@Column(name="closed")
	private boolean closed;
	
	
	
	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="user_id")
	private User user;
	

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="officer_id")
	private LoanOfficer loanofficer;
	

	@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name="loanscheme_id")
	private LoanScheme loanscheme;
	
	@OneToMany(mappedBy = "loanrequest", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<Emi> emi;
	
	
	@OneToMany(mappedBy = "loanrequest", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<RejectionRemark> rejectionRemarks;
	
	
	

}
