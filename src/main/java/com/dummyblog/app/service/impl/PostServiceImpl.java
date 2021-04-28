package com.dummyblog.app.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummyblog.app.domain.dto.PostDto;
import com.dummyblog.app.repository.PostRepository;
import com.dummyblog.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper modelMapper;
	
	@Autowired
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<PostDto> getPosts() {
		return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	}
	
	@Override
	public PostDto getPost(String title) {
		Objects.requireNonNull(title, "Title is a required parameter for getting post.");
        return modelMapper.map(postRepository.findByTitle(title), PostDto.class);
	}

}
