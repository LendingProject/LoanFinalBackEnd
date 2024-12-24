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
@Table(name="roles")
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Role {
	@Id
	@Column(name="roleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private int roleId;

	@Column(name="role_name")
	private String role;
	
	


}
