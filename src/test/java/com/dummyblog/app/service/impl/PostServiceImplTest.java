package com.dummyblog.app.service.impl;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.dummyblog.app.domain.dto.PostDto;
import com.dummyblog.app.repository.PostRepository;
import com.dummyblog.app.repository.UserRepository;
import com.dummyblog.app.repository.entity.Post;

public class PostServiceImplTest {

	private static final String THE_TITLE = "The title";

	private PostServiceImpl underTest;
	private PostRepository postRepository;
	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@BeforeEach
	public void init() {
		postRepository = Mockito.mock(PostRepository.class);
		userRepository = Mockito.mock(UserRepository.class);
		modelMapper = Mockito.mock(ModelMapper.class);
		underTest = new PostServiceImpl(postRepository, userRepository, modelMapper);
	}

	@Test
	public void testGetPostsShouldCallPostRepository() {
		// Given
		PostDto postDto1 = Mockito.mock(PostDto.class);
		PostDto postDto2 = Mockito.mock(PostDto.class);
		List<PostDto> expected = List.of(postDto1, postDto2);
		Post post1 = Mockito.mock(Post.class);
		Post post2 = Mockito.mock(Post.class);
		Mockito.when(postRepository.findAll()).thenReturn(List.of(post1, post2));
		Mockito.when(modelMapper.map(post1, PostDto.class)).thenReturn(postDto1);
		Mockito.when(modelMapper.map(post2, PostDto.class)).thenReturn(postDto2);

		// When
		List<PostDto> actual = underTest.getPosts();

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(postRepository).findAll();
		Mockito.verify(modelMapper).map(post1, PostDto.class);
		Mockito.verify(modelMapper).map(post2, PostDto.class);
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper, postDto1, postDto2, post1, post2);
	}

	@Test
	public void testGetPostShouldReturnAPostDtoWhenThePostExists() {
		// Given
		PostDto expected = Mockito.mock(PostDto.class);
		Post post = Mockito.mock(Post.class);
		Mockito.when(postRepository.findByTitle(THE_TITLE)).thenReturn(post);
		Mockito.when(modelMapper.map(post, PostDto.class)).thenReturn(expected);

		// When
		PostDto actual = underTest.getPost(THE_TITLE);

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(postRepository).findByTitle(THE_TITLE);
		Mockito.verify(modelMapper).map(post, PostDto.class);
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper, post, expected);
	}

	@Test
	public void testGetPostShouldThrowNullPointerExceptionWhenTheTitleParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getPost(null));

		// Then
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper);
	}

	@Test
	public void testGetPostShouldThrowNullPointerExceptionWhenThePostDoesNotExists() {
		// Given
		Mockito.when(postRepository.findByTitle(THE_TITLE)).thenThrow(NullPointerException.class);

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getPost(THE_TITLE));

		// Then
		Mockito.verify(postRepository).findByTitle(THE_TITLE);
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper);
	}

	@Test
	public void testCreatePostShouldThrowNullPointerExceptionWhenThePostDtoParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.createPost(null, "email"));

		// Then
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper);
	}

	@Test
	public void testCreatePostShouldThrowNullPointerExceptionWhenBothParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.createPost(null, null));

		// Then
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper);
	}

	@Test
	public void testCreatePostShouldThrowNullPointerExceptionWhenTheEmailParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class,	() -> underTest.createPost(Mockito.mock(PostDto.class), null));

		// Then
		Mockito.verifyNoMoreInteractions(postRepository);
	}

	@Test
	public void testGetAllPostShouldThrowNullPointerExceptionWhenTheUserDoesNotExist() {
		// Given
		Mockito.when(postRepository.findByUserId((long) 1)).thenThrow(NullPointerException.class);

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getAllPost((long) 1));

		// Then
		Mockito.verify(postRepository).findByUserId((long) 1);
		Mockito.verifyNoMoreInteractions(postRepository, userRepository, modelMapper);
	}

	@Test
	public void testGetAllPostShouldThrowNullPointerExceptionWhenTheIdParameterIsNull() {
		// Given

		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getAllPost(null));

		// Then
		Mockito.verifyNoMoreInteractions(postRepository);
	}

	@Test
	public void testGetAllPostShouldReturnAPostListWhenTheIdExists() {
		// Given
		PostDto postDto1 = Mockito.mock(PostDto.class);
		PostDto postDto2 = Mockito.mock(PostDto.class);
		List<PostDto> expected = List.of(postDto1, postDto2);
		Post post1 = Mockito.mock(Post.class);
		Post post2 = Mockito.mock(Post.class);
		Mockito.when(postRepository.findByUserId((long) 1)).thenReturn(List.of(post1, post2));
		Mockito.when(modelMapper.map(post1, PostDto.class)).thenReturn(postDto1);
		Mockito.when(modelMapper.map(post2, PostDto.class)).thenReturn(postDto2);

		// When
		List<PostDto> actual = underTest.getAllPost((long) 1);

		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(postRepository).findByUserId((long) 1);
		Mockito.verify(modelMapper).map(post1, PostDto.class);
		Mockito.verify(modelMapper).map(post2, PostDto.class);
		Mockito.verifyNoMoreInteractions(postRepository, modelMapper, postDto1, postDto2, post1, post2);
	}

}
