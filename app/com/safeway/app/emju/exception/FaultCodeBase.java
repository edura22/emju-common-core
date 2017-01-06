package com.safeway.app.emju.exception;

import java.io.ObjectStreamField;
import java.io.Serializable;
import java.util.ArrayList;

import play.i18n.Messages;

public class FaultCodeBase implements Serializable {

    public static class FaultCodeList extends ArrayList<FaultCodeBase> {

        private static final long serialVersionUID = 3826249930844553690L;

        /**
         * Constructor
         */
        private FaultCodeList() {
            super();
        }

        /*
         * (non-Javadoc)
         * @see java.util.ArrayList#indexOf(java.lang.Object)
         */
        @Override
        public int indexOf(final Object elem) {
            int elemIndex = -1;
            if (elem != null) {
                FaultCodeBase faultCode = (FaultCodeBase) elem;
                for (int index = 0; index < size(); index++) {
                    if (faultCode.getCode().equals(get(index).getCode())) {
                        elemIndex = index;
                        break;
                    }
                }
            }
            return elemIndex;
        }

    }

    private static final long serialVersionUID = 7712727367595657667L;
    private static final ObjectStreamField[] serialPersistentFields;
    private static FaultCodeList faultCodesReference;

    private static final String appCode;
    private static final String severityError;
    private static final String severityFatal;

    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("code", String.class, true) };
        faultCodesReference = new FaultCodeList();
        appCode = Messages.isDefined("app.code") ? Messages.get("app.code") : "";
        severityError = Messages.get("severity.error");
        severityFatal = Messages.get("severity.fatal");
    }

    protected String code;
    protected String description;


    /**
     * Constructor
     *
     * @param code
     *            String
     * @param description
     *            String
     */
    protected FaultCodeBase(final String code, final String description) {
        this.code = code;
        this.description = description;
        faultCodesReference.add(this);
    }

    /**
     * Returns the code.
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the description.
     *
     * @return String
     */
    public String getDescription() {
        return description;
    }

    /**
     * Converts this <code>FaultCodeBase</code> object to a <code>String</code>
     *
     * @return String - a String representation of this object.
     */
    @Override
    public String toString() {
        return code + " : " + description;
    }

    protected static final String getMessage(final String msgKey) {
        return Messages.get(msgKey, appCode, severityError, severityFatal);
    }

    public static FaultCodeBase getFaultCode(final String code) {
        return faultCodesReference.get(faultCodesReference.indexOf(code));
    }

    // Fault Codes

    // System Errors 1000 - 1050

    public static final FaultCodeBase AUTHORIZATION_FAILURE =
        new FaultCodeBase(
            getMessage("service.authorization.failure.errcode"),
            getMessage("service.authorization.failure.errmsg"));

    public static final FaultCodeBase SYSTEM_FAILURE =
        new FaultCodeBase(
            getMessage("service.system.failure.errcode"),
            getMessage("service.system.failure.errmsg"));

    public static final FaultCodeBase UNEXPECTED_SYSTEM_FAILURE =
        new FaultCodeBase(
            getMessage("unexpected.system.failure.errcode"),
            getMessage("unexpected.system.failure.errmsg"));

    public static final FaultCodeBase TEMPORARY_SYSTEM_FAILURE =
        new FaultCodeBase(
            getMessage("temporary.system.failure.errcode"),
            getMessage("temporary.system.failure.errmsg"));

    // Application Failures 1051 - 4000 to be defined in actual application
    // FaultCode.properties

    // Generic Data/Input/Version/Format failures 4001 - 4599

    /** Invalid Input JSON Format. */
    public static final FaultCodeBase INVALID_JSON_DATA =
        new FaultCodeBase(
            getMessage("fault.invalid.json.errcode"),
            getMessage("fault.invalid.json.errmsg"));

    /** No Data retrieved or found for the input criteria. */
    public static final FaultCodeBase NOT_FOUND =
        new FaultCodeBase(
            getMessage("fault.not.found.errcode"),
            getMessage("fault.not.found.errmsg"));

    /** Invalid Input Data. */
    public static final FaultCodeBase INVALID_INPUT_DATA =
        new FaultCodeBase(
            getMessage("fault.invalid.input.data.errcode"),
            getMessage("fault.invalid.input.data.errmsg"));

    /** Invalid Search Criteria. */
    public static final FaultCodeBase INVALID_SEARCH_CRITERIA =
        new FaultCodeBase(
            getMessage("fault.invalid.search.criteria.errcode"),
            getMessage("fault.invalid.search.criteria.errmsg"));

    /** Invalid Requested Version. */
    public static final FaultCodeBase INVALID_REQUESTED_VERSION =
        new FaultCodeBase(
            getMessage("fault.invalid.version.errcode"),
            getMessage("fault.invalid.version.errmsg"));

    /** Deprecated Feature. */
    public static final FaultCodeBase DEPRECATED_FEATURE =
        new FaultCodeBase(
            getMessage("fault.deprecated.feature.errcode"),
            getMessage("fault.deprecated.feature.errmsg"));

    // Redis Cache Failures 4600 - 4800
    /** Redis Locate Failure */
    public static final FaultCodeBase CACHE_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("cache.unavailable.errcode"),
            getMessage("cache.unavailable.errmsg"));

    /** Redis Initialization Failure */
    public static final FaultCodeBase CACHE_INITIALIZATION_FAILURE =
        new FaultCodeBase(
            getMessage("cache.initialization.failure.errcode"),
            getMessage("cache.initialization.failure.errmsg"));

    /** Coherence DCN Registration Failure */
    public static final FaultCodeBase CACHE_DCN_REGISTRATION_FAILURE =
        new FaultCodeBase(
            getMessage("cache.dcn.registration.failure.errcode"),
            getMessage("cache.dcn.registration.failure.errmsg"));

    /** Redis Cache Read Failure */
    public static final FaultCodeBase CACHE_READ_FAILURE =
        new FaultCodeBase(
            getMessage("cache.read.failure.errcode"),
            getMessage("cache.read.failure.errmsg"));

    /** Redis Cache Write Failure */
    public static final FaultCodeBase CACHE_WRITE_FAILURE =
        new FaultCodeBase(
            getMessage("cache.write.failure.errcode"),
            getMessage("cache.write.failure.errmsg"));

    /** Redis Cache Read Miss */
    public static final FaultCodeBase CACHE_READ_MISS =
        new FaultCodeBase(
            getMessage("cache.read.miss.errcode"),
            getMessage("cache.read.miss.errmsg"));

    /** Redis Cache Refresh Failure from Database */
    public static final FaultCodeBase CACHE_REFRESH_FAILURE =
        new FaultCodeBase(
            getMessage("cache.refresh.failure.errcode"),
            getMessage("cache.refresh.failure.errmsg"));

    // DB Errors 5000 - 5499

    public static final FaultCodeBase CONNECTION_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("db.connection.unavailable.errcode"),
            getMessage("db.connection.unavailable.errmsg"));

    /** Unable to select from Database. */
    public static final FaultCodeBase DB_SELECT_FAILURE =
        new FaultCodeBase(
            getMessage("db.select.failure.errcode"),
            getMessage("db.select.failure.errmsg"));

    /** Unable to insert to Database. */
    public static final FaultCodeBase DB_INSERT_FAILURE =
        new FaultCodeBase(
            getMessage("db.insert.failure.errcode"),
            getMessage("db.insert.failure.errmsg"));

    /** Unable to update Database. */
    public static final FaultCodeBase DB_UPDATE_FAILURE =
        new FaultCodeBase(
            getMessage("db.update.failure.errcode"),
            getMessage("db.update.failure.errmsg"));

    /** Unable to delete from Database. */
    public static final FaultCodeBase DB_DELETE_FAILURE =
        new FaultCodeBase(
            getMessage("db.delete.failure.errcode"),
            getMessage("db.delete.failure.errmsg"));

    public static final FaultCodeBase CONNECTION_CLOSE_FAILURE =
        new FaultCodeBase(
            getMessage("db.connection.close.failure.errcode"),
            getMessage("db.connection.close.failure.errmsg"));

    public static final FaultCodeBase DCN_REGISTRATION_FAILURE =
        new FaultCodeBase(
            getMessage("db.dcn.registration.failure.errcode"),
            getMessage("db.dcn.registration.failure.errmsg"));

    public static final FaultCodeBase DCN_UNREGISTRATION_FAILURE =
        new FaultCodeBase(
            getMessage("db.dcn.unregistration.failure.errcode"),
            getMessage("db.dcn.unregistration.failure.errmsg"));

    // Queue Errors 5500 - 5999

    public static final FaultCodeBase QUEUE_CONNECTION_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("queue.connection.unavailable.errcode"),
            getMessage("queue.connection.unavailable.errmsg"));

    /** Unable to read from Queue. */
    public static final FaultCodeBase QUEUE_READ_FAILURE =
        new FaultCodeBase(
            getMessage("queue.read.failure.errcode"),
            getMessage("queue.read.failure.errmsg"));

    /** Unable to write to Queue. */
    public static final FaultCodeBase QUEUE_WRITE_FAILURE =
        new FaultCodeBase(
            getMessage("queue.write.failure.errcode"),
            getMessage("queue.write.failure.errmsg"));

    public static final FaultCodeBase QUEUE_QUOTA_EXCEPTION =
        new FaultCodeBase(
            getMessage("queue.quota.exceeded.errcode"),
            getMessage("queue.quota.exceeded.errmsg"));

    public static final FaultCodeBase QUEUE_MESSAGE_TYPE_INVALID =
        new FaultCodeBase(
            getMessage("queue.message.type.invalid.errcode"),
            getMessage("queue.message.type.invalid.errmsg"));

    public static final FaultCodeBase QUEUE_MESSAGE_CONTENT_INVALID =
        new FaultCodeBase(
            getMessage("queue.message.content.invalid.errcode"),
            getMessage("queue.message.content.invalid.errmsg"));

    // External Service Failures 6000 - 7999

    // CLIP SERVICE ERRORS 6000 - 6099

    /** Copient Clip Service unavailable. */
    public static final FaultCodeBase EXT_CLIP_SERVICE_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("ext.clip.service.unavailable.errcode"),
            getMessage("ext.clip.service.unavailable.errmsg"));

    /** Copient Clip Service failure. */
    public static final FaultCodeBase EXT_CLIP_SERVICE_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.service.failure.errcode"),
            getMessage("ext.clip.service.failure.errmsg"));

    /** Copient Clip Service Unknown Error. */
    public static final FaultCodeBase EXT_CLIP_SERVICE_UNKNOWN_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.service.unknown.error.errcode"),
            getMessage("ext.clip.service.unknown.error.errmsg"));

    /** Invalid Response received from Copient Clip Service. */
    public static final FaultCodeBase EXT_CLIP_SERVICE_INVALID_RESPONSE =
        new FaultCodeBase(
            getMessage("ext.clip.service.invalid.response.errcode"),
            getMessage("ext.clip.service.invalid.response.errmsg"));

    /** Invalid input data/format for Copient Clip Service. */
    public static final FaultCodeBase EXT_CLIP_SERVICE_INVALID_INPUT_FORMAT =
        new FaultCodeBase(
            getMessage("ext.clip.service.invalid.input.errcode"),
            getMessage("ext.clip.service.invalid.input.errmsg"));

    /** Incorrect engine type error from Copient */
    public static final FaultCodeBase EXT_CLIP_SERVICE_INCORRECT_ENGINE_TYPE =
        new FaultCodeBase(
            getMessage("ext.clip.service.incorrect.engine.errcode"),
            getMessage("ext.clip.service.incorrect.engine.errmsg"));

    // CLIP ACCOUNTING SERVICE ERRORS 6100 - 6149

    /** Clip Accounting Service unavailable. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.unavailable.errcode"),
            getMessage("ext.clip.accounting.service.unavailable.errmsg"));

    /** Clip Accounting Service failure. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.failure.errcode"),
            getMessage("ext.clip.accounting.service.failure.errmsg"));

    /** Clip Accounting Service Unknown Error. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_UNKNOWN_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.unknown.error.errcode"),
            getMessage("ext.clip.accounting.service.unknown.error.errmsg"));

    /** Invalid Response received from Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_INVALID_RESPONSE =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.invalid.response.errcode"),
            getMessage("ext.clip.accounting.service.invalid.response.errmsg"));

    /** Invalid input data/format for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_INVALID_INPUT_FORMAT =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.invalid.input.errcode"),
            getMessage("ext.clip.accounting.service.invalid.input.errmsg"));

    /** Coupon Load Limit Exceeded for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_LOAD_LIMIT_EXCEEDED =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.loadlimit.exceeded.errcode"),
            getMessage("ext.clip.accounting.service.loadlimit.exceeded.errmsg"));

    /** Invalid credentials for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_INVALID_SECURITY_TOKEN =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.invalid.security.token.errcode"),
            getMessage("ext.clip.accounting.service.invalid.security.token.errmsg"));

    /** Invalid Coupon for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_INVALID_COUPON =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.invalid.coupon.errcode"),
            getMessage("ext.clip.accounting.service.invalid.coupon.errmsg"));

    /** Clip Error for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_CLIP_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.clip.error.errcode"),
            getMessage("ext.clip.accounting.service.clip.error.errmsg"));

    /** Clip Error for Clip Accounting Service. */
    public static final FaultCodeBase EXT_CLIP_ACCOUNTING_SERVICE_DISCARDABLE_ERROR =
        new FaultCodeBase(
            getMessage("ext.clip.accounting.service.discardable.error.errcode"),
            getMessage("ext.clip.accounting.service.discardable.error.errmsg"));

    // REDEMPTION SERVICE ERRORS 6150 - 6199

    /** Redemption Service unavailable. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_UNAVAILABLE =
        new FaultCodeBase(
            getMessage("ext.redemption.service.unavailable.errcode"),
            getMessage("ext.redemption.service.unavailable.errmsg"));

    /** Redemption Service failure. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_ERROR =
        new FaultCodeBase(
            getMessage("ext.redemption.service.failure.errcode"),
            getMessage("ext.redemption.service.failure.errmsg"));

    /** Redemption Service Unknown Error. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_UNKNOWN_ERROR =
        new FaultCodeBase(
            getMessage("ext.redemption.service.unknown.error.errcode"),
            getMessage("ext.redemption.service.unknown.error.errmsg"));

    /** Invalid Response received from Redemption Service. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_INVALID_RESPONSE =
        new FaultCodeBase(
            getMessage("ext.redemption.service.invalid.response.errcode"),
            getMessage("ext.redemption.service.invalid.response.errmsg"));

    /** Invalid input data/format for Redemption Service. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_INVALID_INPUT_FORMAT =
        new FaultCodeBase(
            getMessage("ext.redemption.service.invalid.input.errcode"),
            getMessage("ext.redemption.service.invalid.input.errmsg"));

    /** Invalid credentials for Redemption Service. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_INVALID_SECURITY_TOKEN =
        new FaultCodeBase(
            getMessage("ext.redemption.service.invalid.security.token.errcode"),
            getMessage("ext.redemption.service.invalid.security.token.errmsg"));

    /** Invalid Coupon for Redemption Service. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_INVALID_COUPON =
        new FaultCodeBase(
            getMessage("ext.redemption.service.invalid.coupon.errcode"),
            getMessage("ext.redemption.service.invalid.coupon.errmsg"));

    /** Clip Error for Redemption Service. */
    public static final FaultCodeBase EXT_REDEMPTION_SERVICE_DISCARDABLE_ERROR =
        new FaultCodeBase(
            getMessage("ext.redemption.service.discardable.error.errcode"),
            getMessage("ext.redemption.service.discardable.error.errmsg"));

    // Defining Catalina Fault codes
    public static final FaultCodeBase ASYNC_POLLING_TIMEOUT_ERROR =
        new FaultCodeBase(
            getMessage("async.polling.service.timeout.error.errcode"),
            getMessage("async.polling.service.timeout.error.errcode.errmsg"));

    public static final FaultCodeBase ASYNC_POLLING_ERROR =
        new FaultCodeBase(
            getMessage("async.polling.service.error.errcode"),
            getMessage("async.polling.service.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_BADREQUEST_400 =
        new FaultCodeBase(
            getMessage("catalina.service.400.error.errcode"),
            getMessage("catalina.service.400.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_UNAUTHORIZED_401 =
        new FaultCodeBase(
            getMessage("catalina.service.401.error.errcode"),
            getMessage("catalina.service.401.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_NOTFOUND_404 =
        new FaultCodeBase(
            getMessage("catalina.service.404.error.errcode"),
            getMessage("catalina.service.404.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_METHODNOTALLOWED_405 =
        new FaultCodeBase(
            getMessage("catalina.service.405.error.errcode"),
            getMessage("catalina.service.405.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_INTERNALSERVERERROR_500 =
        new FaultCodeBase(
            getMessage("catalina.service.500.error.errcode"),
            getMessage("catalina.service.500.error.errcode.errmsg"));

    public static final FaultCodeBase CATALINA_CONNECTION_TIMEOUT =
        new FaultCodeBase(
            getMessage("catalina.service.connection.timeout.error.errcode"),
            getMessage("catalina.service.connection.timeout.error.errcode"));

    public static final FaultCodeBase ASYNC_TASK_REJECTION =
        new FaultCodeBase(
            getMessage("async.task.rejection.error.errcode"),
            getMessage("async.task.rejection.error.errcode.errmsg"));

    public static final FaultCodeBase GENERIC_EXCEPTION =
        new FaultCodeBase(
            getMessage("generic.failure.errcode"),
            getMessage("generic.failure.errmsg"));
    public static final FaultCodeBase MYCARD_RETRIEVAL_ERROR =
        new FaultCodeBase (
            getMessage ("error.retrieving.mycard.items.errcode"),
            getMessage ("error.retrieving.mycard.items.errmsg"));

    public static final FaultCodeBase EMLS_INVALID_CUST_ID =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.customer.id.errcode"),
            getMessage ("emls.error.invalid.customer.id.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_HOUSEHOLD_ID =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.household.id.errcode"),
            getMessage ("emls.error.invalid.household.id.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_CLUBCARD_ID =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.clubcard.id.errcode"),
            getMessage ("emls.error.invalid.clubcard.id.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_POSTAL_CODE =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.postal.code.errcode"),
            getMessage ("emls.error.invalid.postal.code.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_API_KEY =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.api.key.errcode"),
            getMessage ("emls.error.invalid.api.key.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_API_VERSION =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.api.version.errcode"),
            getMessage ("emls.error.invalid.api.version.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_BANNER =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.banner.errcode"),
            getMessage ("emls.error.invalid.banner.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_USER_SESSION_TOKEN =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.session.token.errcode"),
            getMessage ("emls.error.invalid.session.token.errmsg"));
    public static final FaultCodeBase EMLS_UNABLE_TO_PROCESS =
        new FaultCodeBase (
            getMessage ("emls.error.unable.to.process.errcode"),
            getMessage ("emls.error.unable.to.process.errmsg"));
    public static final FaultCodeBase EMLS_NO_LIST_FOUND =
        new FaultCodeBase (
            getMessage ("emls.error.no.list.found.errcode"),
            getMessage ("emls.error.no.list.found.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_EMAIL_INFO =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.email.errcode"),
            getMessage ("emls.error.invalid.email.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_EMAIL_ID =
        new FaultCodeBase (
            getMessage ("emls.error.invalid.emailId.errcode"),
            getMessage ("emls.error.invalid.emailId.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_TIMESTAMP =
            new FaultCodeBase (
                getMessage ("emls.error.invalid.timestamp.errcode"),
                getMessage ("emls.error.invalid.timestamp.errmsg"));
    public static final FaultCodeBase EMLS_INVALID_CLIP_ID =
            new FaultCodeBase (
                getMessage ("emls.error.invalid.clipid.errcode"),
                getMessage ("emls.error.invalid.clipid.errmsg"));

    public static final FaultCodeBase OSSO_SERVICE_UNAVAILABLE =
        new FaultCodeBase (
            getMessage ("osso.service.unavailable.errcode"),
            getMessage ("osso.service.unavailable.errmsg"));
    
    public static final FaultCodeBase LOYALTYACCOUNTS_SERVICE_TIMEOUT =
            new FaultCodeBase (
                getMessage ("iass.service.timeout.errcode"),
                getMessage ("iass.service.timeout.errmsg"));
    
    public static final FaultCodeBase LOYALTYACCOUNTS_NOT_FOUND = 
    		new FaultCodeBase(
    				getMessage("loyaltyaccounts.no.record.found.errcode"),
    				getMessage("loyaltyaccounts.no.record.found.errmsg")
    				);

    public static final FaultCodeBase LOYALTYACCOUNTS_SERVICE_NOTAVAILABLE = 
    		new FaultCodeBase(
    				getMessage("loyaltyaccounts.service.notavailable.errcode"),
    				getMessage("loyaltyaccounts.service.notavailable.errmsg")
    				);

    public static final FaultCodeBase LOYALTYACCOUNTS_GENERIC_EXCEPTION = 
    		new FaultCodeBase(
    				getMessage("loyaltyaccounts.service.generic.errcode"),
    				getMessage("loyaltyaccounts.service.generic.errmsg")
    				);
    
    public static final FaultCodeBase SERVICE_BUS_GENERIC_EXCEPTION = 
    		new FaultCodeBase(
    				getMessage("servicebus.generic.errcode"),
    				getMessage("servicebus.generic.errmsg")
    				);
    
    public static final FaultCodeBase SERVICE_BUS_UNDEFINED_SUBSCRIPTION_EXCEPTION = 
    		new FaultCodeBase(
    				getMessage("servicebus.undefined.subscription.errcode"),
    				getMessage("servicebus.undefined.subscription.errmsg")
    				);
    
    public static final FaultCodeBase SERVICE_BUS_LISTENER_NOT_SET_EXCEPTION = 
    		new FaultCodeBase(
    				getMessage("servicebus.listener.not.set.errcode"),
    				getMessage("servicebus.listener.not.set.errmsg")
    				);
    
    public static final FaultCodeBase SERVICE_BUS_FAILED_TO_ACKNOWLEDGE_EXCEPTION = 
    		new FaultCodeBase(
    				getMessage("servicebus.failed.to.acknowledge.errcode"),
    				getMessage("servicebus.failed.to.acknowledge.errmsg")
    				);

    public static final FaultCodeBase NO_CLIPPED_RECORDS =
        new FaultCodeBase(
            getMessage("fault.mail.norecords.errorcode"), getMessage("fault.mail.norecords.errmsg"));      				
}
