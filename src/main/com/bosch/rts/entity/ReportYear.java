package com.bosch.rts.entity;

import java.io.Serializable;

public class ReportYear implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7623311432097400486L;
	
	
	private int id;
	private int value;
	private String name;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the value
	 */
	public int getValue() {
		return this.value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
