package com.safeway.app.emju.mobile.exception;

import com.safeway.app.emju.mobile.exception.MobileErrors;
import com.safeway.app.emju.mobile.exception.MobileException;

public class TransformException extends MobileException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransformException(MobileErrors error, String field) {
		super(error.getCode(), error.getDescription().replace("{1}", field));
	}
}