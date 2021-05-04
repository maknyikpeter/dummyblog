package com.dummyblog.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.User;

public class UserDetailsServiceImplTest {

	private static final String EMAIL = "email";

	private UserDetailsServiceImpl underTest;
	private UserRepository userRepository;

	@BeforeEach
	public void init() {
		userRepository = Mockito.mock(UserRepository.class);
		underTest = new UserDetailsServiceImpl(userRepository);
	}

	@Test
	public void testLoadUserByUsernameShouldReturnAUserDetailsImplWhenTheUserExists() {
		// Given
		User user = Mockito.mock(User.class);
		Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(user);
		UserDetails expected = new UserDetailsImpl(user);

		// When
		UserDetails actual = underTest.loadUserByUsername(EMAIL);

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(userRepository).findByEmail(EMAIL);
		Mockito.verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testLoadUserByUsernameShouldThrowUsernameNotFoundExceptionWhenTheUserDoesNotExist() {
		// Given
		Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);

		// When
		Assertions.assertThrows(UsernameNotFoundException.class, () -> underTest.loadUserByUsername(EMAIL));

		// Then
		Mockito.verify(userRepository).findByEmail(EMAIL);
		Mockito.verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testLoadUserByUsernameShouldThrowNullPointerExceptionWhenTheParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.loadUserByUsername(null));

		// Then
		Mockito.verifyNoMoreInteractions(userRepository);
	}

}
