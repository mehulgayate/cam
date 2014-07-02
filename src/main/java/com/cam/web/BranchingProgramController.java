package com.cam.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.swing.text.StyledEditorKit.ItalicAction;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.ProgramBranch;
import com.cam.entity.BranchingProgram;
import com.cam.entity.Company;
import com.cam.entity.ProgramBranchSolutions;
import com.cam.entity.Solution;
import com.cam.entity.support.Repository;
import com.cam.mvc.FileUploadForm;
import com.cam.utility.EncryptionUtility;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class BranchingProgramController {

	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private EncryptionUtility encryptionUtility;


	@RequestMapping("/company/upload-new")
	public ModelAndView uploadNew(HttpSession session,@ModelAttribute FileUploadForm fileUploadForm) throws IOException{
		ModelAndView mv=new ModelAndView("upload-complete");

		Long userId=(Long) session.getAttribute("userId");
		if(userId==null){
			return new ModelAndView("redirect:/company/login");
		}
		Company company=(Company) repository.findCompanyById(userId);
		
		if(company==null){
			return new ModelAndView("redirect:/company/login");
		}
		
		BranchingProgram branchingProgram=repository.findBranchingProgramByCompany(company);
		
		if(branchingProgram==null){
			branchingProgram=new BranchingProgram();
		}
		List<ProgramBranch>branches=branchingProgram.getBranches();
		List<ProgramBranch> tobeDeleteProgramBranch=new ArrayList<ProgramBranch>();
		
		Iterator<ProgramBranch> iterator=branches.iterator();
		while(iterator.hasNext()){
			ProgramBranch branch=iterator.next();
			List<ProgramBranchSolutions> programBranchsSolutions= repository.listProgramBranchSolutionsByBranch(branch);
			for (ProgramBranchSolutions programBranchSolution : programBranchsSolutions) {
				dataStoreManager.remove(programBranchSolution);
			}
			tobeDeleteProgramBranch.add(branch);
			iterator.remove();
		}
			
		
		dataStoreManager.save(branchingProgram);
		
		for (ProgramBranch programBranch : tobeDeleteProgramBranch) {			
			ProgramBranch programBranch2=repository.findProgramBranchById(programBranch.getId());
			dataStoreManager.remove(programBranch2);
		}
		
		branchingProgram.setFileName(fileUploadForm.getFile().getOriginalFilename());

		branchingProgram.setCompany(company);		
		branchingProgram.setFile(Hibernate.getLobCreator(repository.getSession()).createBlob(fileUploadForm.getFile().getBytes()));
		String jsonString=getStringFromInputStream(fileUploadForm.getFile().getInputStream());
		
		
		JSONObject jsonObject=JSONObject.fromObject(encryptionUtility.decryptString(jsonString,company.getPrivateKey()));
		
		JSONArray soluationsArray=jsonObject.getJSONArray("solutions");
		
		for(int i=0;i<soluationsArray.size();i++){
			JSONObject solutionJson=soluationsArray.getJSONObject(i);
			Solution solution=repository.findSolutionByName(solutionJson.getString("name"));
			if(solution==null){
				solution=new Solution();
				solution.setName(solutionJson.getString("name"));
			}
			solution.setDescription(solutionJson.getString("description"));
			dataStoreManager.save(solution);
		}
		
		
		
		JSONArray array=jsonObject.getJSONArray("branches");
		
		
		for(int i=0;i<array.size();i++){
			JSONObject branchJson=array.getJSONObject(i);
			ProgramBranch branch=new ProgramBranch();
			
			Boolean isLeaf=branchJson.getBoolean("isLeaf");
			if(branchJson.has("isRoot")){
				branch.setIsRoot(branchJson.getBoolean("isRoot"));
			}
			branch.setName(branchJson.getString("name"));
			branch.setIsLeaf(isLeaf);
			if(!isLeaf){
				branch.setLeftNode(branchJson.getString("left"));
				branch.setRightNode(branchJson.getString("right"));
				dataStoreManager.save(branch);				
			}else{
				dataStoreManager.save(branch);
				JSONArray solutionsJsonArray=branchJson.getJSONArray("solutions");
				
				for(int j=0;j<solutionsJsonArray.size();j++){					
					Solution solution=repository.findSolutionByName(solutionsJsonArray.getString(j));					
					ProgramBranchSolutions programBranchSolutions=new ProgramBranchSolutions();
					programBranchSolutions.setBranch(branch);
					programBranchSolutions.setSolution(solution);					
					dataStoreManager.save(programBranchSolutions);
				}
			}			
			
			branchingProgram.getBranches().add(branch);
		}		
		dataStoreManager.save(branchingProgram);
		return mv;
	}


	@RequestMapping("/company/upload")
	public ModelAndView showNewUpload(){
		ModelAndView mv=new ModelAndView("upload");

		return mv;
	}
	
	@RequestMapping("/company/encrypt")
	public ModelAndView showEncrypt(){
		ModelAndView mv=new ModelAndView("encrypt");

		return mv;
	}
	
	// convert InputStream to String
		private static String getStringFromInputStream(InputStream is) {
	 
			BufferedReader br = null;
			StringBuilder sb = new StringBuilder();
	 
			String line;
			try {
	 
				br = new BufferedReader(new InputStreamReader(is));
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
	 
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	 
			return sb.toString();
	 
		}
}
