package com.cam.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.evalua.entity.support.EntityBase;

@Entity
public class ProgramBranchSolutions extends EntityBase{
	
	private ProgramBranch branch;
	private Solution solution;
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public ProgramBranch getBranch() {
		return branch;
	}
	public void setBranch(ProgramBranch branch) {
		this.branch = branch;
	}
	
	@OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	public Solution getSolution() {
		return solution;
	}
	public void setSolution(Solution solution) {
		this.solution = solution;
	}
	
	

}
