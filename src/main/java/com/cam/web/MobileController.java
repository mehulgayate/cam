package com.cam.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.User;
import com.cam.entity.support.Repository;
import com.cam.web.support.UserService;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class MobileController {

	@Resource
	private UserService userService;
	
	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private Repository repository;	
	
	
	@RequestMapping("/mobile")
	public ModelAndView companyLogin(){
		ModelAndView mv=new ModelAndView("mobile-index");
		
		return mv;
	}	
	
	@RequestMapping("/mobile/encrypted-data")
	public ModelAndView encData(HttpSession session){
		User user =(User) session.getAttribute("user");
		ModelAndView mv=new ModelAndView("encrypted-data").addObject("userId", user.getId());
		
		return mv;
	}
	
	@RequestMapping("/mobile/expences")
	public ModelAndView expences(HttpSession session){
		User user =(User) session.getAttribute("user");
		ModelAndView mv=new ModelAndView("expenses").addObject("userId", user.getId());
		
		return mv;
	}
}
