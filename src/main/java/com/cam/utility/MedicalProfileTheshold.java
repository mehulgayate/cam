package com.cam.utility;

import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.cam.entity.support.AllProperties;

public class MedicalProfileTheshold {

	private int bloodPressure=AllProperties.BLOOD_PRESSURE;
	private int missedMedication=AllProperties.MISSED_MEDICATION;
	private int energyExpenditure=AllProperties.ENERGY_EXPENDITURE;
	private int saltIntake=AllProperties.SALT_INTAKE;
	
	
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
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("bloodPressure", this.bloodPressure);
		jsonObject.put("energyExpenditure", this.energyExpenditure);
		jsonObject.put("missedMedication", this.missedMedication);
		jsonObject.put("saltIntake", this.saltIntake);
		
		return jsonObject;
	}
}
