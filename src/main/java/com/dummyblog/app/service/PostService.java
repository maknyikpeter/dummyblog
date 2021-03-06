package com.dummyblog.app.service;

import java.util.List;

import com.dummyblog.app.domain.dto.PostDto;

public interface PostService {

	public List<PostDto> getPosts();

	public PostDto getPost(String title);

	public PostDto createPost(PostDto postDto, String email);

	public List<PostDto> getAllPost(Long id);

}
