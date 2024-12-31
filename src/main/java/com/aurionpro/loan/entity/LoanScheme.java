package com.aurionpro.loan.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="loanschemes")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoanScheme {
	@Id
	@Column(name="loanscheme_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	@Column(name="scheme_name")
	private String schemename;
	@Column(name="maxamount")
	private double maxamount;
	@Column(name="minamount")
	private double minamount;
	@Column(name="interest")
	private double interest;
	@Column(name="deleted")
	private boolean isdeleted;
	
	@OneToMany(mappedBy = "loanscheme",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<LoanRequest> loanrequest;
}
