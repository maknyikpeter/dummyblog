package com.dummyblog.app.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.dummyblog.app.domain.Post;

@Controller
public class PostController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView ret = new ModelAndView("posts");
		List<Post> postList = List.of(new Post("Próba cím 1", "Próba alcím 1"), new Post("Próba cím 2", "Próba alcím 2"));
		ret.addObject("posts", postList);
		return ret;
	}
}
