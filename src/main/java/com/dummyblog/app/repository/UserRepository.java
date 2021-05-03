package com.dummyblog.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.dummyblog.app.repository.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

	User findByEmail(String email);

}
