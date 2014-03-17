/*
 *Cell.java 
 */
package com.bosch.rts.entity;

import java.io.Serializable;

/**
 * 
 * @author KHB1HC
 *
 */
public class Cell implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = -8008747464590924823L;
	
	/** The color. */
	private String color;
	
	/** The value. */
	private String value;
	
	/** The col span. */
	private String colSpan;
	
	/** The font color. */
	private String fontColor;
	
	/** The cell flag. */
	private boolean cellFlag = true;
	
	/** The row span. */
	private String rowSpan = "-1";
	
	/** The description. */
	private String description;

	public Cell(){
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the colSpan
	 */
	public String getColSpan() {
		return this.colSpan;
	}

	/**
	 * @param colSpan the colSpan to set
	 */
	public void setColSpan(String colSpan) {
		this.colSpan = colSpan;
	}

	/**
	 * @return the fontColor
	 */
	public String getFontColor() {
		return this.fontColor;
	}

	/**
	 * @param fontColor the fontColor to set
	 */
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}

	/**
	 * @return the cellFlag
	 */
	public boolean isCellFlag() {
		return this.cellFlag;
	}

	/**
	 * @param cellFlag the cellFlag to set
	 */
	public void setCellFlag(boolean cellFlag) {
		this.cellFlag = cellFlag;
	}

	/**
	 * @return the rowSpan
	 */
	public String getRowSpan() {
		return this.rowSpan;
	}

	/**
	 * @param rowSpan the rowSpan to set
	 */
	public void setRowSpan(String rowSpan) {
		this.rowSpan = rowSpan;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
		
}
