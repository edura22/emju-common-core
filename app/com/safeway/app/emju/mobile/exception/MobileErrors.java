package com.safeway.app.emju.mobile.exception;

public enum MobileErrors {

	NIMBUS_API("MAPI0001","Nimbus API Error: Check the logs"),
	TRASFORMATION("MAPI0002","{1} is missing or invalid.");
	
	private String code;
	private String description;
	
	private MobileErrors(String code,String description){
		this.code=code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	
	
	
	
}
