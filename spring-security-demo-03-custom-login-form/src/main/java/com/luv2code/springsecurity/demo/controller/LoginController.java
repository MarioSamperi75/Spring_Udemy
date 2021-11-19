package com.luv2code.springsecurity.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class LoginController {
	
	@GetMapping("/showMyLoginPage")
	public String showMyLoginPage() {
		
		return "plain-login";
	}

}
