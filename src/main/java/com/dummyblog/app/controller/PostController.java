package com.dummyblog.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dummyblog.app.domain.Post;

@Controller
public class PostController {
	
	private static final List<Post> POST_LIST = List.of(new Post("Próba cím 1", "Próba alcím 1","Próba tartalom 1"), new Post("Próba cím 2", "Próba alcím 2", "Próba tartalom 2"));

	@RequestMapping("/")
	public String index(Model model) {
		model.addAttribute("posts",POST_LIST);
		return "posts";
	}
	
	@RequestMapping("/post/{id}")
	public String getPost(@PathVariable(value = "id") Integer id, Model model) throws Exception {
		if (id == null)
			throw new Exception("Wrong id!");
		model.addAttribute("post", POST_LIST.get(id));
		return "post";
	}
}
