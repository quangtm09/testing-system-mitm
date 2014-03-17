/**
 * /rts/src/hot/com/bosch/rts/session/ShortListByFilterBean.java
 */
package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;

import com.bosch.rts.entity.User;

/**
 * Filter utilities for short list by form fields.
 * @author KHB1HC
 *
 */
@Name(value = "shortListByFilter")
public class ShortListByFilterBean {

	/** The filter full name value. */
	private String filterFullNameValue = "";
	
	/** The filter username value. */
	private String filterUsernameValue = "";
	
	/** The filter email value. */
	private String filterEmailValue = "";
	
	/** The filter id number value. */
	private String filterIdNumberValue = "";
	
	/**
	 * Instantiates a new short list by filter bean.
	 */
	public ShortListByFilterBean(){
		
	}
	
	/**
	 * Gets the filter full name value.
	 *
	 * @return the filterFullNameValue
	 */
	public String getFilterFullNameValue() {
		return this.filterFullNameValue;
	}


	/**
	 * Sets the filter full name value.
	 *
	 * @param filterFullNameValue the filterFullNameValue to set
	 */
	public void setFilterFullNameValue(String filterFullNameValue) {
		this.filterFullNameValue = filterFullNameValue;
	}


	/**
	 * Gets the filter username value.
	 *
	 * @return the filterUsernameValue
	 */
	public String getFilterUsernameValue() {
		return this.filterUsernameValue;
	}


	/**
	 * Sets the filter username value.
	 *
	 * @param filterUsernameValue the filterUsernameValue to set
	 */
	public void setFilterUsernameValue(String filterUsernameValue) {
		this.filterUsernameValue = filterUsernameValue;
	}


	/**
	 * Gets the filter email value.
	 *
	 * @return the filterEmailValue
	 */
	public String getFilterEmailValue() {
		return this.filterEmailValue;
	}


	/**
	 * Sets the filter email value.
	 *
	 * @param filterEmailValue the filterEmailValue to set
	 */
	public void setFilterEmailValue(String filterEmailValue) {
		this.filterEmailValue = filterEmailValue;
	}


	/**
	 * Gets the filter id number value.
	 *
	 * @return the filterIdNumberValue
	 */
	public String getFilterIdNumberValue() {
		return this.filterIdNumberValue;
	}


	/**
	 * Sets the filter id number value.
	 *
	 * @param filterIdNumberValue the filterIdNumberValue to set
	 */
	public void setFilterIdNumberValue(String filterIdNumberValue) {
		this.filterIdNumberValue = filterIdNumberValue;
	}


	/**
	 * Filter full name.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterFullName(Object current){
		User user = (User) current;
		if(filterFullNameValue.length() == 0){
			return true;
		}
		
		final String fullname = user.getUsrFullName();
		if(fullname != null && !fullname.isEmpty()){
			if(fullname.toLowerCase().contains(filterFullNameValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter username.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterUsername(Object current){
		User user = (User) current;
		if(filterUsernameValue.length() == 0){
			return true;
		}
	
		final String username = user.getUsrUserName();
		if(username != null && !username.isEmpty()){
			if(username.toLowerCase().contains(filterUsernameValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter email.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterEmail(Object current){
		User user = (User) current;
		if(filterEmailValue.length() == 0){
			return true;
		}
	
		final String email = user.getUsrEmail();
		if(email != null && !email.isEmpty()){
			if(email.toLowerCase().contains(filterEmailValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter id number.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterIdNumber(Object current){
		User user = (User) current;
		if(filterIdNumberValue.length() == 0){
			return true;
		}
		final String idNumber = user.getUsrIdNumber();
		if(idNumber != null && !idNumber.isEmpty()){
			if(idNumber.toLowerCase().contains(filterIdNumberValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
		
	}
}
