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

/* ***************************************************************************
 * NAME         : LoggerFactory.java
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
 * @author ahani00
 *
 */
public class LoggerFactory {

    /**
     * Returns the required logger implmentation class.
     *
     * @param clazz
     * @return
     */
    public static Logger getLogger(final Class clazz) {
        return new ApplicationLoggerImpl(clazz);
    }

}

