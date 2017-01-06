package com.safeway.app.emju.mobile.exception;

import java.util.HashMap;
import java.util.Map;

import com.safeway.app.emju.exception.ApplicationException;
import com.safeway.app.emju.exception.ErrorDescriptor;

public class MobileException extends Exception {
	
	private static final long serialVersionUID = -2642529551009831966L;
	
	private Map<String, ErrorDescriptor[]> errorMap = new HashMap<String, ErrorDescriptor[]>(1);
	private ErrorDescriptor errors;
	
	public MobileException(String  errorCode, String msg) {
		super("Error Code:"+errorCode + " Error Description:"+msg);
		errorMap.clear();
		errors = new ErrorDescriptor(errorCode, msg);
		errorMap.put("errors", new ErrorDescriptor[] { errors });
	}
	
	@Deprecated
	public MobileException(String msg){
		super(msg);
		errorMap.clear();
		errors = new ErrorDescriptor("MOBE001", msg);
		errorMap.put("errors", new ErrorDescriptor[] { errors });
	}
	
	public MobileException (Throwable t){
		super(t);
		errorMap.clear();
		
		if(t instanceof ApplicationException){
			ApplicationException ap = (ApplicationException)t;
			errors = new ErrorDescriptor(ap.getFaultCode().getCode(), ap.getFaultCode().getDescription());
		}else{
			errors = new ErrorDescriptor(MobileErrors.NIMBUS_API.getCode(), t.getMessage());
		}
		
		errorMap.put("errors", new ErrorDescriptor[] { errors });
	}
	
	public Map<String, ErrorDescriptor[]> getErrorMap() {
		return errorMap;
	}

}
