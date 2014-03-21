package com.cam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import net.sf.json.JSONObject;

import com.cam.entity.support.TreeLocation;
import com.evalua.entity.support.EntityBase;

@Entity
public class UserMedicalProfile extends EntityBase{
	
	private User user;
	private int bloodPressure;
	private int missedMedication;
	private int energyExpenditure;
	private int saltIntake;
	private TreeLocation bloodPressureTreeLocation;
	private TreeLocation missedMedicationTreeLocation;
	private TreeLocation energyExpenditureTreeLocation;
	private TreeLocation saltIntakeTreeLocation;
	
	
	
	public TreeLocation getBloodPressureTreeLocation() {
		return bloodPressureTreeLocation;
	}
	public void setBloodPressureTreeLocation(TreeLocation bloodPressureTreeLocation) {
		this.bloodPressureTreeLocation = bloodPressureTreeLocation;
	}
	public TreeLocation getMissedMedicationTreeLocation() {
		return missedMedicationTreeLocation;
	}
	public void setMissedMedicationTreeLocation(
			TreeLocation missedMedicationTreeLocation) {
		this.missedMedicationTreeLocation = missedMedicationTreeLocation;
	}
	public TreeLocation getEnergyExpenditureTreeLocation() {
		return energyExpenditureTreeLocation;
	}
	public void setEnergyExpenditureTreeLocation(
			TreeLocation energyExpenditureTreeLocation) {
		this.energyExpenditureTreeLocation = energyExpenditureTreeLocation;
	}
	public TreeLocation getSaltIntakeTreeLocation() {
		return saltIntakeTreeLocation;
	}
	public void setSaltIntakeTreeLocation(TreeLocation saltIntakeTreeLocation) {
		this.saltIntakeTreeLocation = saltIntakeTreeLocation;
	}	
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getBloodPressure() {
		return bloodPressure;
	}
	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	public int getMissedMedication() {
		return missedMedication;
	}
	public void setMissedMedication(int missedMedication) {
		this.missedMedication = missedMedication;
	}
	public int getEnergyExpenditure() {
		return energyExpenditure;
	}
	public void setEnergyExpenditure(int energyExpenditure) {
		this.energyExpenditure = energyExpenditure;
	}
	public int getSaltIntake() {
		return saltIntake;
	}
	public void setSaltIntake(int saltIntake) {
		this.saltIntake = saltIntake;
	}
	public JSONObject toJSON(){
		JSONObject jsonObject =new JSONObject();
		jsonObject.put("bloodPressure", this.bloodPressure);
		jsonObject.put("bloodPressureTreeLocation", this.bloodPressureTreeLocation);
		jsonObject.put("energyExpenditure", this.energyExpenditure);
		jsonObject.put("energyExpenditureTreeLocation", this.energyExpenditureTreeLocation);
		jsonObject.put("missedMedication", this.missedMedication);
		jsonObject.put("missedMedicationTreeLocation", this.missedMedicationTreeLocation);
		jsonObject.put("saltIntake", this.saltIntake);
		jsonObject.put("saltIntakeTreeLocation", this.saltIntakeTreeLocation);
		jsonObject.put("userId",this.user.getId());
		
		return jsonObject;
	}

}
