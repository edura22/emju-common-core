package com.safeway.app.emju.mobile.util;

import com.safeway.app.emju.helper.ValidationHelper;
import com.safeway.app.emju.logging.Logger;
import com.safeway.app.emju.logging.LoggerFactory;
import com.safeway.app.emju.mobile.exception.MobileErrors;
import com.safeway.app.emju.mobile.exception.TransformException;
import com.safeway.app.emju.mobile.model.ClientRequestInfo;

public class ClientInfoUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientInfoUtil.class);

	public static void validateRegionId(ClientRequestInfo clientInfo) throws TransformException {
		if(clientInfo.getRegionId()==null || clientInfo.getRegionId()<0){
			LOGGER.error("TransformUtil - Region Id is missing or invalid.");
			throw new TransformException(MobileErrors.TRASFORMATION,"Region Id"); 
		}
	}

	public static void validateStoreId(ClientRequestInfo clientInfo) throws TransformException {
		if(clientInfo.getStoreId()==null || clientInfo.getStoreId()<0){
			LOGGER.error("Store Id is missing or invalid.");
			throw new TransformException(MobileErrors.TRASFORMATION,"Store Id"); 
		}
	}

	public static void validateValidHouseholdId(ClientRequestInfo clientInfo)
			throws TransformException {
		int hhLength = clientInfo.getHouseholdId()!=null ? clientInfo.getHouseholdId().toString().length():0;

		if ( hhLength < 7 || hhLength > 18	) {
			LOGGER.error("Household Id is missing or invalid.");
			throw new TransformException(MobileErrors.TRASFORMATION,"Household Id"); 

		}
	}

	public static void validateCustomerGUID(ClientRequestInfo clientInfo)
			throws TransformException {
		if (! ValidationHelper.isNonEmpty(clientInfo.getCustomerGUID()) ) {
			LOGGER.error("CustomerGUID is missing.");
			throw new TransformException(MobileErrors.TRASFORMATION,"CustomerGUID"); 
		}
	}

	
	public static void validateAppId(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getAppId()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"AppId");
		} 
	}
	public static void validateAppUser(ClientRequestInfo clientInfo)
			throws TransformException {

		if ( !ValidationHelper.isNonEmpty(clientInfo.getAppUser()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"AppUser");
		} 
	}
	public static void validateAppVersion(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getAppVersion()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"AppVersion");
		} 
	}

	public static void validateBanner(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getBanner()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"Banner");
		}
	}

	public static void validateClubcard(ClientRequestInfo clientInfo)
			throws TransformException {
		int ccLength = clientInfo.getClubCard()!=null ? clientInfo.getClubCard().toString().length():0;
		if ( ccLength < 7 || ccLength > 18 ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"Clubcard");
		}
	}

	public static void validatePostalCode(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getPostalCode()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"PostalCode");
		}
	}

	public static void validateSessionToken(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getSessionToken()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"SessionToken");
		} 
	}
	
	public static void validatePriceZone(ClientRequestInfo clientInfo)
			throws TransformException {
		if ( !ValidationHelper.isNonEmpty(clientInfo.getPriceZone()) ) {
			throw new TransformException(MobileErrors.TRASFORMATION,"Price Zone");
		} 
	}
}
