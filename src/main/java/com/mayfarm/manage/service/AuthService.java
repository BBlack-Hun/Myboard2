package com.mayfarm.manage.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mayfarm.manage.dao.AuthDAO;
import com.mayfarm.manage.dto.AuthDTO;

@Service
public class AuthService {
	
	@Inject
	private AuthDAO dao;
	
	@Inject
	private BCryptPasswordEncoder bcyBCryptPasswordEncoder;

	public void register(AuthDTO authDTO) {
		
		String encodePassword = bcyBCryptPasswordEncoder.encode(authDTO.getPassword());
		authDTO.setPassword(encodePassword);
		dao.register(authDTO);		
	}

}
