package com.dummyblog.app.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.dummyblog.app.domain.dto.PostDto;
import com.dummyblog.app.service.PostService;

@Controller
public class PostController {

	private PostService postService;

	@Autowired
	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping("/")
	public String getAllPosts(Model model) {
		model.addAttribute("posts", postService.getPosts());
		return "posts";
	}

	@GetMapping("/post/{title}")
	public String getPost(@PathVariable(value = "title") String title, Model model) throws Exception {
		if (title == null)
			throw new Exception("There is no story with this title!");
		model.addAttribute("post", postService.getPost(title));
		return "post";
	}

	@GetMapping("/posteditor")
	public String getPostEditor(Model model) {
		model.addAttribute("postDto", new PostDto());
		return "posteditor";

	}

	@PostMapping("/posteditor")
	public String createNewPost(@Valid @ModelAttribute PostDto postDto, Principal principal) {
		String principalName = principal.getName();
		postService.createPost(postDto, principalName);
		return "redirect:/";
	}
	
	@GetMapping("/user/{id}")
	public String getAllPostOfUser(@PathVariable(value = "id") Long id, Model model) throws Exception {
		if (id == null)
			throw new Exception("There is no stories!");
		model.addAttribute("posts", postService.getAllPost(id));
		return "postsOfUser";
	}

}
