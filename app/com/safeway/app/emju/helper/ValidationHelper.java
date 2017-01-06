/* **************************************************************************
 * Copyright 2015 Albertsons Safeway.
 *
 * This document/file contains proprietary data that is the property of
 * Albertsons Safeway.  Information contained herein may not be used,
 * copied or disclosed in whole or in part except as permitted by a
 * written agreement signed by an officer of Albertsons Safeway.
 *
 * Unauthorized use, copying or other reproduction of this document/file
 * is prohibited by law.
 *
 ***************************************************************************/

package com.safeway.app.emju.helper;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;

/* ***************************************************************************
 * NAME         : ValidationHelper.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Sep 16, 2015 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 *
 * @author ahani00
 */
@SuppressWarnings("rawtypes")
public class ValidationHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationHelper.class);
    // email pattern
 	private static final String EMAIL_PATTERN = ".+@.+\\.[a-z]+";
 	private static final int EMAIL_MAX_LENGTH = 256;

    private ValidationHelper() {}
    
    /**
     * 
     * @param startDt
     * @param endDt
     * @param theDate
     * @return boolean true or false
     * @throws ParseException
     */
    public static boolean isDateInRange(Date startDt, Date endDt, Date theDate) throws ParseException {
		//theDate in range?
		if ( theDate.compareTo(startDt) == 0 ||
				theDate.compareTo(endDt) == 0 ||
					(theDate.after(startDt) && theDate.before(endDt))
			) {			
			return true; 
		} else {
			return false;
		}
    }
    /**
     * @param startDt
     * @param endDt
     * @param clientTimeZone
     * @return boolean true or false
     * @throws ParseException
     */
    public static boolean isTimeZoneInRange(Date startDt, Date endDt, String clientTimeZone) throws ParseException {
		//Get CurrentDt from clientTimeZone  			    			
        Long clientDBCurrDtInMS = DataHelper.getCurrTsInTzAsDBTzMs(clientTimeZone);
		Date currentDt = new java.sql.Date(clientDBCurrDtInMS);		
		//CurrentDt in range?
		if ( currentDt.compareTo(startDt) == 0 ||
				currentDt.compareTo(endDt) == 0 ||
					(currentDt.after(startDt) && currentDt.before(endDt))
			) {			
			return true; 
		} else {
			return false;
		}
    }
    
    public static boolean isEmpty(final String value) {
        if (value == null) {
            return true;
        }
        else {
            return value.trim().isEmpty();
        }
    }

    public static boolean isNonEmpty(final String value) {
        return !isEmpty(value);
    }

    public static boolean isEmpty(final Collection values) {
        return (values == null) || values.isEmpty();
    }

    public static boolean isEmptyArray(final Object[] objArray) {
        return (objArray == null) || (objArray.length == 0);
    }

    public static boolean isNonEmpty(final Collection values) {
        return !isEmpty(values);
    }

    public static boolean isEmpty(final Map values) {
        return (values == null) || values.isEmpty();
    }

    public static boolean isNonEmpty(final Map values) {
        return !isEmpty(values);
    }

    public static boolean isNullOrEmptyArray(final Object[] inputArray) {
        boolean isNullOrEmpty = false;
        if ((inputArray == null) || (inputArray.length == 0)) {
            isNullOrEmpty = true;
        }
        return isNullOrEmpty;
    }

    public static boolean isNonEmptyArray(final Object[] inputArray) {
        return !isNullOrEmptyArray(inputArray);
    }

    public static boolean hasValidLength(final String value, final int maxLength) {
        boolean hasValidLength = true;
        if (value != null) {
            hasValidLength = value.trim().length() <= maxLength;
        }
        return hasValidLength;
    }

    public static boolean isNumber(final String input) {
        boolean isNumber = true;
        try {
            Double.parseDouble(input);
        }
        catch (Exception e) {
            LOGGER.warn("isNumber : " + e.getMessage());
            isNumber = false;
        }
        return isNumber;
    }

    public static boolean isLong(final String input) {
        boolean isLong = true;
        try {
            Long.parseLong(input);
        }
        catch (Exception e) {
            LOGGER.warn("isLong : " + e.getMessage());
            isLong = false;
        }
        return isLong;
    }

    public static boolean isInteger(final String input) {
        boolean isInteger = true;
        try {
            Integer.parseInt(input);
        }
        catch (Exception e) {
            LOGGER.warn("isInteger : " + e.getMessage());
            isInteger = false;
        }
        return isInteger;
    }

    public static boolean isSame(final Object obj1, final Object obj2, final boolean matchClass) {
        boolean isEquals = true;
        if (obj1 != obj2) {
            if (obj1 == null) {
                isEquals = false;
            }
            else if (matchClass) {
                isEquals = obj1.getClass() == obj2.getClass();
            }
            if (isEquals) {
                if (obj1.getClass().isArray()) {
                    isEquals = Arrays.deepEquals((Object[]) obj1, (Object[]) obj2);
                }
                else {
                    isEquals = obj1.equals(obj2);
                }
            }
        }
        return isEquals;
    }

    public static String trimLengthInBytes(final String inputText, final int maxLength) {
        String resultText = inputText;
        byte[] utf8Bytes = null;
        try {
            if (resultText != null) {
                utf8Bytes = resultText.getBytes("UTF-8");
                if (utf8Bytes.length > maxLength) {
                    LOGGER.info("Length exceeds maximum length. Truncating " + resultText);
                    resultText = resultText.substring(0,
                        (resultText.length() > maxLength) ? maxLength : resultText.length());
                    utf8Bytes = resultText.getBytes("UTF-8");
                    int i = utf8Bytes.length;
                    while (i > maxLength) {
                        resultText = resultText.substring(0, resultText.length() - 1);
                        utf8Bytes = resultText.getBytes("UTF-8");
                        i = utf8Bytes.length;
                    }
                }
            }
        }
        catch (Exception e1) {
            LOGGER.error("UnsupportedEncodingException while trimming the string: " + resultText);
        }
        return resultText;
    }

    public static String trimForMaxLength(String input,final int maxLength){

        if(!hasValidLength(input, maxLength)){
            input = input.substring(0,maxLength);
        }
        return input;
    }
    
    public static boolean validateEmail(String email) {
		boolean isValid = false;
		Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
		Matcher m = emailPattern.matcher(email);
		if ((email.length() <= EMAIL_MAX_LENGTH) && (m.matches())) {
			isValid = true;
		}
		return isValid;
	}
}
