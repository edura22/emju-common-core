package com.safeway.app.emju.exception;

import java.util.HashMap;
import java.util.Map;

public class MobileException extends Exception {
	
	private static final long serialVersionUID = -2642529551009831966L;
	
	Map<String, ErrorDescriptor[]> errorMap = new HashMap<String, ErrorDescriptor[]>(1);
	
	public MobileException(String msg){
		super(msg);
	}
	
	public MobileException (Throwable t){
		super(t);
		ErrorDescriptor errors;
		
		if(t instanceof ApplicationException){
			ApplicationException ap = (ApplicationException)t;
			errors = new ErrorDescriptor(ap.getFaultCode().getCode(), ap.getFaultCode().getDescription());
		}else{
			errors = new ErrorDescriptor("MOBE001", t.getMessage());
		}
		
		errorMap.put("errors", new ErrorDescriptor[] { errors });
	}
	
	public Map<String, ErrorDescriptor[]> getErrorMap() {
		return errorMap;
	}

}
