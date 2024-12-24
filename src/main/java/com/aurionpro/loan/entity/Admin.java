package com.aurionpro.loan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="admins")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Admin {
	
@Id
@Column(name="admin_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name="firstname")
private String firstName;
@Column(name="lastname")
private String lastName;

	
}
