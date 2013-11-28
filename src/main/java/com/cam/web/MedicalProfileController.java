package com.cam.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cam.entity.User;
import com.cam.entity.UserMedicalProfile;
import com.cam.entity.support.DataTransaction;
import com.cam.entity.support.TreeLocation;
import com.cam.utility.MedicalProfileTheshold;
import com.cam.utility.UserUtility;

@Controller
public class MedicalProfileController {

	@Resource
	private UserUtility userUtility;

	@Resource
	private DataTransaction dataTransaction;

	@RequestMapping("/medical/addMedicalProfile")
	public ModelAndView addMedicalProfile(HttpServletRequest request){

		ModelAndView mv=new ModelAndView("MedicalTreeReport");
		User user=userUtility.verifyUser(request.getParameter("auth_token"));
		MedicalProfileTheshold threshold=new MedicalProfileTheshold();
		UserMedicalProfile medicalProfile=new UserMedicalProfile();
		medicalProfile.setBloodPressure(new Integer(request.getParameter("booldPressure")));
		medicalProfile.setEnergyExpenditure(new Integer(request.getParameter("energyExpenditure")));
		medicalProfile.setMissedMedication(new Integer(request.getParameter("missedMedication")));
		medicalProfile.setSaltIntake(new Integer(request.getParameter("saltIntake")));
		medicalProfile.setUser(user);
		processMedicalProfile(medicalProfile, threshold);
		dataTransaction.save(medicalProfile);
		mv.addObject("medicalProfile", medicalProfile);
		mv.addObject("threshold", threshold);
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
