package com.dummyblog.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView ret = new ModelAndView("index");
		ret.addObject("name", "world!");
		return ret;
	}
}
