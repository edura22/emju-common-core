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

package com.safeway.app.emju.helper;

import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;

/* ***************************************************************************
 * NAME         : ComparisionHelper.java
 *
 * SYSTEM       : emju-common
 *
 * AUTHOR       : Arun Hariharan
 *
 * REVISION HISTORY
 *
 * Revision 0.0.0.0 Mar 16, 2016 ahani00
 * Initial creation for emju-common
 *
 ***************************************************************************/

/**
 * @author ahani00
 *
 */
public class ComparisionHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(DataHelper.class);

    private ComparisionHelper() {}

    /**
     * Compare 2 comparable objects
     *
     * @param cObj1
     * @param cObj2
     * @return
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public int compare(final Comparable cObj1, final Comparable cObj2) {
        int result = 0;

        if ((cObj1 != null) && (cObj2 != null)) {
            result = cObj1.compareTo(cObj2);
        }
        else if (cObj1 != null) {
            result = 1;
        }
        else {
            result = -1;
        }

        return result;
    }
}

