/*
 * /rts/src/hot/com/bosch/rts/bus/Resources.java
 */
package com.bosch.rts.bus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Map;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.log.Log;

import com.bosch.rts.session.CandidateHasAttachmentsHome;
import com.bosch.rts.session.CandidateHome;
import com.bosch.rts.session.RecruitRequestHasAttachmentHome;

/**
 * Upload and download attachment files. 
 * 
 * @author nmg1hc
 * @author khb1hc
 *
 */
@Name("resources")
public class Resources implements Serializable {

	/** serialVersionUID. */
	
	private static final long serialVersionUID = 5890504539507672598L;

	/** The log. */
	@Logger
	private transient Log log;
	
	/** The data. */
	private FileInputStream data;	
	
	/** The candidate id. */
	private Integer candidateId;
	
	/** The recruit request id. */
	private Integer recruitRequestId;
	
	/** The attachment id. */
	@RequestParameter
	private Integer attachmentId;
	
	/**
	 * Gets the attachment id.
	 *
	 * @return the attachment id
	 */
	public int getAttachmentId() {
		return attachmentId;
	}	
	
	/**
	 * Sets the attachment id.
	 *
	 * @param attachmentId the new attachment id
	 */
	public void setAttachmentId(int attachmentId) {
		this.attachmentId = attachmentId;
	}

	/** The candidate home. */
	@In(create = true)
	CandidateHome candidateHome;
	
	/** The messages. */
	@In
	private Map<String, String> messages;

	/**
	 * Gets the candidate id.
	 *
	 * @return the candidate id
	 */
	public int getCandidateId() {
		return candidateId;
	}

	/**
	 * Sets the candidate id.
	 *
	 * @param candidateId the new candidate id
	 */
	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}
	
	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		
		String fileName = "";	
		
		if(this.attachmentId != null ){	
			
			if(recruitRequestId != null ){
				RecruitRequestHasAttachmentHome attachmentHome = (RecruitRequestHasAttachmentHome)Component.getInstance(RecruitRequestHasAttachmentHome.class);
				attachmentHome.setId(attachmentId);			
				fileName = attachmentHome.getInstance().getAttachmentName();
			}
			
			if(candidateId != null){
				CandidateHasAttachmentsHome attachmentHome = (CandidateHasAttachmentsHome)Component.getInstance(CandidateHasAttachmentsHome.class);
				attachmentHome.setId(attachmentId);			
				fileName = attachmentHome.getInstance().getChaAttachmentName();
			}
								
		}
		
		return fileName;
	}
	
	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(FileInputStream data) {
		this.data = data;
	}
	
	/**
	 * Gets the uploaded attachment.
	 *
	 * @return the uploaded attachment
	 */
	public FileInputStream getUploadedAttachment() {
		if(this.attachmentId != null ){	
			
			if(this.recruitRequestId != null){
				
				final String basePath = messages.get("com.bosch.storage.recruit_request_doc") + System.getProperty("file.separator");		
				
				RecruitRequestHasAttachmentHome attachmentHome = (RecruitRequestHasAttachmentHome)Component.getInstance(RecruitRequestHasAttachmentHome.class);
				attachmentHome.setId(attachmentId);	
				
				final String fileName = attachmentHome.getInstance().getAttachmentName();
				
				if(fileName != null && !(fileName.isEmpty())){
					
					try {
						data = new FileInputStream(basePath
								+ attachmentHome.getInstance().getAttachmentName());
					} catch (FileNotFoundException e) {
						log.error("Error in getting recruit request data with id: " + recruitRequestId + ". " + e);
					}
				}
			}
			
			if(this.candidateId != null){
				final String basePath = messages.get("com.bosch.storage.candidate_doc") + System.getProperty("file.separator");		
				
				CandidateHasAttachmentsHome attachmentHome = (CandidateHasAttachmentsHome)Component.getInstance(CandidateHasAttachmentsHome.class);
				attachmentHome.setId(attachmentId);	
				
				final String fileName = attachmentHome.getInstance().getChaAttachmentName();
				
				if(fileName != null && !(fileName.isEmpty())){
					
					try {
						data = new FileInputStream(basePath
								+ attachmentHome.getInstance().getChaAttachmentName());
					} catch (FileNotFoundException e) {
						log.error("Error in getting candidate data with id: " + candidateId + ". " + e);
					}
				}
			}
			
			
		}
		
		return data;
	}
	

	/**
	 * Gets the recruit request id.
	 *
	 * @return the recruit request id
	 */
	public Integer getRecruitRequestId() {
		return recruitRequestId;
	}

	/**
	 * Sets the recruit request id.
	 *
	 * @param recruitRequestId the new recruit request id
	 */
	public void setRecruitRequestId(Integer recruitRequestId) {
		this.recruitRequestId = recruitRequestId;
	}

	/**
	 * Check resource available.
	 *
	 * @return the string
	 */
	public String checkResourceAvailable() {	
		
		String resourceStatus = "unavailable";
		
		if(attachmentId != null ){	
			if(recruitRequestId != null){				
				final String basePath = messages.get("com.bosch.storage.recruit_request_doc");
				
				RecruitRequestHasAttachmentHome attachmentHome = (RecruitRequestHasAttachmentHome)Component.getInstance(RecruitRequestHasAttachmentHome.class);
				attachmentHome.setId(attachmentId);	
				
				File f = new File(basePath, attachmentHome.getInstance().getAttachmentName());
				if (f.exists()) {
					resourceStatus = "available";
				}
			}
			
			if(candidateId != null){
				final String basePath = messages.get("com.bosch.storage.candidate_doc");			
				
				CandidateHasAttachmentsHome attachmentHome = (CandidateHasAttachmentsHome)Component.getInstance(CandidateHasAttachmentsHome.class);
				attachmentHome.setId(attachmentId);	
				
				File f = new File(basePath, attachmentHome.getInstance().getChaAttachmentName());
				if (f.exists()) {
					resourceStatus = "available";
				}
				
			}
			
		}
		
		return resourceStatus;
	}
	

}