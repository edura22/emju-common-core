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

import com.safeway.app.emju.exception.FaultCodeBase;

/* ***************************************************************************
 * NAME         : Logger.java
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
 * Application wrapper for logging
 *
 * @author ahani00
 */
public interface Logger {

    void debug(String msg);

    void debug(boolean isKeyValue, String... values);

    void debug(String msg, Throwable t);

    void info(String msg);

    void info(String msg, Throwable t);

    void info(boolean isKeyValue, String... values);

    void warn(String msg);

    void warn(boolean isKeyValue, String... values);

    void warn(String msg, Throwable t);

    void warnAndError(String msg, Throwable t);

    void warn(FaultCodeBase faultCode, String errorMsg, Throwable t, boolean isKeyValue, String... values);

    void error(String msg);

    void error(boolean isKeyValue, String... values);

    void error(String msg, Throwable t);

    void error(FaultCodeBase faultCode, String errorMsg, Throwable t, boolean isKeyValue, String... values);

    boolean isDebugEnabled();

    boolean isInfoEnabled();

	void debug(Function<Logger, String> func);

	void info(Function<Logger, String> func);

	void warn(Function<Logger, String> func);

	boolean isWarnEnabled();

}

