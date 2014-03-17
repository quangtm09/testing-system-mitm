package com.bosch.rts.session;


import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.CandidateHasAttachment;

import java.util.Arrays;

/**
 * The Class CandidateHasAttachmentsList.
 */
@Name("candidateHasAttachmentsList")
public class CandidateHasAttachmentsList extends
		EntityQuery<CandidateHasAttachment> {

	/** serialVersionUID = -7828319368429054108L;. */
	
	private static final long serialVersionUID = -7828319368429054108L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select candidateHasAttachments from CandidateHasAttachments candidateHasAttachments";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"lower(candidateHasAttachments.chaAttachmentName) like lower(concat(#{candidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentName},'%'))",
			"lower(candidateHasAttachments.chaAttachmentCreatedBy) like lower(concat(#{candidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentCreatedBy},'%'))",
			"lower(candidateHasAttachments.chaAttachmentUpdatedBy) like lower(concat(#{candidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentUpdatedBy},'%'))",
			"lower(candidateHasAttachments.chaAttachmentBasePath) like lower(concat(#{candidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentBasePath},'%'))",
			"lower(candidateHasAttachments.chaAttachmentStatus) like lower(concat(#{candidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentStatus},'%'))",
			"lower(candidateHasAttachments.chaAttachmentDescription) like lower(concat(#{CandidateHasAttachmentsList.trtsCandidateHasAttachments.chaAttachmentDescription},'%'))", };

	/** The candidate has attachment. */
	private CandidateHasAttachment candidateHasAttachment = new CandidateHasAttachment();

	/**
	 * Instantiates a new candidate has attachments list.
	 */
	public CandidateHasAttachmentsList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	/**
	 * Gets the candidate has attachment.
	 *
	 * @return the candidate has attachment
	 */
	public CandidateHasAttachment getCandidateHasAttachment() {
		return candidateHasAttachment;
	}
}
