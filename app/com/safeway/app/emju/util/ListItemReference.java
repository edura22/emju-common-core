package com.safeway.app.emju.util;

public class ListItemReference {

    private String itemRefId;
    private String itemId;
    private String itemType;
    private Integer storeId = 0;

    private static String separator = "~";
    public static final class ItemTypeCode {

        private ItemTypeCode() {}

        public static final String CLUB_SPECIAL = "YCS";
        public static final String UPC = "UPC";
        public static final String FF = "FF";
        public static final String WS = "WS";
        public static final String PD = "PD";
        public static final String CC = "CC";
        public static final String SC = "SC";
        public static final String MF = "MF";

    }

    /*
     * Splits 'itemRefId' and sets itemId, itemType and storeId
     */
    public ListItemReference(final String itemRefId) {

        if(itemRefId != null) {
            String[] keys = itemRefId.split(separator);
            for(int i=0; i<(keys.length+1); i++)
            {
                if(i==0) {
                    setItemType(keys[0]);
                }
                if(i==1) {
                    setItemId(keys[1]);
                }
                if(i==2) {
                    setStoreId(keys[2]);
                }
            }
        }
    }

    /*
     * Generates itemRefId from itemId, itemType, storeId
     */
    public ListItemReference(final String itemType, final String itemId, final Integer storeId) {

        this.itemId = itemId;
        this.itemType = itemType;
        this.storeId = storeId;

        String refId = itemType + separator + itemId;

        //Include storeId in ItemRefId if itemType is NOT PD,SC,MF,FF or UPC
        if(!(ItemTypeCode.PD.equalsIgnoreCase(itemType)
            || ItemTypeCode.SC.equalsIgnoreCase(itemType)
            || ItemTypeCode.MF.equalsIgnoreCase(itemType)
            || ItemTypeCode.FF.equalsIgnoreCase(itemType)
            || ItemTypeCode.UPC.equalsIgnoreCase(itemType)))
        {
            if(null != this.storeId)
            {
                refId = refId.concat(separator + this.storeId.toString());
            }
        }
        itemRefId = refId;
    }

    /**
     * constructor used when all fields are available
     *
     * @param itemRefId
     * @param itemId
     * @param itemType
     * @param storeId
     */
    public ListItemReference(
        final String itemRefId, final String itemId, final String itemType, final Integer storeId) {
        this.itemRefId = itemRefId;
        this.itemId = itemId;
        this.itemType = itemType;
        this.storeId = storeId;
    }

    //setters
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    public void setStoreId(final String storeId) {
        try{
            this.storeId = Integer.parseInt(storeId);
        }
        catch(NumberFormatException ne)
        {
            ;//do nothing
        }
    }

    //getters
    public String getItemRefId() {
        return itemRefId;
    }

    public String getItemId() {
        return itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public Integer getStoreId() {
        return storeId;
    }


}
