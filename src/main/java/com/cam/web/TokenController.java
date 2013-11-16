package com.cam.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TokenController {
	
	@RequestMapping("/getToken")
	public ModelAndView getToken(){
		ModelAndView mv=new ModelAndView("index");
		
		mv.addObject("message", "Spring 3 MVC Hello World");
		return mv;
	}

}
