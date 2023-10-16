package org.lessons.java.spring.auth.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {
	private SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();

	@GetMapping("/login")
	public String login() {
		return "auth/login.html";
	}

	@PostMapping("/logout")
	public void requestLogout(Authentication authentication, HttpServletRequest request,
			HttpServletResponse response) {
		this.logoutHandler.logout(request, response, authentication);
	}
	
	@GetMapping("/forbidden")
	public String forbidden() {
		return "auth/forbidden.html";
	}
}
