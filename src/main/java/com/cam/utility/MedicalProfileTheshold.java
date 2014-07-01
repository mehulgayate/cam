package com.cam.utility;

import java.math.BigInteger;

import thep.paillier.EncryptedInteger;
import thep.paillier.PublicKey;
import thep.paillier.exceptions.BigIntegerClassNotValid;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import com.cam.entity.support.AllProperties;

public class MedicalProfileTheshold {

	private int bloodPressure=AllProperties.BLOOD_PRESSURE;
	private int missedMedication=AllProperties.MISSED_MEDICATION;
	private int energyExpenditure=AllProperties.ENERGY_EXPENDITURE;
	private int saltIntake=AllProperties.SALT_INTAKE;
	public int heartBeats=AllProperties.HEART_BEATS;
	public int suger=AllProperties.SUGER;	
	
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
	
	public int getHeartBeats() {
		return heartBeats;
	}
	public void setHeartBeats(int heartBeats) {
		this.heartBeats = heartBeats;
	}
	public int getSuger() {
		return suger;
	}
	public void setSuger(int suger) {
		this.suger = suger;
	}
	public JSONObject toJSON(){
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("bloodPressure", this.bloodPressure);
		jsonObject.put("energyExpenditure", this.energyExpenditure);
		jsonObject.put("missedMedication", this.missedMedication);
		jsonObject.put("saltIntake", this.saltIntake);
		
		return jsonObject;
	}
	
	public JSONObject toJSONEncrypted(){
		
		JSONObject jsonObject=new JSONObject();
		PublicKey pubkey = EncryptionUtility.privkey.getPublicKey();
		try {
		jsonObject.put("bloodPressure", new EncryptedInteger(new BigInteger(this.bloodPressure+""), pubkey).getCipherVal());
		jsonObject.put("energyExpenditure", new EncryptedInteger(new BigInteger(this.energyExpenditure+""), pubkey).getCipherVal());
		jsonObject.put("missedMedication", new EncryptedInteger(new BigInteger(this.missedMedication+""), pubkey).getCipherVal());
		jsonObject.put("saltIntake",new EncryptedInteger(new BigInteger(this.saltIntake+""), pubkey).getCipherVal());
		} catch (BigIntegerClassNotValid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}
}
