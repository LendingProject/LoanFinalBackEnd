package com.aurionpro.loan.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.loan.dto.LoginRequestDto;
import com.aurionpro.loan.dto.RegistrationDto;
import com.aurionpro.loan.dto.UserResponseDto;
import com.aurionpro.loan.entity.Login;
import com.aurionpro.loan.entity.Role;
import com.aurionpro.loan.entity.User;
import com.aurionpro.loan.exceptions.UserException;
import com.aurionpro.loan.repository.LoginRepository;
import com.aurionpro.loan.repository.RoleRepository;
import com.aurionpro.loan.security.JwtTokenProvider;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService{
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private LoginRepository loginRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	@Transactional
	public UserResponseDto Register(RegistrationDto registrationDto) {
	if(loginRepository.existsByUsername(registrationDto.getUsername()))
		throw new UserException("Username Already Exist : "+registrationDto.getUsername());
	Login registerUser =  new Login();
	registerUser.setUsername(registrationDto.getUsername());
	registerUser.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
	
	List<Role>  roles  = new ArrayList<>();
	Role role = roleRepository.findByRole("ROLE_CUSTOMER").get();
	roles.add(role);
	registerUser.setRoles(roles);
	
	
	User userDetails = new User();
	userDetails.setContactNumber(registrationDto.getContactNumber());
	userDetails.setFirstName(registrationDto.getFirstName());
	userDetails.setLastName(registrationDto.getLastName());
	userDetails.setDob(registrationDto.getDob());
	userDetails.setPancardNumber(registrationDto.getPancardNumber());
	userDetails.setGender(registrationDto.getGender());
	userDetails.setNpa(0);
	userDetails.setEmail(registrationDto.getUsername());
	userDetails.setDeleted(false);
	
	registerUser.setUser(userDetails);
	userDetails.setLogin(registerUser);
	
	loginRepository.save(registerUser);
	
	UserResponseDto response = new UserResponseDto();
	response.setContactNumber(userDetails.getContactNumber());
	response.setFirstName(userDetails.getFirstName());
	response.setLastName(userDetails.getLastName());
	response.setPancardNumber(userDetails.getPancardNumber());
	response.setGender(userDetails.getGender());
	response.setDob(userDetails.getDob());
	response.setUserId(userDetails.getId());
	response.setEmail(registerUser.getUsername());
	response.setLoginId(registerUser.getUser().getId());
	
	return response;
	}

	@Override
	public String login(LoginRequestDto loginDto) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token =  jwtTokenProvider.generateToken(authentication);
			return token;
		}catch(BadCredentialsException e) {
			throw new UserException("Username or Password is incorrect");
		}
		
	}

}
