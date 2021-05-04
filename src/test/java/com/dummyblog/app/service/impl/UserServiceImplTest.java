package com.dummyblog.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.dummyblog.app.domain.dto.UserDto;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.User;

public class UserServiceImplTest {

	private static final String EMAIL = "email";

	private UserServiceImpl underTest;
	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		userRepository = Mockito.mock(UserRepository.class);
		modelMapper = Mockito.mock(ModelMapper.class);
		underTest = new UserServiceImpl(userRepository, modelMapper);
	}

	@Test
	public void testGetUserShouldThrowNullPointerExceptionWhenTheParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getUser(null));

		// Then
		Mockito.verifyNoMoreInteractions(userRepository, modelMapper);
	}

	@Test
	public void testGetUserShouldReturnAUserDtoWhenTheUserExists() {
		// Given
		UserDto expected = Mockito.mock(UserDto.class);
		User user = Mockito.mock(User.class);
		Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(user);
		Mockito.when(modelMapper.map(user, UserDto.class)).thenReturn(expected);

		// When
		UserDto actual = underTest.getUser(EMAIL);

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(userRepository).findByEmail(EMAIL);
		Mockito.verify(modelMapper).map(user, UserDto.class);
		Mockito.verifyNoMoreInteractions(userRepository, modelMapper);
	}

	@Test
	public void testGetUserShouldThrowNullPointerExceptionWhenTheUserDoesNotExists() {
		// Given
		Mockito.when(userRepository.findByEmail(EMAIL)).thenReturn(null);

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getUser(EMAIL));

		// Then
		Mockito.verify(userRepository).findByEmail(EMAIL);
		Mockito.verifyNoMoreInteractions(userRepository, modelMapper);
	}

}
