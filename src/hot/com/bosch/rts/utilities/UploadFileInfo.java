/*
 * com.bosch.rts.utilities.UploadFileInfo
 */
package com.bosch.rts.utilities;

import java.io.Serializable;

import com.bosch.rts.entity.CandidateHasAttachment;
import com.bosch.rts.entity.RecruitRequestHasAttachment;
import com.bosch.rts.entity.UploadUtils;

/**
 * Uploaded Attachment File Bean.
 *
 * @author nmg1hc
 */
public class UploadFileInfo implements Serializable {
	
	/** serialVersionUID. */	
	private static final long serialVersionUID = 8880831133110308866L;
	
	/** The name. */
	private String name;
	
	/** The status. */
	private String status;	
	
	/** The mime. */
	private String mime;
	
	/** The length. */
	private int length;
	
	/** The data. */
	private byte[] data;
	
	/** The file size. */
	private int fileSize;	
	
	/** The recruit request att. */
	private RecruitRequestHasAttachment recruitRequestAtt;
	
	/** The candidate att. */
	private CandidateHasAttachment candidateAtt;	
	
	/**
	 * Gets the candidate att.
	 *
	 * @return the candidate att
	 */
	public CandidateHasAttachment getCandidateAtt() {
		return candidateAtt;
	}

	/**
	 * Sets the candidate att.
	 *
	 * @param candidateAtt the new candidate att
	 */
	public void setCandidateAtt(CandidateHasAttachment candidateAtt) {
		this.candidateAtt = candidateAtt;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}


	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the mime.
	 *
	 * @param mime the new mime
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Gets the mime.
	 *
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}

	/**
	 * Gets the recruit request att.
	 *
	 * @return the recruit request att
	 */
	public RecruitRequestHasAttachment getRecruitRequestAtt() {
		return recruitRequestAtt;
	}

	/**
	 * Sets the recruit request att.
	 *
	 * @param recruitRequestAtt the new recruit request att
	 */
	public void setRecruitRequestAtt(RecruitRequestHasAttachment recruitRequestAtt) {
		this.recruitRequestAtt = recruitRequestAtt;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return UploadUtils.humanReadableFileLength(length);		
	}
	
}