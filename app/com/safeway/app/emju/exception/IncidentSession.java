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

import java.util.UUID;

/* ***************************************************************************
 * NAME         : IncidentSession.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Dec 3, 2015 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 * Generates a unique uuid.
 *
 * @author ahani00
 */
public class IncidentSession {

    private static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String>();
    private static boolean useThreadLocal = false;

    public IncidentSession(final boolean useLocal) {
        IncidentSession.useThreadLocal = useLocal;
        IncidentSession.clear();
    }

    public static String getIncidentId() {
        String incidentId = null;
        if (IncidentSession.useThreadLocal) {
            incidentId = userThreadLocal.get();
            if (incidentId == null) {
                incidentId = UUID.randomUUID().toString();
                userThreadLocal.set(incidentId);
            }
        }
        else {
            incidentId = UUID.randomUUID().toString();
        }
        return incidentId;
    }

    public static void clear() {
        if (IncidentSession.useThreadLocal) {
            userThreadLocal.set(null);
        }
    }

}
