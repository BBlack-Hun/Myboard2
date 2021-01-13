package com.mayfarm.manage.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mayfarm.manage.dto.AuthDTO;
import com.mayfarm.manage.service.AuthService;

@Controller
@RequestMapping(value="/manage")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	
	@Inject
	AuthService service;
	
	@GetMapping(value="/login")
	public void login() {
		logger.info("move to loginPage");
	}
	
	// 회원가입 폼
	@RequestMapping(value= "/registerView", method = RequestMethod.GET)
	public void RegisterPage() throws Exception {
		logger.info("registerView");
	}
	
	// 회원가입 폼 작성
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String Register(AuthDTO authDTO) throws Exception {
		logger.info("register");
		
		service.register(authDTO);
		return "redirect:/manage/login";
		
	}
}
