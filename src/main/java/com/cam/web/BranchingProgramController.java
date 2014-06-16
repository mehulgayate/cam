package com.cam.web;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.BranchingProgram;
import com.cam.entity.Company;
import com.cam.entity.support.Repository;
import com.cam.mvc.FileUploadForm;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class BranchingProgramController {
	
	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;
	
	
	@RequestMapping("/company/upload-new")
	public ModelAndView uploadNew(HttpSession session,@ModelAttribute FileUploadForm fileUploadForm) throws IOException{
		ModelAndView mv=new ModelAndView("upload-complete");
		Company company=(Company) session.getAttribute("user");
		BranchingProgram branchingProgram=new BranchingProgram();
		branchingProgram.setFileName(fileUploadForm.getXmlFile().getOriginalFilename());
		
		branchingProgram.setCompany(company);		
		branchingProgram.setFile(Hibernate.getLobCreator(repository.getSession()).createBlob(fileUploadForm.getXmlFile().getBytes()));
		
		dataStoreManager.save(branchingProgram);
		return mv;
	}

	
	@RequestMapping("/company/upload")
	public ModelAndView showNewUpload(){
		ModelAndView mv=new ModelAndView("upload");
			
		return mv;
	}
}
