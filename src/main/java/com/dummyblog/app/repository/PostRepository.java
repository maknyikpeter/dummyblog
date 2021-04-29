package com.dummyblog.app.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dummyblog.app.repository.entity.Post;

public interface PostRepository extends CrudRepository<Post, Long> {

	List<Post> findAll();

	Post findByTitle(String title);

}
