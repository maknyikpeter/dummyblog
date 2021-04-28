package com.dummyblog.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.dummyblog.app.domain.dto.PostDto;
import com.dummyblog.app.repository.PostRepository;
import com.dummyblog.app.repository.entity.Post;

public class PostServiceImplTest {

	private static final String THE_TITLE = "The title";

	private PostServiceImpl underTest;
	private PostRepository postRepository;
	private ModelMapper modelMapper;

	@BeforeEach
	public void init() {
	    postRepository = Mockito.mock(PostRepository.class);
	    modelMapper = Mockito.mock(ModelMapper.class);
		underTest = new PostServiceImpl(postRepository, modelMapper);
	}

	@Test
	public void testGetPostsShouldCallPostRepository() {
		// Given
		List<PostDto> expected = List.of(new Post(), new Post()).stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		Mockito.when(postRepository.findAll().stream().map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList())).thenReturn(expected);
		
		// When
		List<PostDto> actual = underTest.getPosts();
		
		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(postRepository).findAll();
		Mockito.verifyNoMoreInteractions(postRepository);
	}

	@Test
	public void testGetPostShouldThrowNullPointerExceptionWhenTheTitleParameterIsNull() {
		// Given
		
		// When
		Assertions.assertThrows(NullPointerException.class, () -> underTest.getPost(null));
		
		// Then
		Mockito.verifyNoMoreInteractions(postRepository);
	}

	@Test
	public void testGetPostShouldReturnAPostDtoWhenThePostExists() {
		// Given
		PostDto expected = Mockito.mock(PostDto.class);
		Mockito.when(modelMapper.map(postRepository.findByTitle(THE_TITLE), PostDto.class)).thenReturn(expected);
		
		// When
		PostDto actual = underTest.getPost(THE_TITLE);
		
		// Then
		Assertions.assertEquals(expected, actual);
		Mockito.verify(postRepository);
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

}
