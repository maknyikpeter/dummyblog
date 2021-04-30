package com.dummyblog.app.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummyblog.app.domain.dto.PostDto;
import com.dummyblog.app.repository.PostRepository;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.Post;
import com.dummyblog.app.repository.entity.User;
import com.dummyblog.app.repository.entity.User.Privileges;
import com.dummyblog.app.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository postRepository;
	private ModelMapper modelMapper;
	private UserRepository userRepository;

	@Autowired
	public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper, UserRepository userRepository) {
		this.postRepository = postRepository;
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
	}

	@Override
	public List<PostDto> getPosts() {
		return postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PostDto getPost(String title) {
		Objects.requireNonNull(title, "Title is a required parameter for getting post.");
		return modelMapper.map(postRepository.findByTitle(title), PostDto.class);
	}
//	Long id, Integer age, String email, String name, String password, Privileges role
	@PostConstruct
	public void init() {
		User user = new User(null,3,"elso","1","1",Privileges.USER);
		User user2 = new User(null,3,"masodik","2","2",Privileges.USER);
		userRepository.save(user);
		userRepository.save(user2);
		
		Post post = new Post("Főcím", "Alcím", "Ez itt a poszt tartalma.", user);
		Post post2 = new Post("Főcím2", "Alcím2", "Ez itt a poszt tartalma.2", user2);
		postRepository.save(post);
		postRepository.save(post2);
	}

}
