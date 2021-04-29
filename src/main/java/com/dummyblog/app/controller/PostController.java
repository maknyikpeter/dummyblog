package com.dummyblog.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
}
