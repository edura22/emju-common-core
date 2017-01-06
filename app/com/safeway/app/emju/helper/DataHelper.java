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

import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;
import com.safeway.app.emju.util.GenericConstants;

/* ***************************************************************************
 * NAME         : DataHelper.java
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
 * @author ahani00
 *
 */
public class DataHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataHelper.class);
    private static final String EMPTY_STRING = "";
    private static final int MAX_ALLOWED_TTL = 630720000;
	private static final int DEFAULT_TTL = 7952400;
	private static final String DEFAULT_TIME_ZONE = "PST";

    private DataHelper() {}

    private enum TimeSetting {
        CLEAR, MAXIMIZE;
    }

    public static Boolean parseBoolean(final String value) {
        Boolean boolValue = false;
        if (ValidationHelper.isNonEmpty(value)) {
            try {
                boolValue = Boolean.parseBoolean(value.trim());
            }
            catch (NumberFormatException nfe) {
                LOGGER.warn(nfe.getMessage(), nfe);
            }
        }
        return boolValue;
    }

    public static Double parseDouble(final String value) {
        Double doubleValue = null;
        if (ValidationHelper.isNonEmpty(value)) {
            try {
                doubleValue = Double.parseDouble(value.trim());
            }
            catch (NumberFormatException nfe) {
                LOGGER.warn(nfe.getMessage(), nfe);
            }
        }
        return doubleValue;
    }

    public static Double parseDouble(final String value, final double defaultValue) {
        Double doubleValue = parseDouble(value);
        return doubleValue != null ? doubleValue : defaultValue;
    }

    public static BigDecimal parseBigDecimal(final String value) {
        BigDecimal bigDecimalValue = null;
        if (ValidationHelper.isNonEmpty(value)) {
            try {
                bigDecimalValue = new BigDecimal(value.trim());
            }
            catch (NumberFormatException nfe) {
                LOGGER.warn(nfe.getMessage(), nfe);
            }
        }
        return bigDecimalValue;
    }

    public static Long parseLong(final String value) {
        Long longValue = null;
        if (ValidationHelper.isNonEmpty(value)) {
            try {
                longValue = Long.parseLong(value.trim());
            }
            catch (NumberFormatException nfe) {
                LOGGER.warn(nfe.getMessage(), nfe);
            }
        }
        return longValue;
    }

    public static Long parseLong(final String value, final long valueIfNull) {
        Long longValue = null;
        longValue = parseLong(value);
        return longValue != null ? longValue : valueIfNull;
    }

    public static Integer parseInt(final String value) {
        Integer intValue = null;
        if (ValidationHelper.isNonEmpty(value)) {
            try {
                intValue = Integer.parseInt(value.trim());
            }
            catch (NumberFormatException nfe) {
                LOGGER.warn(nfe.getMessage(), nfe);
            }
        }
        return intValue;
    }

    public static Integer parseInt(final String value, final int valueIfNull) {
        Integer intValue = parseInt(value);
        return intValue != null ? intValue : valueIfNull;
    }

    public static String getIntAsString(final Integer intValue) {
        return intValue != null ? String.valueOf(intValue) : null;
    }

    public static String getLongAsString(final Long longValue) {
        return longValue != null ? String.valueOf(longValue) : null;
    }

    public static String getDoubleAsString(final Double dblValue) {
        return dblValue != null ? String.valueOf(dblValue) : null;
    }

    public static String getShortISODateTime(final Date inDate) {
        String isoDate = null;
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        if (inDate != null) {
            isoDate = isoDateFormat.format(inDate);
        }
        return isoDate;
    }

    public static String getJSONDate(final Date objDate) {
        return objDate != null ? "\\/Date(" + objDate.getTime() + ")\\/" : null;
    }

    /**
     * Returns a numerical representation of current time in Milliseconds after obtaining the current Calendar instance
     * based on input time zone and setting the same date and time DB time zone
     *
     * @param inTimezone
     *            String
     * @return Long
     */
    public static Long getCurrTsInTzAsDBTzMs(final String inTimezone) {
        Long currTsInTzAsDBTzMs = null;
        if (ValidationHelper.isNonEmpty(inTimezone)) {
            Calendar inCalendar = Calendar.getInstance(TimeZone.getTimeZone(inTimezone));
            currTsInTzAsDBTzMs = getInputTsInTzAsDBTzMs(inTimezone, inCalendar.getTimeInMillis());
        }
        return currTsInTzAsDBTzMs;
    }

    /**
     * Returns a numerical representation of input time in Milliseconds in current timezone translated to input time
     * zone
     *
     * @param inTimeInMs
     *            Long
     * @param inTimezone
     *            String
     * @return Long
     */
    public static Long getInTsInCurrTzAsInTzMs(final Long inTimeInMs, final String inTimezone) {
        Long currTsInTzAsDBTzMs = null;
        if ((inTimeInMs != null) && ValidationHelper.isNonEmpty(inTimezone)) {
            Calendar inCalendar = Calendar.getInstance();
            inCalendar.setTimeInMillis(inTimeInMs);
            inCalendar.setTimeZone(TimeZone.getTimeZone(inTimezone));
            currTsInTzAsDBTzMs = inCalendar.getTimeInMillis();
        }
        return currTsInTzAsDBTzMs;
    }

    public static Long getInputTsInTzAsDBTzMaxedMs(final String inTimezone, final Long inTsInMs) {
        return getInputTsInTzAsDBTzMs(inTimezone, inTsInMs, TimeSetting.MAXIMIZE);
    }

    /**
     * Returns a numerical representation of current time in Milliseconds after obtaining the current Calendar instance
     * based on input time zone and setting the same date and time DB time zone
     *
     * @param inTimezone
     *            String
     * @param inTsInMs
     *            Long
     * @return Long
     */
    public static Long getInputTsInTzAsDBTzMs(final String inTimezone, final Long inTsInMs) {
        return getInputTsInTzAsDBTzMs(inTimezone, inTsInMs, TimeSetting.CLEAR);
    }

    /**
     * Returns a numerical representation of current time in Milliseconds after obtaining the current Calendar instance
     * based on input time zone and setting the same date and time DB time zone
     *
     * @param clientTimezone
     * @param clientDtInMs
     * @param timeSetting
     * @return
     */
    private static Long getInputTsInTzAsDBTzMs(
        final String clientTimezone, final Long clientDtInMs, final TimeSetting timeSetting) {
        Date clientDBDt = null;
        if (clientDtInMs != null) {
            Calendar clientCalendar = Calendar.getInstance(TimeZone.getTimeZone(clientTimezone));
            clientCalendar.setTimeInMillis(clientDtInMs);
            clearTimeFields(clientCalendar);
            Calendar dbCalendar = Calendar.getInstance();
            if (TimeSetting.CLEAR == timeSetting) {
                clearTimeFields(dbCalendar);
            }
            else if (TimeSetting.MAXIMIZE == timeSetting) {
                maximizeTimeFields(dbCalendar);
            }
            dbCalendar.set(Calendar.DAY_OF_MONTH, clientCalendar.get(Calendar.DAY_OF_MONTH));
            dbCalendar.set(Calendar.DATE, clientCalendar.get(Calendar.DATE));
            dbCalendar.set(Calendar.MONTH, clientCalendar.get(Calendar.MONTH));
            dbCalendar.set(Calendar.YEAR, clientCalendar.get(Calendar.YEAR));
            clientDBDt = new Date(dbCalendar.getTimeInMillis());
        }
        return clientDBDt != null ? clientDBDt.getTime() : clientDtInMs;
    }

    /**
     * Clears all the time fields in Calendar object.
     *
     * @param calendar
     *            Calendar
     */
    public static void clearTimeFields(final Calendar calendar) {
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
    }

    public static void maximizeTimeFields(final Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }

    /**
     * Returns the actual trimmed String or empty String if null.
     *
     * @param value
     *            String
     * @return String
     */
    public static String replaceIfNull(final String value) {
        return replaceIfNull(value, EMPTY_STRING);
    }

    /**
     * Returns the actual trimmed String or empty String if null.
     *
     * @param value
     *            String
     * @return String
     */
    public static String replaceIfNull(final String value, final String valueIfNull) {
        return value != null ? value.toString().trim() : valueIfNull;
    }

    /**
     * Returns the actual Integer or 0 if null.
     *
     * @param value
     *            Integer
     * @return Integer
     */
    public static Integer replaceIfNull(final Integer value) {
        return value != null ? value : 0;
    }

    /**
     * Returns the actual trimmed String or empty String if null.
     *
     * @param value
     * @return String
     */
    public static String replaceIfNull(final Object value) {
        return replaceIfNull(value, GenericConstants.EMPTY_STRING);
    }

    /**
     * Returns the actual trimmed String or empty String if null.
     *
     * @param value
     * @return String
     */
    public static String replaceIfNull(final Object value, final String valueIfNull) {
        return value != null ? value.toString().trim() : valueIfNull;
    }

    /**
     * Returns the actual Integer or the input replacement if null.
     *
     * @param value
     *            Integer
     * @param valueIfNull
     *            Integer
     * @return Integer
     */
    public static Integer replaceIfNull(final Integer value, final Integer valueIfNull) {
        return value != null ? value : valueIfNull;
    }

    /**
     * Returns the actual Long or 0 if null.
     *
     * @param value
     *            Long
     * @return Long
     */
    public static Long replaceIfNull(final Long value) {
        return value != null ? value : 0;
    }

    /**
     * Returns the actual Integer or the input replacement if null.
     *
     * @param value
     *            Long
     * @param valueIfNull
     *            Long
     * @return Long
     */
    public static Long replaceIfNull(final Long value, final Long valueIfNull) {
        return value != null ? value : valueIfNull;
    }

    /**
     * The method converts ClipTimeStamp, which is a sting, to XMLGregorianCalendar.
     *
     * @param clipTS
     *            The ClipTimeStamp in milliseconds
     * @param timezone
     *            String
     * @return
     */
    public static XMLGregorianCalendar formatTimeStamp(final String clipTS, final String timezone) {

        XMLGregorianCalendar xmlCal = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        Date date = null;
        try {
            if (ValidationHelper.isNonEmpty(clipTS)) {
                date = formatter.parse(clipTS);
                xmlCal = formatTimeStamp(date, timezone);
            }
        }
        catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
        }

        return xmlCal;
    }

    public static XMLGregorianCalendar formatTimeStamp(final Date clipTs, final String timezone) {

        XMLGregorianCalendar xmlCal = null;
        GregorianCalendar calendar = new GregorianCalendar(TimeZone.getTimeZone(timezone));
        calendar.setTime(clipTs);
        try {
            xmlCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        }
        catch (Exception e) {
            LOGGER.warn(e.getMessage(), e);
        }

        return xmlCal;
    }

    /**
     * Returns a Date object from JSON string.
     *
     * @param jsonDate
     *            - String
     * @return Date
     */
    public static Date getDateFromJSONString(final String jsonDate) {
        if (ValidationHelper.isEmpty(jsonDate)) {
            return null;
        }
        int startIndex = jsonDate.indexOf("(");
        int endIndex = jsonDate.indexOf(")");
        String dateInMilliseconds = jsonDate.substring(startIndex + 1, endIndex);
        return new Date(Long.valueOf(dateInMilliseconds));
    }

    public static Date parseISODate(final String isoDate) {
        Date dateValue = null;
        try {
            if (ValidationHelper.isNonEmpty(isoDate)) {
                String strDate = isoDate.trim();
                // NOTE: SimpleDateFormat uses GMT[-+]hh:mm for the TZ which
                // breaks things a bit. Before we go on we have to repair this.
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");

                // this is UTC / zero time so we need to add that TZ indicator
                if (strDate.endsWith("Z")) {
                    strDate = strDate.substring(0, strDate.length() - 1) + "GMT+00:00";
                }
                else {
                    int tzLength = 6;
                    String s0 = strDate.substring(0, strDate.length() - tzLength);
                    String s1 = strDate.substring(strDate.length() - tzLength, strDate.length());
                    strDate = s0 + "GMT" + s1;
                }
                dateValue = df.parse(strDate);
            }
        }
        catch (ParseException e) {
            LOGGER.info(e.getMessage(), e);
        }
        return dateValue;
    }

    /**
     * Clears all the time fields in Calendar object.
     *
     * @param date
     *            Date
     */
    public static Date getDateWithNoTime(final Date date) {
        Date dateWithNoTime = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR, 0);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            dateWithNoTime = calendar.getTime();
        }
        return dateWithNoTime;
    }

    /**
     * return date with all the time fields set to the maximum possible values
     *
     * @param date
     *            Date
     * @return Date
     */
    public static Date getDateWithMaxedTime(final Date date) {
        Date dateWithMaxedTime = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            calendar.set(Calendar.MILLISECOND, 999);
            dateWithMaxedTime = calendar.getTime();
        }
        return dateWithMaxedTime;
    }

    public static String getISODate(final Date inDate) {
        String isoDate = null;
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (inDate != null) {
            isoDate = isoDateFormat.format(inDate);
        }
        return isoDate;
    }

    public static String getISODateTime(final Date inDate) {
        String isoDate = null;
        SimpleDateFormat isoDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSZ");
        if (inDate != null) {
            isoDate = isoDateFormat.format(inDate);
        }
        return isoDate;
    }

    public static Date getDateInClientTimezone(final String clientTimezone, final Date actualDt) {
        Date clientDt = null;
        if ((actualDt != null) && (clientTimezone != null)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(actualDt);
            Calendar clientCalendar = Calendar.getInstance(TimeZone.getTimeZone(clientTimezone));
            DataHelper.clearTimeFields(clientCalendar);
            clientCalendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH));
            clientCalendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
            clientCalendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            clientCalendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            clientCalendar.set(Calendar.HOUR_OF_DAY, 11);
            clientDt = new Date(clientCalendar.getTimeInMillis());
        }
        return clientDt;
    }

    public static String encrypt(final String value) {
        String sessionKey = null;
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(value.getBytes("UTF-8"), 0, value.length());
            sessionKey = new BigInteger(1, m.digest()).toString(16).toString();

        }
        catch (Exception e) {
            LOGGER.error("Error while processing the session token  ", e);
        }

        return sessionKey;
    }


    public static int getCurrentQuarter(final Date currClientDt) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currClientDt);
        int month = calendar.get(Calendar.MONTH);
        return month/3 + 1;
    }
    
    public static int getTTLsetup(final Date offset, final int months, final int days) {
    	
    	int ttl = DEFAULT_TTL;
		
		Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone(DEFAULT_TIME_ZONE));
		clearTimeFields(currentDate);
		
		Calendar initialDate = Calendar.getInstance();
		initialDate.setTime(offset);
		
		if(months > 0) {
			initialDate.add(Calendar.MONTH, months);
		}
		if(days > 0) {
			initialDate.add(Calendar.DATE, days);
		}
		
		if(initialDate.getTimeInMillis() > currentDate.getTimeInMillis()){
			ttl = new Long((initialDate.getTimeInMillis() - currentDate.getTimeInMillis())/1000).intValue();
			if(ttl > MAX_ALLOWED_TTL){
				ttl = DEFAULT_TTL;
			}
		}
		
		return ttl;
    }

}

