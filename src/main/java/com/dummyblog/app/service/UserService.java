package com.dummyblog.app.service;

import com.dummyblog.app.domain.dto.UserDto;

public interface UserService {

	public UserDto getUser(String email);

}
