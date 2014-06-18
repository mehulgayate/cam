package com.cam.entity;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.evalua.entity.support.EntityBase;

@Entity
public class BranchingProgram extends EntityBase{
	
	private String fileName;
	private Company company;
	private Blob file;
	
	private List<ProgramBranch> branches=new ArrayList<ProgramBranch>(0);
	
	@Column(length=5242880)
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public Company getCompany() {
		return company;
	}
	public void setCompany(Company company) {
		this.company = company;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.DETACH }, fetch = FetchType.EAGER)
	public List<ProgramBranch> getBranches() {
		return branches;
	}
	public void setBranches(List<ProgramBranch> branches) {
		this.branches = branches;
	}

}
