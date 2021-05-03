package com.dummyblog.app.service.impl;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummyblog.app.domain.dto.UserDto;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public UserDto getUser(String email) {
		Objects.requireNonNull(email, "Email is a required parameter.");
		return modelMapper.map(userRepository.findByEmail(email), UserDto.class);
	}

}
