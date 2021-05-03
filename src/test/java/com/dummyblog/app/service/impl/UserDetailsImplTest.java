package com.dummyblog.app.service.impl;

import java.util.Collection;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.dummyblog.app.repository.entity.User;

public class UserDetailsImplTest {

	private UserDetailsImpl underTest;
	private User user;

	@BeforeEach
	public void init() {
		user = Mockito.mock(User.class);
		underTest = new UserDetailsImpl(user);
	}

	@Test
	public void testGetAuthoritiesShouldAddAnAuthority() {
		// Given
		Set<? extends GrantedAuthority> expected = Set.of(new SimpleGrantedAuthority("USER"));
		Mockito.when(user.getRole()).thenReturn(User.Privileges.USER);

		// When
		Collection<? extends GrantedAuthority> actual = underTest.getAuthorities();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(user).getRole();
		Mockito.verifyNoMoreInteractions(user);
	}

	@Test
	public void testGetPasswordSchouldCallUser() {
		// Given
		String expected = new String();
		Mockito.when(user.getPassword()).thenReturn(expected);

		// When
		String actual = underTest.getPassword();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(user).getPassword();
		Mockito.verifyNoMoreInteractions(user);
	}

	@Test
	public void testGetUsernameSchouldCallUser() {
		// Given
		String expected = new String();
		Mockito.when(user.getEmail()).thenReturn(expected);

		// When
		String actual = underTest.getUsername();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(user).getEmail();
		Mockito.verifyNoMoreInteractions(user);
	}

	@Test
	public void testIsAccountNonExpiredSchouldReturnTrue() {
		// Given
		Boolean expected = true;

		// When
		Boolean actual = underTest.isAccountNonExpired();

		// Then
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testIsAccountNonLockedSchouldReturnTrue() {
		// Given
		Boolean expected = true;

		// When
		Boolean actual = underTest.isAccountNonLocked();

		// Then
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testIsCredentialsNonExpiredSchouldReturnTrue() {
		// Given
		Boolean expected = true;

		// When
		Boolean actual = underTest.isCredentialsNonExpired();

		// Then
		Assertions.assertEquals(expected, actual);
	}

	@Test
	public void testIsEnabledSchouldReturnTrue() {
		// Given
		Boolean expected = true;

		// When
		Boolean actual = underTest.isEnabled();

		// Then
		Assertions.assertEquals(expected, actual);
	}

}
