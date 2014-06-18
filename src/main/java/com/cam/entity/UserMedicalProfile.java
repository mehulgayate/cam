package com.cam.entity;

import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import thep.paillier.EncryptedInteger;
import thep.paillier.PrivateKey;
import thep.paillier.PublicKey;
import thep.paillier.exceptions.BigIntegerClassNotValid;

import net.sf.json.JSONObject;

import com.cam.entity.support.TreeLocation;
import com.cam.utility.EncryptionUtility;
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
	private String booldGroup;
	private Integer suger;
	private Integer heartBeats;
	private Boolean physicalActivity;
	private Boolean normalDiet;
	
	
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
	
	public String getBooldGroup() {
		return booldGroup;
	}
	public void setBooldGroup(String booldGroup) {
		this.booldGroup = booldGroup;
	}
	public Integer getSuger() {
		return suger;
	}
	public void setSuger(Integer suger) {
		this.suger = suger;
	}
	public Integer getHeartBeats() {
		return heartBeats;
	}
	public void setHeartBeats(Integer heartBeats) {
		this.heartBeats = heartBeats;
	}	
	
	public Boolean getPhysicalActivity() {
		return physicalActivity;
	}
	public void setPhysicalActivity(Boolean physicalActivity) {
		this.physicalActivity = physicalActivity;
	}
	public Boolean getNormalDiet() {
		return normalDiet;
	}
	public void setNormalDiet(Boolean normalDiet) {
		this.normalDiet = normalDiet;
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
	
	public JSONObject toJSONEncrypted(){
		JSONObject jsonObject =new JSONObject();
		PublicKey pubkey = EncryptionUtility.privkey.getPublicKey();
		try {
			jsonObject.put("bloodPressure", new EncryptedInteger(new BigInteger(this.bloodPressure+""), pubkey).getCipherVal());
		
		jsonObject.put("bloodPressureTreeLocation", new EncryptedInteger(new BigInteger(this.bloodPressureTreeLocation.getInteger()+""), pubkey).getCipherVal());
		jsonObject.put("energyExpenditure", new EncryptedInteger(new BigInteger(this.energyExpenditure+""), pubkey).getCipherVal());
		jsonObject.put("energyExpenditureTreeLocation", new EncryptedInteger(new BigInteger(this.energyExpenditureTreeLocation.getInteger()+""), pubkey).getCipherVal());
		jsonObject.put("missedMedication", new EncryptedInteger(new BigInteger(this.missedMedication+""), pubkey).getCipherVal());
		jsonObject.put("missedMedicationTreeLocation", new EncryptedInteger(new BigInteger(this.missedMedicationTreeLocation.getInteger()+""), pubkey).getCipherVal());
		jsonObject.put("saltIntake", new EncryptedInteger(new BigInteger(this.saltIntake+""), pubkey).getCipherVal());
		jsonObject.put("saltIntakeTreeLocation", new EncryptedInteger(new BigInteger(this.saltIntakeTreeLocation.getInteger()+""), pubkey).getCipherVal());
		jsonObject.put("userId",this.user.getId());
		jsonObject.put("id",this.getId());
		
		} catch (BigIntegerClassNotValid e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonObject;
	}

}
