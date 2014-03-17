package com.bosch.boschadministration.model;

import java.util.Date;

/**
 * * The Interface ItemModel.
 */
public interface BAItemModel {

    /**
     * Gets the item id.
     * 
     * @return the item id
     */
    public String getItemId();

    /**
     * Gets the item name.
     * 
     * @return the item name
     */
    public String getItemName();

    /**
     * Gets the item value.
     * 
     * @return the item value
     */
    public String getItemValue();

    /**
     * Gets the item type.
     * 
     * @return the item type
     */
    public String getItemType();

    /**
     * Gets the item url.
     * 
     * @return the item url
     */
    public String getItemUrl();

    /**
     * Gets the item date.
     * 
     * @return the item date
     */
    public Date getItemDate();

    /**
     * Gets the item second value.
     * 
     * @return the item second value
     */
    public String getItemSecondValue();

    /**
     * Sets the item id.
     * 
     * @return the item id
     */
    public void setItemId(String itemId);

    /**
     * Sets the item name.
     * 
     * @return the item name
     */
    public void setItemName(String itemName);

    /**
     * Sets the item value.
     * 
     * @return the item value
     */
    public void setItemValue(String itemValue);

    /**
     * Sets the item type.
     * 
     * @return the item type
     */
    public void setItemType(String itemType);

    /**
     * Sets the item url.
     * 
     * @return the item url
     */
    public void setItemUrl(String itemUrl);

    /**
     * Sets the item date.
     * 
     * @return the item date
     */
    public void setItemDate(Date itemDate);

    /**
     * Sets the item second value.
     * 
     * @return the item second value
     */
    public void setItemSecondValue(String itemSecondValue);

}
