package com.safeway.app.emju.util;

import play.Configuration;

public class GeneralUtil {
	
	public static int getIntValueFromConfig(Configuration config, String key, int defaultValue){
		Integer value;
		try{
			value = config.getInt(key);
		}
		catch(Exception e){
			value = null;
		}
		if(null == value){
			value = defaultValue;
		}
		
		return value;
	}
}
