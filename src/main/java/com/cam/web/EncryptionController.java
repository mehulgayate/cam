package com.cam.web;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.cam.entity.Company;
import com.cam.entity.support.Repository;
import com.cam.mvc.FileUploadForm;
import com.cam.utility.EncryptionUtility;

@Controller
public class EncryptionController {
	
	@Resource
	private Repository repository;
	
	@Resource
	private EncryptionUtility encryptionUtility;

	@RequestMapping("/company/encrypt-new")
	public ModelAndView encryptProgramm(HttpSession session,@ModelAttribute FileUploadForm fileUploadForm,HttpServletResponse response) throws IOException{
		ModelAndView mv=new ModelAndView("upload-complete");

		Long userId=(Long) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/company/login");
		}
		Company company=(Company) repository.findCompanyById(userId);
		
		if(company==null){
			return new ModelAndView("redirect:/company/login");
		}
		
		
		try {
		      // get your file as InputStream
		      InputStream is = fileUploadForm.getFile().getInputStream();
		      
		      StringWriter writer = new StringWriter();
		      IOUtils.copy(is, writer, "UTF-8");
		      String theString = writer.toString();
		      
		      System.out.println("The string "+theString);
		      String encriptedString=encryptionUtility.encriptString(theString, company.getPrivateKey());
		      
		      
		      // copy it to response's OutputStream
		      IOUtils.copy(new ByteArrayInputStream(encriptedString.getBytes()), response.getOutputStream());
		      
		      response.flushBuffer();
		    } catch (IOException ex) {
		     
		      throw new RuntimeException("IOError writing file to output stream");
		    }
		
		return new ModelAndView();
	}
}
