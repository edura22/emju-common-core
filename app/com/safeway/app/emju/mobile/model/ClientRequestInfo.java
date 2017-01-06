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
 * NAME         : ClientRequestInfo.java
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
public class ClientRequestInfo {

    private String customerGUID;
    private Long householdId = -1L;
    private Long clubCard = -1L;
    private Integer storeId = 0;
    private String postalCode;
    private String banner = "Safeway";
    private String appId;
    private String appUser;
    private String appVersion = "1.0";
    private String sessionToken;
    private String priceZone;
    private boolean preview = false;
    private boolean runtime = true;
    private String timeZone = "America/Los_Angeles";
    private Integer regionId;
    private String loggedUserId;

    /**
     * @return the customerGUID
     */
    public String getCustomerGUID() {
        return customerGUID;
    }

    /**
	 * @return the loggedUserId
	 */
	public String getLoggedUserId() {
		return loggedUserId;
	}

	/**
	 * @param loggedUserId the loggedUserId to set
	 */
	public void setLoggedUserId(String loggedUserId) {
		this.loggedUserId = loggedUserId;
	}

	/**
     * @param customerGUID
     *            the customerGUID to set
     */
    public void setCustomerGUID(final String customerGUID) {
        this.customerGUID = customerGUID;
    }

    /**
     * @return the householdId
     */
    public Long getHouseholdId() {
        return householdId;
    }

    /**
     * @param householdId
     *            the householdId to set
     */
    public void setHouseholdId(final Long householdId) {
        this.householdId = householdId;
    }

    /**
     * @return the clubCard
     */
    public Long getClubCard() {
        return clubCard;
    }

    /**
     * @param clubCard
     *            the clubCard to set
     */
    public void setClubCard(final Long clubCard) {
        this.clubCard = clubCard;
    }

    /**
     * @return the storeId
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     *            the storeId to set
     */
    public void setStoreId(final Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * @return the postalCode
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode
     *            the postalCode to set
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the banner
     */
    public String getBanner() {
        return banner;
    }

    /**
     * @param banner
     *            the banner to set
     */
    public void setBanner(final String banner) {
        this.banner = banner;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     *            the appId to set
     */
    public void setAppId(final String appId) {
        this.appId = appId;
    }

    /**
     * @return the appUser
     */
    public String getAppUser() {
        return appUser;
    }

    /**
     * @param appUser
     *            the appUser to set
     */
    public void setAppUser(final String appUser) {
        this.appUser = appUser;
    }

    /**
     * @return the appVersion
     */
    public String getAppVersion() {
        return appVersion;
    }

    /**
     * @param appVersion
     *            the appVersion to set
     */
    public void setAppVersion(final String appVersion) {
        this.appVersion = appVersion;
    }

    /**
     * @return the sessionToken
     */
    public String getSessionToken() {
        return sessionToken;
    }

    /**
     * @param sessionToken
     *            the sessionToken to set
     */
    public void setSessionToken(final String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public String getPriceZone() {
		return priceZone;
	}

	public void setPriceZone(String priceZone) {
		this.priceZone = priceZone;
	}

	/**
     * @return the preview
     */
    public boolean isPreview() {
        return preview;
    }

    /**
     * @param preview
     *            the preview to set
     */
    public void setPreview(final boolean preview) {
    	if(preview) setRuntime(false);
        this.preview = preview;
    }

    /**
     * @return the runtime
     */
    public boolean isRuntime() {
        return runtime;
    }

    /**
     * @param runtime
     *            the runtime to set
     */
    public void setRuntime(final boolean runtime) {
    	if(runtime) setPreview(false);
        this.runtime = runtime;
    }

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	@Override
	public String toString() {
		return "customerGUID="+customerGUID
				+ " householdId="+householdId
				+ " clubCard="+clubCard
				+ " storeId="+storeId
				+ " banner="+banner
				+ " appId="+appId
				+ " appUser="+appUser
				+ " appVersion="+appVersion
				+ " sessionToken="+sessionToken
				+ " priceZone="+priceZone
				+ " preview="+preview
				+ " runtime="+runtime
				+ " timeZone="+timeZone
				+ " regionId="+regionId
				+ " loggedUserId="+loggedUserId;
	}
    
	
    

}

