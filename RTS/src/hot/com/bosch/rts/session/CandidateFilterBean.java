/**
 * /rts/src/hot/com/bosch/rts/session/CandidateFilterBean.java
 */
package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.RecruitRequest;

/**
 * Filter utilities for candidate form fields.
 * 
 * @author KHB1HC
 */
@Name(value = "candidateFilterBean")
public class CandidateFilterBean {

	/** The filter name value. */
	private String filterNameValue = "";
	
	/** The filter email value. */
	private String filterEmailValue = "";

	/** The filter contact number value. */
	private String filterContactNumberValue = "";

	/** The filter degree value. */
	private String filterDegreeValue = "";
	
	/** The filter applied req value. */
	private String filterAppliedReqValue = "";	
	
	/** The filter yrs of exp value. */
	private Float filterYrsOfExpValue = null;
	
	/** The filter rel exp value. */
	private Float filterRelExpValue = null;
	
	/** The filter status value. */
	private String filterStatusValue = "";
	
	/**
	 * Instantiates a new short list by filter bean.
	 */
	public CandidateFilterBean(){
		
	}

	/**
	 * Gets the filter name value.
	 *
	 * @return the filterNameValue
	 */
	public String getFilterNameValue() {
		return this.filterNameValue;
	}

	/**
	 * Sets the filter name value.
	 *
	 * @param filterNameValue the filterNameValue to set
	 */
	public void setFilterNameValue(String filterNameValue) {
		this.filterNameValue = filterNameValue;
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
	 * Gets the filter contact number value.
	 *
	 * @return the filterContactNumberValue
	 */
	public String getFilterContactNumberValue() {
		return this.filterContactNumberValue;
	}

	/**
	 * Sets the filter contact number value.
	 *
	 * @param filterContactNumberValue the filterContactNumberValue to set
	 */
	public void setFilterContactNumberValue(String filterContactNumberValue) {
		this.filterContactNumberValue = filterContactNumberValue;
	}

	/**
	 * Gets the filter degree value.
	 *
	 * @return the filterDegreeValue
	 */
	public String getFilterDegreeValue() {
		return this.filterDegreeValue;
	}

	/**
	 * Sets the filter degree value.
	 *
	 * @param filterDegreeValue the filterDegreeValue to set
	 */
	public void setFilterDegreeValue(String filterDegreeValue) {
		this.filterDegreeValue = filterDegreeValue;
	}

	/**
	 * Gets the filter applied request value.
	 *
	 * @return the filterAppliedReqValue
	 */
	public String getFilterAppliedReqValue() {
		return this.filterAppliedReqValue;
	}

	/**
	 * Sets the filter applied request value.
	 *
	 * @param filterAppliedReqValue the filterAppliedReqValue to set
	 */
	public void setFilterAppliedReqValue(String filterAppliedReqValue) {
		this.filterAppliedReqValue = filterAppliedReqValue;
	}

	/**
	 * Gets the filter years of experience value.
	 *
	 * @return the filterYrsOfExpValue
	 */
	public Float getFilterYrsOfExpValue() {
		return this.filterYrsOfExpValue;
	}

	/**
	 * Sets the filter years of experience value.
	 *
	 * @param filterYrsOfExpValue the filterYrsOfExpValue to set
	 */
	public void setFilterYrsOfExpValue(Float filterYrsOfExpValue) {
		this.filterYrsOfExpValue = filterYrsOfExpValue;
	}

	/**
	 * Gets the filter relevant experience value.
	 *
	 * @return the filterRelExpValue
	 */
	public Float getFilterRelExpValue() {
		return this.filterRelExpValue;
	}

	/**
	 * Sets the filter relevant experience value.
	 *
	 * @param filterRelExpValue the filterRelExpValue to set
	 */
	public void setFilterRelExpValue(Float filterRelExpValue) {
		this.filterRelExpValue = filterRelExpValue;
	}

	/**
	 * Gets the filter status value.
	 *
	 * @return the filterStatusValue
	 */
	public String getFilterStatusValue() {
		return this.filterStatusValue;
	}

	/**
	 * Sets the filter status value.
	 *
	 * @param filterStatusValue the filterStatusValue to set
	 */
	public void setFilterStatusValue(String filterStatusValue) {
		this.filterStatusValue = filterStatusValue;
	}	

	
	/**
	 * Filter name.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterName(Object current){
		Candidate candidate = (Candidate) current;
		if(filterNameValue.length() == 0){
			return true;
		}
		
		final String fullname = candidate.getCddName();
		if(fullname != null && !fullname.isEmpty()){
			if(fullname.toLowerCase().startsWith(filterNameValue.toLowerCase())){
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
		Candidate candidate = (Candidate) current;
		if(filterEmailValue.length() == 0){
			return true;
		}
		
		final String email = candidate.getCddEmail();
		if(email != null && !email.isEmpty()){
			if(email.toLowerCase().startsWith(filterEmailValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter contact number.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterContactNumber(Object current){
		Candidate candidate = (Candidate) current;
		if(filterContactNumberValue.length() == 0){
			return true;
		}
		
		final String contactNumber = candidate.getCddPhoneNo();
		if(contactNumber != null && !contactNumber.isEmpty()){
			if(contactNumber.toLowerCase().startsWith(filterContactNumberValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter degree.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterDegree(Object current){
		Candidate candidate = (Candidate) current;
		if(filterDegreeValue.length() == 0){
			return true;
		}
		
		final String degree = candidate.getCddGraduationFaculty();
		if(degree != null && !degree.isEmpty()){
			if(degree.toLowerCase().startsWith(filterDegreeValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	/**
	 * Filter applied for request.
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterAppliedReq(Object current){
		Candidate candidate = (Candidate) current;
		if(filterAppliedReqValue.length() == 0){
			return true;
		}
		final RecruitRequest recruitRequest = candidate.getRecruitRequest();
		if(recruitRequest != null){
			final String appliedReq = recruitRequest.getRecruitRequestName();
			if(appliedReq != null && !appliedReq.isEmpty()){
				if(appliedReq.toLowerCase().startsWith(filterAppliedReqValue.toLowerCase())){
					return true;
				} else {
					return false;
				}
			} else {
				return true;
			}
		} 
		
		return false;
		
	}
	
	/**
	 * Filter years of experience
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterYrsOfExp(Object current){
		Candidate candidate = (Candidate) current;
		if(filterYrsOfExpValue == null){
			return true;
		}
		final Float yearOfExperience = candidate.getCddYearOfExperience();
		if(yearOfExperience != null){
			if(String.valueOf(yearOfExperience).startsWith(String.valueOf(filterYrsOfExpValue))){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}		
	}
	
	/**
	 * Filter relevant years of experience
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterRelExp(Object current){
		Candidate candidate = (Candidate) current;
		if(filterRelExpValue == null){
			return true;
		}
		final Float relExp = candidate.getCddYearOfExperience();
		if(relExp != null){
			if(String.valueOf(relExp).startsWith(String.valueOf(filterRelExpValue))){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}		
	}
	
	/**
	 * Filter status
	 *
	 * @param current the current
	 * @return true, if successful
	 */
	public boolean filterStatus(Object current){
		Candidate candidate = (Candidate) current;
		if(filterStatusValue.length() == 0){
			return true;
		}
		final String status = candidate.getCddStatus().toString();
		if(status != null){
			if(status.toLowerCase().startsWith(filterStatusValue.toLowerCase())){
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}		
	}
		
	
	
}
