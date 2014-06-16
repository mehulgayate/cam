package com.cam.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.Company;
import com.cam.entity.User;
import com.cam.entity.support.Repository;
import com.cam.entity.support.UserForm;
import com.cam.web.support.UserService;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class UserController {

	@Resource
	private UserService userService;
	
	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private Repository repository;
	
	@RequestMapping("/register")
	public ModelAndView registerNewUser(HttpServletRequest request,@ModelAttribute(UserForm.key) UserForm userForm){
		ModelAndView mv=new ModelAndView("json-string");
		userService.addUser(userForm);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("success", "success");
		mv.addObject("response", jsonObject);
		return mv;
	}
	
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,@RequestParam String email,@RequestParam String password){
		ModelAndView mv=new ModelAndView("json-string");
		JSONObject jsonObject=new JSONObject();	
		User user=userService.authenticate(email,password);
		if(user!=null){
			jsonObject.put("success", "success");
			jsonObject.put("id", user.getId());
		}else{
			jsonObject.put("fail", "fail");
		}
		mv.addObject("response", jsonObject);
		return mv;
	}
	
	@RequestMapping("/page")
	public ModelAndView display(){
		ModelAndView mv=new ModelAndView("get-expenses");
		
		return mv;
	}
	
	@RequestMapping("/company/login")
	public ModelAndView companyLogin(){
		ModelAndView mv=new ModelAndView("login");
		
		return mv;
	}
	
	@RequestMapping("/company/authenticate")
	public ModelAndView authenticatCompanyLogin(HttpSession session,@RequestParam String email,@RequestParam String password){
		ModelAndView mv=new ModelAndView("redirect:/company/upload/");
		Company company=repository.findCompanyByEmail(email);
		if(company==null || !company.getPassword().equals(password)){
			return new ModelAndView("login");
		}
		session.setAttribute("user",company);
		return mv;
	}
	
	@RequestMapping("/company/register")
	public ModelAndView companyRegister(){
		ModelAndView mv=new ModelAndView("signup/signup");
		
		return mv;
	}
	
	@RequestMapping("/company/register/add")
	public ModelAndView companyRegisterAdd(@ModelAttribute Company company){
		ModelAndView mv=new ModelAndView("signup/complete");
		Company company2=repository.findCompanyByEmail(company.getEmail());
		if(company2==null){
			return mv;
		}
		dataStoreManager.save(company);
		return mv;
	}
	
	
}
