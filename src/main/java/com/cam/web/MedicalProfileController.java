package com.cam.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import thep.paillier.exceptions.BigIntegerClassNotValid;

import com.cam.entity.BranchingProgram;
import com.cam.entity.Company;
import com.cam.entity.ProgramBranch;
import com.cam.entity.ProgramBranchSolutions;
import com.cam.entity.User;
import com.cam.entity.UserMedicalProfile;
import com.cam.entity.support.Repository;
import com.cam.entity.support.TreeLocation;
import com.cam.utility.EncryptionUtility;
import com.cam.utility.MedicalProfileTheshold;
import com.cam.utility.UserUtility;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class MedicalProfileController {

	@Resource
	private UserUtility userUtility;

	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;
	
	@Resource
	private EncryptionUtility encryptionUtility;

	String traversePath="";

	@RequestMapping("/medical/addMedicalProfile")
	public ModelAndView addMedicalProfile(HttpServletRequest request) throws BigIntegerClassNotValid{	    

		ModelAndView mv=new ModelAndView("json-string");
		//		User user=userUtility.verifyUser(request.getParameter("auth_token"));
		User user=repository.findUserById(new Long(request.getParameter("id")));
		System.out.println(request.getParameter("saltIntake") +" "+request.getParameter("missedMedication")+" "+request.getParameter("energyExpenditure")+" "+request.getParameter("booldPressure"));
		MedicalProfileTheshold threshold=new MedicalProfileTheshold();
		UserMedicalProfile medicalProfile=repository.findMedicalProfileByUser(user);
		if(medicalProfile==null){
			medicalProfile=new UserMedicalProfile();
		}		
		
		medicalProfile.setBloodPressure(new Integer(request.getParameter("booldPressure")));
		medicalProfile.setEnergyExpenditure(new Integer(request.getParameter("energyExpenditure")));
		medicalProfile.setMissedMedication(new Integer(request.getParameter("missedMedication")));
		medicalProfile.setSaltIntake(new Integer(request.getParameter("saltIntake")));
		medicalProfile.setBooldGroup(request.getParameter("bloodGroup"));
		medicalProfile.setSuger(new Integer(request.getParameter("suger")));
		medicalProfile.setHeartBeats(new Integer(request.getParameter("heartBeats")));
		medicalProfile.setNormalDiet(new Boolean(request.getParameter("normalDiet")));
		medicalProfile.setPhysicalActivity(new Boolean(request.getParameter("physicalActivity")));
		medicalProfile.setUser(user);

		Company company=repository.findCompanyById(new Long(request.getParameter("companyId")));
		String finalString=processMedicalProfile(medicalProfile, threshold,company);
		String encFinalString=encryptionUtility.encriptString(finalString, user.getPrivateKey());
		String encTra=encryptionUtility.encriptString(traversePath, user.getPrivateKey());

		dataStoreManager.save(medicalProfile);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("profile", medicalProfile.toJSON());
		jsonObject.put("threshold", threshold.toJSON());
		jsonObject.put("result", finalString);
		jsonObject.put("enc", encFinalString);
		jsonObject.put("encTra", encTra);
		jsonObject.put("traversePath", traversePath);		

		mv.addObject("data", jsonObject);
		return mv;
	}

	@RequestMapping("/medical/profile")
	public ModelAndView getMedicalProfile(HttpServletRequest request,@RequestParam Long id) throws BigIntegerClassNotValid{

		ModelAndView mv=new ModelAndView("json-string");
		//		User user=userUtility.verifyUser(request.getParameter("auth_token"));
		UserMedicalProfile medicalProfile=repository.findMedicalProfileById(id);
		System.out.println(request.getParameter("saltIntake") +" "+request.getParameter("missedMedication")+" "+request.getParameter("energyExpenditure")+" "+request.getParameter("booldPressure"));
		MedicalProfileTheshold threshold=new MedicalProfileTheshold();

		dataStoreManager.save(medicalProfile);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("profile", medicalProfile.toJSON());
		jsonObject.put("threshold", threshold.toJSON());		

		mv.addObject("data", jsonObject);
		return mv;
	}



	@RequestMapping("/medical/addMedicalProfile/encrypt")
	public ModelAndView addMedicalProfileEncrypted(HttpServletRequest request) throws BigIntegerClassNotValid{

		traversePath="";
		ModelAndView mv=new ModelAndView("json-string");
		//		User user=userUtility.verifyUser(request.getParameter("auth_token"));
		User user=repository.findUserById(new Long(request.getParameter("id")));
		System.out.println(request.getParameter("saltIntake") +" "+request.getParameter("missedMedication")+" "+request.getParameter("energyExpenditure")+" "+request.getParameter("booldPressure"));
		MedicalProfileTheshold threshold=new MedicalProfileTheshold();
		UserMedicalProfile medicalProfile=new UserMedicalProfile();
		medicalProfile.setBloodPressure(new Integer(request.getParameter("booldPressure")));
		medicalProfile.setEnergyExpenditure(new Integer(request.getParameter("energyExpenditure")));
		medicalProfile.setMissedMedication(new Integer(request.getParameter("missedMedication")));
		medicalProfile.setSaltIntake(new Integer(request.getParameter("saltIntake")));
		medicalProfile.setUser(user);
		String result=processMedicalProfile(medicalProfile, threshold,null);
		dataStoreManager.save(medicalProfile);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("profile", medicalProfile.toJSONEncrypted());
		jsonObject.put("threshold", threshold.toJSONEncrypted());
		jsonObject.put("result",result);
		jsonObject.put("traversePath", traversePath);
		mv.addObject("data", jsonObject);
		return mv;
	}

	private String processMedicalProfile(UserMedicalProfile medicalProfile, MedicalProfileTheshold threshold,Company company){

		BranchingProgram branchingProgram=repository.findBranchingProgramByCompany(company);
		if(branchingProgram!=null){
			List<ProgramBranch> branches=branchingProgram.getBranches();
			ProgramBranch rootBranch=null;

			//Select root branch
			for (ProgramBranch programBranch : branches) {
				if(programBranch.getIsRoot()!=null && programBranch.getIsRoot()){
					rootBranch=programBranch;
					break;
				}
			}

			if(rootBranch==null){
				return null;
			}

			//Start processing
			String finalString=traverseThroughTree(rootBranch,medicalProfile,threshold,branchingProgram);
			return finalString;
		}		
		return null;
	}


	private String traverseThroughTree(ProgramBranch branch,UserMedicalProfile medicalProfile,MedicalProfileTheshold threshold,BranchingProgram branchingProgram){
		String branchName=branch.getName();
		traversePath=traversePath+"  "+branchName+" ------>>";
		System.out.println("### traverse    *** "+traversePath);
		String resultString="";
		TreeLocation nextSide=null;
		if(branch.getIsLeaf()!=null && branch.getIsLeaf()){
			List<ProgramBranchSolutions> programBranchSolutions=repository.listProgramBranchSolutionsByBranch(branch);
			for (ProgramBranchSolutions programBranchSolution : programBranchSolutions) {
				resultString=resultString+" "+programBranchSolution.getSolution().getName()+" : "+programBranchSolution.getSolution().getDescription()+" ,";
			}
			System.out.println(" Finale RESULT : "+resultString);
			return resultString;
		}else{

			if(branchName.indexOf(("bloodPressure"))>-1){
				nextSide = getTreeLocation(medicalProfile.getBloodPressure(),threshold.getBloodPressure());
			}else if(branchName.indexOf("missedMedication")>-1){
				nextSide = getTreeLocation(medicalProfile.getMissedMedication(),threshold.getMissedMedication());
			}else if(branchName.indexOf("energyExpenditure")>-1){
				nextSide = getTreeLocation(medicalProfile.getEnergyExpenditure(),threshold.getEnergyExpenditure());
			}else if(branchName.indexOf("saltIntake")>-1){
				nextSide = getTreeLocation(medicalProfile.getSaltIntake(),threshold.getSaltIntake());
			}else if(branchName.indexOf("suger")>-1){
				nextSide = getTreeLocation(medicalProfile.getSuger(),threshold.getSuger());
			}else if(branchName.indexOf("heartBeats")>-1){
				nextSide= getTreeLocation(medicalProfile.getHeartBeats(),threshold.getHeartBeats());
			}else if(branchName.indexOf("physicalActivity")>-1){
				nextSide= getTreeLocation(medicalProfile.getPhysicalActivity());
			}else if(branchName.indexOf("normalDiet")>-1){
				nextSide= getTreeLocation(medicalProfile.getNormalDiet());
			}

			ProgramBranch nextNode=null;
			String nextBranchName="";
			if(nextSide==TreeLocation.LEFT){
				nextBranchName=branch.getLeftNode();	
				System.out.println("left");
			}else{
				nextBranchName=branch.getRightNode();
				System.out.println("right");
			}
			System.out.println("&&&& "+nextBranchName);

			List<ProgramBranch> branches=branchingProgram.getBranches();
			for (ProgramBranch programBranch : branches) {
				if(nextBranchName.equals(programBranch.getName())){
					nextNode=programBranch;
					break;
				}
			}
			resultString=resultString+traverseThroughTree(nextNode, medicalProfile, threshold,branchingProgram);			
		}
		return resultString;
	}

	private TreeLocation getTreeLocation(int original, int threshold){

		if(original<=threshold){
			return TreeLocation.LEFT;
		}else{
			return TreeLocation.RIGHT;
		}

	}

	private TreeLocation getTreeLocation(boolean original){

		System.out.println("BOOLEAN "+original);
		if(original){
			return TreeLocation.LEFT;
		}else{
			return TreeLocation.RIGHT;
		}

	}
}
