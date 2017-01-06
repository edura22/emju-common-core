package com.safeway.app.emju.exception;


public class ApplicationException extends Exception{

    private static final long serialVersionUID = -5482271316446712230L;

    private FaultCodeBase faultCode;
    private final String incidentId;

    /**
     * Constructor
     *
     * @param msg
     * @param incidentId
     */
    public ApplicationException(final String msg, final String incidentId) {
        this(null, msg, null, incidentId);
    }

    /**
     * Constructor
     *
     * @param faultCode
     * @param msg
     * @param cause
     */
    public ApplicationException(final FaultCodeBase faultCode, final String msg, final Throwable cause) {
        this(
            faultCode, msg, cause,
            (cause != null) && (cause instanceof ApplicationException)
            ? ((ApplicationException) cause).getIncidentId() : IncidentSession.getIncidentId());
    }

    /**
     * Constructor
     *
     * @param faultCode
     *            FaultCodeBase
     * @param msg
     * @param cause
     */
    public ApplicationException(final FaultCodeBase faultCode, final String msg, final Throwable cause,
        final String incidentId) {
        super(msg,cause);

        this.faultCode = faultCode;
        this.incidentId = incidentId;
    }

    /**
     * Constructor
     *
     * @param appE
     *            ApplicationException
     */
    public ApplicationException(final ApplicationException appE) {
        this(appE.getFaultCode(), appE.getMessage(), appE.getCause(), appE.getIncidentId());
    }


    /**
     * Returns the faultCode.
     *
     * @return FaultCodeBase
     */
    public FaultCodeBase getFaultCode() {
        return faultCode;
    }

    /**
     * @param faultCode
     *            The faultCode to set.
     */
    public void setFaultCode(final FaultCodeBase faultCode) {
        this.faultCode = faultCode;
    }

    /**
     * Returns the incidentId.
     *
     * @return String
     */
    public final String getIncidentId() {
        return incidentId;
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (faultCode != null) {
            builder.append(faultCode);
            builder.append(",");
        }
        builder.append(" IncidentId = ");
        builder.append(getIncidentId());
        builder.append(", message = ");
        builder.append(getMessage());
        builder.append(", cause = ");
        builder.append(getCause());
        builder.append("]");
        return builder.toString();
    }

}
