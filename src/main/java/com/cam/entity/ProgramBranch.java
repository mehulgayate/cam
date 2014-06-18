package com.cam.entity;

import javax.persistence.Entity;
import com.evalua.entity.support.EntityBase;

@Entity
public class ProgramBranch extends EntityBase{

	private String name;
	private String leftNode;
	private String rightNode;
	private Boolean isLeaf;	
	private Boolean isRoot;	

	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLeftNode() {
		return leftNode;
	}

	public void setLeftNode(String left) {
		this.leftNode = left;
	}

	public String getRightNode() {
		return rightNode;
	}

	public void setRightNode(String right) {
		this.rightNode = right;
	}

	public Boolean getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	
}
