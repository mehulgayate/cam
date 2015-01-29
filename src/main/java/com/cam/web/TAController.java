package com.cam.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.User;
import com.cam.entity.UserMedicalProfile;
import com.cam.entity.support.Repository;
import com.cam.web.support.UserService;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class TAController {

	@Resource
	private UserService userService;
	
	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private Repository repository;	
	
	@RequestMapping("/ta")
	public ModelAndView showTa(HttpSession session){
		ModelAndView mv=new ModelAndView("admin/index");
		String isTa= (String) session.getAttribute("isTa");
		if(isTa==null || !isTa.equals("true")){
			return new ModelAndView("redirect:/ta/login");
		}
		
		List<User> users = repository.listUsers();
		mv.addObject("users",users);
		return mv;
	}
	
	@RequestMapping("/ta/login")
	public ModelAndView login(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("admin/login");
		return mv;
	}
	
	@RequestMapping("/ta/authenticate")
	public ModelAndView authenticate(HttpServletRequest request,@RequestParam String email, @RequestParam String password,HttpSession session){
		System.out.println(email+" "+password);
		if(email.equals("taadmin") && password.equals("ta123")){
			session.setAttribute("isTa", "true");
			return new ModelAndView("redirect:/ta");
		}
		
		return new ModelAndView("/ta/login");
	}
	
	@RequestMapping("/ta/logout")
	public ModelAndView logout(HttpServletRequest request,HttpSession session){
		
		session.invalidate();
		
		return new ModelAndView("/ta/login");
	}
	
	@RequestMapping("/ta/show-user")
	public ModelAndView showUser(HttpServletRequest request,@RequestParam Long id){
		ModelAndView mv = new ModelAndView("admin/show-user");
		User user = repository.findUserById(id);
		mv.addObject("user",user);
		UserMedicalProfile medicalProfile=repository.findMedicalProfileByUser(user);
		mv.addObject("medicalProfile",medicalProfile);
		return mv;
	}
	
}
