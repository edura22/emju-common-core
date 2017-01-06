package com.safeway.app.emju.util;

import static com.safeway.app.emju.util.GenericConstants.CHAR_EQUALS;
import static com.safeway.app.emju.util.GenericConstants.CHAR_SEMICOLON;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;

import play.Configuration;
import play.libs.F.Promise;
import play.libs.ws.WS;
import play.libs.ws.WSRequest;
import play.libs.ws.WSResponse;

public class WSClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(WSClient.class);

    private static final int DEFAULT_TIMEOUT = 5;
    private static final String COOKIE_HEADER = "Cookie";

    public static final HTTPMethod DEFAULT_GET = new HTTPMethod.GETMethod();
    public static final HTTPMethod DEFAULT_POST = new HTTPMethod.POSTMethod();
    @Inject private  Configuration configuration;

    public WSResponse execute(
        final HTTPMethod httpMethod, final String url, final String timeout, final WSKeyValuePair... requestParams) {
        WSRequest request = WS.url(url);
        setWSRequest(request, requestParams);
        Promise<WSResponse> promiseResponse = httpMethod.executeMethod(request);
        LOGGER.debug("Connecting to URL " + url);
        WSResponse response = promiseResponse.get(StringUtils.isEmpty(timeout) ? DEFAULT_TIMEOUT : Integer.parseInt(timeout), TimeUnit.SECONDS);
        return response;
    }

    // TODO refactor
    public WSResponse execute(
        final HTTPMethod httpMethod, final String url, final String timeout, final String body, final String contentType, final WSKeyValuePair... requestParams) {
        WSRequest request = WS.url(url);
        setWSRequest(request, requestParams);
        request.setContentType(contentType);
        Promise<WSResponse> promiseResponse = httpMethod.executeMethod(request, body);
        WSResponse response = promiseResponse.get(StringUtils.isEmpty(timeout) ? DEFAULT_TIMEOUT : Integer.parseInt(timeout), TimeUnit.SECONDS);
        return response;
    }

    private void setWSRequest(final WSRequest request, final WSKeyValuePair... requestParams) {

        StringBuffer sb = new StringBuffer();
        for (WSKeyValuePair pair : requestParams) {
            if (pair != null) {
                switch (pair.type) {
                    case WSKeyValuePair.HEADER:
                        request.setHeader(pair.name, pair.value);
                        break;
                    case WSKeyValuePair.COOKIE_HEADER:
                        if (sb.length() > 0) {
                            sb.append(CHAR_SEMICOLON);
                        }
                        sb.append(pair.toString());
                        break;
                    case WSKeyValuePair.PARAM:
                        request.setQueryParameter(pair.name, pair.value);
                        break;
                }
            }
        }

        if (sb.length() > 0) {
            request.setHeader(COOKIE_HEADER, sb.toString());
        }
    }

    public String getProperty(final String propertyName) {
        return configuration.getString(propertyName);
    }

    public static class WSKeyValuePair {
        public static final int HEADER = 1;
        public static final int COOKIE_HEADER = 2;
        public static final int PARAM = 3;

        private String name;
        private String value;
        private int type;

        public WSKeyValuePair(final String name, final String value, final int isHeader) {
            this.name = name;
            this.value = value;
            type = isHeader;
        }

        @Override
        public String toString() {
            return name + CHAR_EQUALS + value;
        }
    }

    public interface HTTPMethod {
        Promise<WSResponse> executeMethod(WSRequest request);
        Promise<WSResponse> executeMethod(WSRequest request, String body);

        public class POSTMethod implements HTTPMethod {
            @Override
            public Promise<WSResponse> executeMethod(final WSRequest request) {
                return request.post("");
            }

            @Override
            public Promise<WSResponse> executeMethod(final WSRequest request, final String body) {
                return request.post(body);
            }
        }

        public class GETMethod implements HTTPMethod {
            @Override
            public Promise<WSResponse> executeMethod(final WSRequest request) {
                return request.get();
            }

            @Override
            public Promise<WSResponse> executeMethod(final WSRequest request, final String body) {
                // ignore body
                return executeMethod(request);
            }
        }
    }
}