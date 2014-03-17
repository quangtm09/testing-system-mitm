package com.bosch.rts.session;


import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.CandidateHasAttachment;

/**
 * The Class CandidateHasAttachmentsHome.
 */
@Name("candidateHasAttachmentsHome")
public class CandidateHasAttachmentsHome extends
		EntityHome<CandidateHasAttachment> {

	/** serialVersionUID = -3571841001958585746L;. */
	
	
	private static final long serialVersionUID = -3571841001958585746L;

	/**
	 * Sets the candidate has attachments cha attachment id.
	 *
	 * @param id the new candidate has attachments cha attachment id
	 */
	public void setCandidateHasAttachmentsChaAttachmentId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the candidate has attachments cha attachment id.
	 *
	 * @return the candidate has attachments cha attachment id
	 */
	public Integer getCandidateHasAttachmentsChaAttachmentId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected CandidateHasAttachment createInstance() {
		CandidateHasAttachment candidateHasAttachments = new CandidateHasAttachment();
		return candidateHasAttachments;
	}

	/**
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	/**
	 * Wire.
	 */
	public void wire() {
		getInstance();
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public CandidateHasAttachment getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

}
