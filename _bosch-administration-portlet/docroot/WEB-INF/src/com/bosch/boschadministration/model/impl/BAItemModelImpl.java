package com.bosch.boschadministration.model.impl;

import com.bosch.boschadministration.model.BAItemModel;

import java.util.Date;

/**
 * The Class BAItemModelImpl.
 */
public class BAItemModelImpl implements BAItemModel {

    /**
     * Instantiates a new item model impl.
     * 
     */
    public BAItemModelImpl(final String itemId) {
        this.itemId = itemId;
    }

    /**
     * Instantiates a new item model impl.
     * 
     * @param itemName
     *            the item name
     * @param itemValue
     *            the item value
     */
    public BAItemModelImpl(final String itemName, final String itemValue) {
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    /**
     * Instantiates a new item model impl.
     * 
     * @param itemName
     *            the item name
     * @param itemValue
     *            the item value
     * @param itemDate
     *            the item date
     */
    public BAItemModelImpl(final String itemName, final String itemValue, final Date itemDate) {
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemDate = itemDate;
    }

    /**
     * Instantiates a new item model impl.
     * 
     * @param itemId
     *            the item id
     * @param itemName
     *            the item name
     * @param itemValue
     *            the item value
     */
    public BAItemModelImpl(final String itemId, final String itemName, final String itemValue) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemValue = itemValue;
    }

    /**
     * Instantiates a new item model impl.
     * 
     * @param itemName
     *            the item name
     * @param itemType
     *            the item type
     * @param itemUrl
     *            the item url
     * @param itemValue
     *            the item value
     */
    public BAItemModelImpl(final String itemName, final String itemType, final String itemUrl, final String itemValue) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemUrl = itemUrl;
        this.itemValue = itemValue;
    }

    public BAItemModelImpl(final String itemId, final String itemName, final String itemType, final String itemUrl,
            final String itemValue) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemUrl = itemUrl;
        this.itemValue = itemValue;
    }

    public BAItemModelImpl(final String itemId, final String itemName, final String itemType, final String itemUrl,
            final String itemValue, final String itemSecondValue) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemUrl = itemUrl;
        this.itemValue = itemValue;
        this.itemSecondValue = itemSecondValue;
    }

    /**
     * Instantiates a new item model impl.
     * 
     * @param itemName
     *            the item name
     * @param itemValue
     *            the item value
     * @param itemUrl
     *            the item url
     * @param itemDate
     *            the item date
     */
    public BAItemModelImpl(final String itemName, final String itemValue, final String itemUrl, final Date itemDate) {
        this.itemName = itemName;
        this.itemValue = itemValue;
        this.itemUrl = itemUrl;
        this.itemDate = itemDate;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemId()
     */
    @Override
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Sets the item id.
     * 
     * @param itemId
     *            the new item id
     */
    public void setItemId(final String itemId) {
        this.itemId = itemId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemName()
     */
    @Override
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Sets the item name.
     * 
     * @param itemName
     *            the new item name
     */
    public void setItemName(final String itemName) {
        this.itemName = itemName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemValue()
     */
    @Override
    public String getItemValue() {
        return this.itemValue;
    }

    /**
     * Sets the item value.
     * 
     * @param itemValue
     *            the new item value
     */
    public void setItemValue(final String itemValue) {
        this.itemValue = itemValue;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemType()
     */
    @Override
    public String getItemType() {
        return this.itemType;
    }

    /**
     * Sets the item type.
     * 
     * @param itemType
     *            the new item type
     */
    public void setItemType(final String itemType) {
        this.itemType = itemType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemUrl()
     */
    @Override
    public String getItemUrl() {
        return this.itemUrl;
    }

    /**
     * Sets the item url.
     * 
     * @param itemUrl
     *            the new item url
     */
    public void setItemUrl(final String itemUrl) {
        this.itemUrl = itemUrl;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.bosch.boschadministration.model.ItemModel#getItemDate()
     */
    @Override
    public Date getItemDate() {
        return this.itemDate;
    }

    @Override
    public String getItemSecondValue() {
        return this.itemSecondValue;
    }

    public void setItemSecondValue(final String itemSecondValue) {
        this.itemSecondValue = itemSecondValue;
    }

    /**
     * Sets the item date.
     * 
     * @param itemDate
     *            the new item date
     */
    public void setItemDate(final Date itemDate) {
        this.itemDate = itemDate;
    }

    /** The item id. */
    private String itemId;

    /** The item name. */
    private String itemName;

    /** The item value. */
    private String itemValue;

    /** The item type. */
    private String itemType;

    /** The item url. */
    private String itemUrl;

    /** The item date. */
    private Date itemDate;

    /** The item value. */
    private String itemSecondValue;
}
