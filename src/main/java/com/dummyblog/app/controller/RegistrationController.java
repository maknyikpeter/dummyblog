package com.dummyblog.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.dummyblog.app.domain.dto.UserDto;
import com.dummyblog.app.service.RegistrationService;

@Controller
public class RegistrationController {

	private RegistrationService registrationService;

	@Autowired
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@GetMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("userDto", new UserDto());
		return "registration";
	}

	@PostMapping("/registration")
	public String registration(@Valid @ModelAttribute UserDto userDto) {
		registrationService.registerUser(userDto);
		return "auth/login";
	}
}
