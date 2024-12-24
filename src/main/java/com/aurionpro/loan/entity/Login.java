package com.aurionpro.loan.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="loginDetails")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Login {
	
	
	@Id
	@Column(name="login_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	@Column(name="username")
	private String username ;
	@Column(name="password")
	private String password;
	
		
		@ManyToMany(fetch = FetchType.EAGER)
		@JoinTable(joinColumns = @JoinColumn(name="login_id"), 
		inverseJoinColumns = @JoinColumn(name="roleId"))
		private List<Role> roles;
		
		  @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
		    private User  User;
		
		  @OneToOne(mappedBy = "login", cascade = CascadeType.ALL)
		    private LoanOfficer loanOfficer;

}
