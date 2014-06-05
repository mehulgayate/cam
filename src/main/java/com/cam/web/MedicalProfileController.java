package com.cam.web;

import java.math.BigInteger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import thep.paillier.EncryptedInteger;
import thep.paillier.PrivateKey;
import thep.paillier.PublicKey;
import thep.paillier.exceptions.BigIntegerClassNotValid;

import com.cam.entity.User;
import com.cam.entity.UserMedicalProfile;
import com.cam.entity.support.DataTransaction;
import com.cam.entity.support.Repository;
import com.cam.entity.support.TreeLocation;
import com.cam.utility.MedicalProfileTheshold;
import com.cam.utility.UserUtility;
import com.cam.web.support.UserService;
import com.evalua.entity.support.DataStoreManager;

@Controller
public class MedicalProfileController {

	@Resource
	private UserUtility userUtility;
	
	@Resource
	private Repository repository;

	@Resource
	private DataStoreManager dataStoreManager;

	@RequestMapping("/medical/addMedicalProfile")
	public ModelAndView addMedicalProfile(HttpServletRequest request) throws BigIntegerClassNotValid{
 
	    
	    
	    
	    

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
		processMedicalProfile(medicalProfile, threshold);
		dataStoreManager.save(medicalProfile);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("profile", medicalProfile.toJSON());
		jsonObject.put("threshold", threshold.toJSON());		

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
		processMedicalProfile(medicalProfile, threshold);
		dataStoreManager.save(medicalProfile);
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("profile", medicalProfile.toJSONEncrypted());
		jsonObject.put("threshold", threshold.toJSONEncrypted());
		
		
		mv.addObject("data", jsonObject);
		return mv;
	}

	private void processMedicalProfile(UserMedicalProfile medicalProfile, MedicalProfileTheshold threshold){

		
		medicalProfile.setBloodPressureTreeLocation(getTreeLocation(medicalProfile.getBloodPressure(), threshold.getBloodPressure()));
		medicalProfile.setEnergyExpenditureTreeLocation(getTreeLocation(medicalProfile.getEnergyExpenditure(), threshold.getEnergyExpenditure()));
		medicalProfile.setMissedMedicationTreeLocation(getTreeLocation(medicalProfile.getMissedMedication(), threshold.getMissedMedication()));
		medicalProfile.setSaltIntakeTreeLocation(getTreeLocation(medicalProfile.getSaltIntake(), threshold.getSaltIntake()));
	}
	
	private TreeLocation getTreeLocation(int original, int threshold){
		
		if(original<=threshold){
			return TreeLocation.LEFT;
		}else{
			return TreeLocation.RIGHT;
		}
		
	}
}
