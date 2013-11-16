package com.cam.web;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.Token;
import com.cam.entity.support.Repository;

@Controller
public class TokenController {
	
	@Resource
	private Repository repository;
	
	@RequestMapping("/getToken")
	public ModelAndView getToken(){
		ModelAndView mv=new ModelAndView("json-string");
		Token token=repository.generateToken();
		JSONObject jsonObject=token.toJSON();
				
		mv.addObject("token",jsonObject);
		return mv;
	}

}
