package com.aurionpro.loan.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Column(name="loginId")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int loginId;
@Column(name="email")
private String email;
@Column(name="password")
private String password;

@ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
private Role role;

}
