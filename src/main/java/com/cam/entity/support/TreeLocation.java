package com.cam.entity.support;

public enum TreeLocation {
	LEFT,RIGHT;
	
	public Integer getInteger(){
		if(this.toString().equals("LEFT")){
			return 0;
		}else{
			return 1;
		}
		
	}
}
