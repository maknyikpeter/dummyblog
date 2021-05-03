package com.dummyblog.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dummyblog.app.domain.dto.UserDto;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.User;
import com.dummyblog.app.repository.entity.User.Privileges;
import com.dummyblog.app.service.RegistrationService;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	private static final Privileges USER_ROLE = Privileges.USER;

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@Autowired
	public RegistrationServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository, ModelMapper modelMapper) {
		this.passwordEncoder = passwordEncoder;
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public void registerUser(UserDto userDto) {
		User user = modelMapper.map(userDto, User.class);
		user.setRole(USER_ROLE);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
