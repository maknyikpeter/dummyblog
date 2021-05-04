package com.dummyblog.app.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.dummyblog.app.domain.dto.UserDto;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.User;
import com.dummyblog.app.repository.entity.User.Privileges;

public class RegistrationServiceImplTest {

	private RegistrationServiceImpl underTest;
	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		userRepository = Mockito.mock(UserRepository.class);
		passwordEncoder = Mockito.mock(PasswordEncoder.class);
		modelMapper = Mockito.mock(ModelMapper.class);
		underTest = new RegistrationServiceImpl(passwordEncoder, userRepository, modelMapper);
	}

	@Test
	public void testRegisterUserShouldCallTheUserRepositoryWhenTheUserDtoParameterIsNotNull() {
		// Given
		UserDto userDto = new UserDto(null, "name", "password", 1, "email");
		User user = Mockito.mock(User.class);
		Mockito.when(user.getPassword()).thenReturn("password");
		Mockito.when(modelMapper.map(userDto, User.class)).thenReturn(user);
		Mockito.when(passwordEncoder.encode("password")).thenReturn("encryptedPassword");

		// When
		underTest.registerUser(userDto);

		// Then
		Mockito.verify(modelMapper).map(userDto, User.class);
		Mockito.verify(user).setRole(Privileges.USER);
		Mockito.verify(user).getPassword();
		Mockito.verify(user).setPassword("encryptedPassword");
		Mockito.verify(userRepository).save(user);
		Mockito.verify(passwordEncoder).encode("password");
		Mockito.verifyNoMoreInteractions(passwordEncoder, userRepository, modelMapper, user);
	}

	@Test
	public void testRegisterUserShouldThrowNullPointerExceptionWhenTheUserDtoParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.registerUser(null));

		// Then
		Mockito.verifyNoMoreInteractions(passwordEncoder, userRepository, modelMapper);
	}

}
