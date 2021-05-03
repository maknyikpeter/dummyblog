package com.dummyblog.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dummyblog.app.repository.UserRepository;

public class RegistrationServiceImplTest {

	private RegistrationServiceImpl underTest;
	private UserRepository userRepository = Mockito.mock(UserRepository.class);
	private PasswordEncoder passwordEncoder = Mockito.mock(PasswordEncoder.class);
	private ModelMapper modelMapper = Mockito.mock(ModelMapper.class);

	@BeforeEach
	public void init() {
		underTest = new RegistrationServiceImpl(passwordEncoder, userRepository, modelMapper);
	}

	@Test
	public void testRegisterUserShouldThrowNullPointerExceptionWhenTheUserDtoParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.registerUser(null));

		// Then
		Mockito.verifyNoMoreInteractions(passwordEncoder);
		Mockito.verifyNoMoreInteractions(userRepository);
	}

}
