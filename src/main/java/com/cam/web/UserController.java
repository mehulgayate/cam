package com.cam.web;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import thep.paillier.PrivateKey;

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
	public ModelAndView login(HttpServletRequest request,@RequestParam String email,@RequestParam String password,HttpSession session){
		ModelAndView mv=new ModelAndView("json-string");
		JSONObject jsonObject=new JSONObject();	
		User user=userService.authenticate(email,password);		
		
		if(user!=null){
			session.setAttribute("user", user);
			byte[] r = new byte[16]; //Means 2048 bit
			Random random=new Random();
			random.nextBytes(r);
			String s = Base64.encodeBase64String(r);			
			user.setPrivateKey(s);
			dataStoreManager.save(user);
			
			jsonObject.put("success", "success");
			jsonObject.put("id", user.getId());
			jsonObject.put("privateKey", user.getPrivateKey());
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
		session.setAttribute("userId",company.getId());
		return mv;
	}
	
	@RequestMapping("/company/register")
	public ModelAndView companyRegister(){
		ModelAndView mv=new ModelAndView("signup/signup");
		
		return mv;
	}
	
	@RequestMapping("/company/register/add")
	public ModelAndView companyRegisterAdd(@ModelAttribute Company company) throws NoSuchAlgorithmException, NoSuchProviderException{
		ModelAndView mv=new ModelAndView("signup/complete");
		Company company2=repository.findCompanyByEmail(company.getEmail());
		if(company2!=null){
			return mv;
		}
		byte[] r = new byte[16]; //Means 2048 bit
		Random random=new Random();
		random.nextBytes(r);
		String s = Base64.encodeBase64String(r);
		company.setPrivateKey(s);
		dataStoreManager.save(company);
		return mv;
	}
	
	@RequestMapping("/list-companies.json")
	public ModelAndView listComanies(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("json-string");
		JSONObject jsonObject=new JSONObject();	
		List<Company> companies =repository.listCompanies();
		for (Company company : companies) {
			jsonObject.put(company.getId().toString(), company.getName());
		}
		mv.addObject("response", jsonObject);
		return mv;
	}
	
	@RequestMapping("/verify-privatekey")
	public ModelAndView verifyPrivateKey(@RequestParam Long id,
			@RequestParam String privateKey){
		ModelAndView mv=new ModelAndView("json-string");
		User user=repository.findUserById(id);
		System.out.println(user.getPrivateKey()+" **** "+privateKey);
		if(user.getPrivateKey().equals(privateKey)){
			mv.addObject("response", true);
		}else{
			mv.addObject("response", false);
		}
		return mv;
	}
	
	
}
