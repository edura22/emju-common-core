/* **************************************************************************
 * Copyright 2016 Albertsons Safeway.
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

package com.safeway.app.emju.mobile.model;

/* ***************************************************************************
 * NAME         : ClientRequestType.java
 *
 * SYSTEM       : emju-allocation
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Jan 14, 2016 ahani00
 * Initial creation for emju-allocation
 *
 ***************************************************************************/

/**
 * @author ahani00
 *
 */
public class ClientRequestType {

    private String apiKey;
    private String apiVersion;

    /**
     * @return the apiKey
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @param apiKey
     *            the apiKey to set
     */
    public void setApiKey(final String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * @return the apiVersion
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     * @param apiVersion
     *            the apiVersion to set
     */
    public void setApiVersion(final String apiVersion) {
        this.apiVersion = apiVersion;
    }

}
