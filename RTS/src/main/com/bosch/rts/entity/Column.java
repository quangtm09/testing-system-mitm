
package com.bosch.rts.entity;

import org.richfaces.model.Ordering;

public class Column {
	private String header;
	private String footer;
	private String background;
	
	/**
	 * @param header
	 * @param footer
	 */
	public Column(String header, String footer, String background) {
	    super();
	    this.header = header;
	    this.footer = footer;
	    this.background = background;
	}
	/**
	 * @return the header
	 */
	public String getHeader() {
	    return header;
	}
	/**
	 * @param header the header to set
	 */
	public void setHeader(String header) {
	    this.header = header;
	}
	/**
	 * @return the footer
	 */
	public String getFooter() {
	    return footer;
	}
	/**
	 * @param footer the footer to set
	 */
	public void setFooter(String footer) {
	    this.footer = footer;
	}

	private Ordering sortOrder;
	
	public Ordering getSortOrder() {
		return sortOrder;
	}
	
	public void setSortOrder(Ordering sortOrder) {
		this.sortOrder = sortOrder;
	}
	public void setBackground(String background) {
		this.background = background;
	}
	public String getBackground() {
		return background;
	}
}