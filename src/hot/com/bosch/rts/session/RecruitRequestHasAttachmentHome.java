package com.bosch.rts.session;


import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.RecruitRequestHasAttachment;
import com.bosch.rts.entity.RecruitRequest;

/**
 * The Class RecruitRequestHasAttachmentHome.
 */
@Name("recruitRequestHasAttachmentHome")
public class RecruitRequestHasAttachmentHome extends EntityHome<RecruitRequestHasAttachment> {

	
	/** serialVersionUID. */
	private static final long serialVersionUID = -7122346062585233969L;
	
	/** The recruit request home. */
	@In(create = true)
	RecruitRequestHome recruitRequestHome;

	/**
	 * Sets the attachment attachment id.
	 *
	 * @param id the new attachment attachment id
	 */
	public void setAttachmentAttachmentId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the attachments attachment id.
	 *
	 * @return the attachments attachment id
	 */
	public Integer getAttachmentsAttachmentId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected RecruitRequestHasAttachment createInstance() {
		RecruitRequestHasAttachment attachment = new RecruitRequestHasAttachment();
		return attachment;
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
		RecruitRequest recruitRequest = recruitRequestHome.getDefinedInstance();
		if (recruitRequest != null) {
			getInstance().setRecruitRequest(recruitRequest);
		}
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
	public RecruitRequestHasAttachment getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}
	
	
	/**
	 * Gets the attachment byd.
	 *
	 * @param id the id
	 * @return the attachment byd
	 */
	public RecruitRequestHasAttachment getAttachmentByd(final int id){
		return getEntityManager().find(RecruitRequestHasAttachment.class, id);
	}

}
