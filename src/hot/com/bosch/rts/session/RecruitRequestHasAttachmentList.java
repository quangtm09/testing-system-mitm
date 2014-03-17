package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;

import com.bosch.rts.entity.RecruitRequestHasAttachment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The Class RecruitRequestHasAttachmentList.
 */
@Name("recruitRequestHasAttachmentList")
public class RecruitRequestHasAttachmentList extends EntityQuery<RecruitRequestHasAttachment> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 176015008847023765L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select trtsAttachments from RecruitRequestHasAttachment trtsAttachments order by trtsAttachments.attachmentCreatedOn desc";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = {
			"lower(trtsAttachments.attachmentName) like lower(concat(#{trtsAttachmentsList.trtsAttachments.attachmentName},'%'))",
			"lower(trtsAttachments.attachmentDescription) like lower(concat(#{trtsAttachmentsList.trtsAttachments.attachmentDescription},'%'))",
			"lower(trtsAttachments.attachmentCreatedBy) like lower(concat(#{trtsAttachmentsList.trtsAttachments.attachmentCreatedBy},'%'))",
			"lower(trtsAttachments.attachmentUpdatedBy) like lower(concat(#{trtsAttachmentsList.trtsAttachments.attachmentUpdatedBy},'%'))",
			"lower(trtsAttachments.attachmentBasePath) like lower(concat(#{trtsAttachmentsList.trtsAttachments.attachmentBasePath},'%'))", };

	/** The attachment. */
	private RecruitRequestHasAttachment attachment = new RecruitRequestHasAttachment();

	/**
	 * Instantiates a new recruit request has attachment list.
	 */
	public RecruitRequestHasAttachmentList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	/**
	 * Gets the attachment.
	 *
	 * @return the attachment
	 */
	public RecruitRequestHasAttachment getAttachment() {
		return attachment;
	}
	
	/**
	 * Gets the attachment list by recruit id.
	 *
	 * @param recruitId the recruit id
	 * @return the attachment list by recruit id
	 */
	public List<RecruitRequestHasAttachment> getAttachmentListByRecruitID(int recruitId){
		List<RecruitRequestHasAttachment> attachmentList = new ArrayList<RecruitRequestHasAttachment>();
		
		String strQuery = "select att from RecruitRequestHasAttachment att where att.recruitRequest.rcrRecruitRequestId = #{recruitId}";
		
		setEjbql(strQuery);

		attachmentList = getResultList();
		
		return attachmentList;
	}
}
