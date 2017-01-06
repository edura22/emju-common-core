package com.safeway.app.emju.exception;

public class XMLEOFException extends RuntimeException {

    private static final long serialVersionUID = 3585728075475399488L;

    /**
     * No arg Constructor
     */
    public XMLEOFException() {
        // No arg Constructor to support basic EOF errors
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public XMLEOFException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

