package com.dummyblog.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dummyblog.app.repository.UserRepository;

public class UserDetailsServiceImplTest {

	private UserDetailsServiceImpl underTest;
	private UserRepository userRepository;

	@BeforeEach
	public void init() {
		userRepository = Mockito.mock(UserRepository.class);
		underTest = new UserDetailsServiceImpl(userRepository);
	}

	@Test
	public void testLoadUserByUsernameSchouldThrowUsernameNotFoundExceptionWhenTheParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername(null));

		// Then
		Mockito.verify(userRepository).findByEmail(null);
		Mockito.verifyNoMoreInteractions(userRepository);
	}

}
