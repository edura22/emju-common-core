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

import java.util.Arrays;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import com.safeway.app.emju.exception.FaultCodeBase;
import com.safeway.app.emju.helper.ValidationHelper;

/* ***************************************************************************
 * NAME         : LogFormatter.java
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
 * methods to build messages that needs to be logged for error, information or debugging.
 *
 * @author ahani00
 */
public class LogFormatter {

    private static final String KEY_INFO = "DevData";
    private static final String KEY_VALUE_IDENTIFIER = " = ";
    private static final String VALUE_QUOTE = "\"";
    private static final String KEY_VALUE_SEPARAT0R = ", ";
    private static final String FAULTCODE_KEY = "faultCode";
    private static final String ERRORMSG_KEY = "errorMsg";
    private static final String ERRORCAUSE_KEY = "cause";
    private static final String NESTEDCAUSESTART_KEY = "[NestedCause : ";
    private static final String NESTEDCAUSEEND_KEY = "]";
    private static final String KEY_REPLACE_PATTERN = "[^A-Za-z0-9._]";

    public static String formatMsg(final String... values) {
        return formatMsg(false, values);
    }

    public static String formatMsg(final boolean isKeyValue, final String... values) {
        StringBuilder strBuilder = new StringBuilder();
        buildFormattedText(strBuilder, isKeyValue, values);
        return strBuilder.toString();
    }

    public static String formatMsg(
        final FaultCodeBase faultCode, final String errorMsg, final Throwable t, final boolean isKeyValue,
        final String... values) {

        StringBuilder strBuilder = new StringBuilder();

        if (faultCode != null) {
            strBuilder
            .append(FAULTCODE_KEY).append(KEY_VALUE_IDENTIFIER).append(VALUE_QUOTE).append(
                StringEscapeUtils.escapeJava(String.valueOf(faultCode))).append(
                    VALUE_QUOTE);
        }
        if (ValidationHelper.isNonEmpty(errorMsg)) {
            addSeparator(strBuilder);
            strBuilder
            .append(ERRORMSG_KEY).append(KEY_VALUE_IDENTIFIER).append(VALUE_QUOTE).append(
                StringEscapeUtils.escapeJava(errorMsg)).append(
                    VALUE_QUOTE);
        }
        if (t != null) {
            String strCause = t.getMessage();
            Throwable nestedError = t.getCause();
            String strNestedCause = null;
            if (nestedError != null) {
                strNestedCause = nestedError.getMessage();
            }
            StringBuilder strCauseBuilder = new StringBuilder();
            if (ValidationHelper.isNonEmpty(strCause)) {
                strCauseBuilder.append(strCause);
            }
            if (ValidationHelper.isNonEmpty(strNestedCause)) {
                if (strCauseBuilder.length() > 0) {
                    strCauseBuilder.append(NESTEDCAUSESTART_KEY).append(strNestedCause).append(NESTEDCAUSEEND_KEY);
                }
                else {
                    strCauseBuilder.append(strNestedCause);
                }
            }
            if (strCauseBuilder.length() > 0) {
                addSeparator(strBuilder);
                strBuilder
                .append(ERRORCAUSE_KEY).append(KEY_VALUE_IDENTIFIER).append(VALUE_QUOTE).append(
                    StringEscapeUtils.escapeJava(strCauseBuilder.toString()))
                .append(VALUE_QUOTE);
            }
        }
        buildFormattedText(strBuilder, isKeyValue, values);

        return strBuilder.toString();
    }

    private static void buildFormattedText(
        final StringBuilder strBuilder, final boolean isKeyValue, final String... values) {

        if (ValidationHelper.isEmptyArray(values)) {
            return;
        }

        if (isKeyValue && ((values.length % 2) == 0)) {
            int index = 0;
            for (Object value : values) {
                if ((index++ % 2) == 0) {
                    addSeparator(strBuilder);
                    strBuilder.append(StringUtils.replacePattern(String.valueOf(value), KEY_REPLACE_PATTERN, "_"));
                    strBuilder.append(KEY_VALUE_IDENTIFIER);
                }
                else {
                    strBuilder.append(VALUE_QUOTE);
                    strBuilder.append(
                        StringEscapeUtils.escapeJava(StringUtils.replaceChars(String.valueOf(value), '"', '\'')));
                    strBuilder.append(VALUE_QUOTE);
                }
            }
        }
        else {
            addSeparator(strBuilder);
            strBuilder
            .append(KEY_INFO).append(KEY_VALUE_IDENTIFIER).append(VALUE_QUOTE).append(
                    StringEscapeUtils.escapeJava(StringUtils.replaceChars(Arrays.toString(values), '"', '\'')))
            .append(VALUE_QUOTE);
        }
    }

    private static void addSeparator(final StringBuilder strBuilder) {
        if (strBuilder.length() > 0) {
            strBuilder.append(KEY_VALUE_SEPARAT0R);
        }
    }
}


