package com.bosch.rts.entity;

import org.apache.commons.lang3.text.WordUtils;

/**
 * Candidate status flow.
 *
 * @author khb1hc
 */
public enum CandidateStatus{	
	
	/** The new. */
	NEW (0), 	
	
	/** The screened. */
	SCREENED (1), 
	
	/** The short listed. */
	SHORT_LISTED (2), 
	
	/** The technical scheduled. */
	TECHNICAL_SCHEDULED (3), 
	
	/** The technical on hold. */
	TECHNICAL_ON_HOLD (4),
	
	/** The technical pass. */
	TECHNICAL_PASS (5), 	
	
	/** The technical fail. */
	TECHNICAL_FAIL (6), 
	
	/** The hr scheduled. */
	HR_SCHEDULED (7), 
	
	/** The hr on hold. */
	HR_ON_HOLD (8),
	
	/** The hr pass. */
	HR_PASS (9), 
	
	/** The hr fail. */
	HR_FAIL (10),	 
	
	/** The to offer. */
	TO_OFFER (11),
	
	/** The offered. */
	OFFERED (12), 
	
	/** The offer accepted. */
	OFFER_ACCEPTED (13), 
	
	/** The offer refused. */
	OFFER_REFUSED (14), 
	
	/** The selected. */
	SELECTED (15),
	
	/** The joined. */
	JOINED (16);
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		String s = super.toString();
		s = s.replace('_', ' ');
		return WordUtils.capitalize(s.toLowerCase());
	}

	/**
	 * Gets the enum value string.
	 *
	 * @return the enum value string
	 */
	public String getEnumValueString() {
		return super.toString();
	}

	/**
	 * Db string.
	 *
	 * @return the string
	 */
	public String dbString() {
		return super.toString();
	}
	
	private int position;
	
	/**
	 * @return the position
	 */
	public int getPosition() {
		return this.position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	CandidateStatus(int position){
		this.position = position;
	}
	
	CandidateStatus(){
	}

	public static void main(String[] args) {
		System.out.println("xxx: " + CandidateStatus.OFFER_ACCEPTED.position);
	}
	
}