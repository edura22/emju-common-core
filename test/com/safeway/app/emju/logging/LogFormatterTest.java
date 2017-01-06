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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/* ***************************************************************************
 * NAME         : LogFormatterTest.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Nov 4, 2015 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 * Test for LogFormatter
 *
 * @author ahani00
 */
public class LogFormatterTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(LogFormatterTest.class);

    @Test
    public void testFormatMsg() {
        String expectedMsg = "DevData = \"[Application starting...]\"";
        String logMsg = LogFormatter.formatMsg("Application starting...");
        LOGGER.debug("testFormatMsg logMsg : " + logMsg);
        assertEquals("testFormatMsg: value should be same", expectedMsg, logMsg);
    }

    @Test
    public void testFormatMsgForIncorrectKeyValue() {
        String expectedMsg = "DevData = \"[Application Started, Initial Value, BEGIN]\"";
        String logMsg = LogFormatter.formatMsg(true, "Application Started", "Initial Value", "BEGIN");
        LOGGER.debug("testFormatMsgForIncorrectKeyValue logMsg : " + logMsg);
        assertEquals("testFormatMsgForIncorrectKeyValue: value should be same", expectedMsg, logMsg);
    }

    @Test
    public void testFormatMsgForKeyValue() {
        String expectedMsg = "Status = \"Application Started\", Initial_Value = \"BEGIN\"";
        String logMsg = LogFormatter.formatMsg(true, "Status", "Application Started", "Initial Value", "BEGIN");
        LOGGER.debug("testFormatMsgForKeyValue logMsg : " + logMsg);
        assertEquals("testFormatMsgForKeyValue: value should be same", expectedMsg, logMsg);
    }

}

