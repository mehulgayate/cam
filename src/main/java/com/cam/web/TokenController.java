package com.cam.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.Token;
import com.cam.entity.User;
import com.cam.entity.support.DataTransaction;
import com.cam.entity.support.Repository;
import com.cam.view.support.Error;

@Controller
public class TokenController {
	
	@Resource
	private Repository repository;
	
	@Resource
	private DataTransaction transaction;
	
	@RequestMapping("/getToken")
	public ModelAndView getToken(HttpServletRequest request){
		ModelAndView mv=new ModelAndView("json-string");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		User user=repository.findUserByUserPass(email, password);
		
		if(user==null){
			Error error=new Error();
			error.setErrorCode(101);
			error.setErrorMassage("Invalid Login");
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("error", error.toJSON());
			mv.addObject("error",jsonObject);
			return mv;
		}
		
		if(user.getToken()!=null){
			JSONObject jsonObject=new JSONObject();
			jsonObject.put("access_token", user.getToken().toJSON());
			mv.addObject("token",jsonObject);
			return mv;
		}
		else{
			JSONObject jsonObject=new JSONObject();
			Token token=transaction.generateToken(user);
			jsonObject.put("access_token", token.toJSON());
			mv.addObject("token",jsonObject);
			return mv;
		}
	}

}
