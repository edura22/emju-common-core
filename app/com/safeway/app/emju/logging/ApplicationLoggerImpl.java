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

package com.safeway.app.emju.logging;

import java.util.function.Function;

import org.slf4j.LoggerFactory;

import com.safeway.app.emju.exception.FaultCodeBase;

/* ***************************************************************************
 * NAME         : ApplicationLoggerImpl.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Nov 19, 2015 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 * Application logger wrapping over slf4j wrapper to control the data format being logged.
 *
 * @author ahani00
 */
public class ApplicationLoggerImpl implements Logger {

    private org.slf4j.Logger logger;

    /**
     * Constructor
     *
     * @param clazz
     */
    public ApplicationLoggerImpl(final Class clazz) {
        logger = LoggerFactory.getLogger(clazz);
    }

    @Override
    public void debug(final String msg) {
        debug(false, msg);
    }
    
    @Override
    public void debug(Function<Logger, String> func) {
    	if(isDebugEnabled()){
    		debug(false, func.apply(this));
    	}
    }

    @Override
    public void info(Function<Logger, String> func) {
    	if(isInfoEnabled()){
    		info(false, func.apply(this));    		
    	}
    }

    @Override
    public void warn(Function<Logger, String> func) {
        if (logger.isWarnEnabled()) {
        	warn(false, func.apply(this));
        }
    }

    @Override
    public void debug(final String msg, final Throwable t) {
        if (logger.isDebugEnabled()) {
            logger.debug(LogFormatter.formatMsg(null, msg, t, false), t);
        }
    }

    @Override
    public void debug(final boolean isKeyValue, final String... values) {
        if (logger.isDebugEnabled()) {
            logger.debug(LogFormatter.formatMsg(isKeyValue, values));
        }
    }

    @Override
    public void info(final String msg) {
        info(false, msg);
    }

    @Override
    public void info(final String msg, final Throwable t) {
        if (logger.isInfoEnabled()) {
            logger.info(LogFormatter.formatMsg(null, msg, t, false), t);
        }
    }

    @Override
    public void info(final boolean isKeyValue, final String... values) {
        if (logger.isInfoEnabled()) {
            logger.info(LogFormatter.formatMsg(isKeyValue, values));
        }
    }

    @Override
    public void warn(final String msg) {
        warn(false, msg);
    }

    @Override
    public void warn(final boolean isKeyValue, final String... values) {
        if (logger.isWarnEnabled()) {
            logger.warn(LogFormatter.formatMsg(isKeyValue, values));
        }
    }

    @Override
    public void warn(final String msg, final Throwable t) {
        if (logger.isWarnEnabled()) {
            logger.warn(LogFormatter.formatMsg(null, msg, t, false), t);
        }
    }

    @Override
    public void warn(
        final FaultCodeBase faultCode, final String errorMsg, final Throwable t, final boolean isKeyValue,
        final String... values) {

        if (logger.isWarnEnabled()) {
            logger.warn(LogFormatter.formatMsg(faultCode, errorMsg, t, isKeyValue, values), t);
        }
    }

    @Override
    public void error(final String msg) {
        error(false, msg);
    }

    @Override
    public void error(final boolean isKeyValue, final String... values) {
        logger.error(LogFormatter.formatMsg(isKeyValue, values));
    }

    @Override
    public void error(final String msg, final Throwable t) {
        logger.error(LogFormatter.formatMsg(null, msg, t, false), t);
    }

    @Override
    public void error(
        final FaultCodeBase faultCode, final String errorMsg, final Throwable t, final boolean isKeyValue,
        final String... values) {

        logger.error(LogFormatter.formatMsg(faultCode, errorMsg, t, isKeyValue, values), t);
    }

    @Override
    public boolean isDebugEnabled() {
        return logger.isDebugEnabled();
    }

    @Override
    public boolean isInfoEnabled() {
        return logger.isInfoEnabled();
    }
    
    @Override
    public boolean isWarnEnabled() {
    	return logger.isWarnEnabled();
    }
    
    

	@Override
	public void warnAndError(String msg, Throwable t) {
		if(logger.isWarnEnabled()){
			warn(msg, t);			
		}
		else{
			error(msg);			
		}
	}

}

