package com.aurionpro.loan.entity;

import java.sql.Date;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="users")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name="firstname")
	private String firstName;
	@Column(name="lastname")
	private String lastName;
	@Column(name="pancardNumber")
	private String pancardNumber;
	@Column(name="dob")
	private Date dob;
	@Column(name="contactNumber")
	private long contactNumber;
	@Column(name="email")
	private String email;
	@Enumerated(EnumType.STRING)
	@Column(name="gender")
	private Gender gender;
	@Column(name="npa")
	private int npa;
	@Column(name="deleted")
	private boolean deleted;
	
	
	
	
	@OneToMany(mappedBy = "user",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	private List<LoanRequest> loanrequest;
	
	 @OneToOne
	    @JoinColumn(name = "login_id", nullable = false)
	    private Login login;
	
	
	
	
	
	
	
	
	
}
