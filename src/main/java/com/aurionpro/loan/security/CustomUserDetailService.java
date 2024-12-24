package com.aurionpro.loan.security;



import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.entity.Login;
import com.aurionpro.loan.repository.LoginRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{
	@Autowired
	private LoginRepository loginRepo;
	
	// Mapping the User TO UserDetails
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Login loginuser = loginRepo.findByUsername(username).orElseThrow(
				()-> new UsernameNotFoundException(username));
		
		Set<GrantedAuthority> authorities = loginuser
				.getRoles()
				.stream()
				.map((role)-> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toSet());  
		return new org.springframework.security.core.userdetails.User(loginuser.getUsername(), loginuser.getPassword(), 
			    authorities);
	}

}
