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

package com.safeway.app.emju.exception;

/* ***************************************************************************
 * NAME         : ErrorDescriptor.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Sep 18, 2015 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 * @author ahani00
 *
 */
public class ErrorDescriptor {

    private final String code;
    private final String msg;

    /**
     * @param code
     * @param msg
     */
    public ErrorDescriptor(final String code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

}

