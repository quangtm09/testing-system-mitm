/*
 * com.bosch.rts.entity.CandidateHasAttachment
 */
package com.bosch.rts.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;

/**
 * The Class CandidateHasAttachment.
 */
@Entity
@Table(name = "trts_candidate_has_attachments")
public class CandidateHasAttachment implements java.io.Serializable {

	/** serialVersionUID = 4894354259111214755L;. */
	private static final long serialVersionUID = 4894354259111214755L;
	
	/** The cha attachment id. */
	private Integer chaAttachmentId;
	
	/** The cha attachment name. */
	private String chaAttachmentName;
	
	/** The cha attachment created on. */
	private Date chaAttachmentCreatedOn;
	
	/** The cha attachment created by. */
	private String chaAttachmentCreatedBy;
	
	/** The cha attachment updated on. */
	private Date chaAttachmentUpdatedOn;
	
	/** The cha attachment updated by. */
	private String chaAttachmentUpdatedBy;
	
	/** The cha attachment base path. */
	private String chaAttachmentBasePath;
	
	/** The candidate. */
	private Candidate candidate;
	
	/** The cha attachment length. */
	private Integer chaAttachmentLength;
	
	/** The cha attachment status. */
	private String chaAttachmentStatus;
	
	/** The cha attachment description. */
	private String chaAttachmentDescription;

	/**
	 * Instantiates a new candidate has attachment.
	 */
	public CandidateHasAttachment() {
	}

	/**
	 * Instantiates a new candidate has attachment.
	 *
	 * @param chaAttachmentName the cha attachment name
	 */
	public CandidateHasAttachment(String chaAttachmentName) {
		this.chaAttachmentName = chaAttachmentName;
	}

	/**
	 * Instantiates a new candidate has attachment.
	 *
	 * @param chaAttachmentName the cha attachment name
	 * @param chaAttachmentCreatedOn the cha attachment created on
	 * @param chaAttachmentCreatedBy the cha attachment created by
	 * @param chaAttachmentUpdatedOn the cha attachment updated on
	 * @param chaAttachmentUpdatedBy the cha attachment updated by
	 * @param chaAttachmentBasePath the cha attachment base path
	 * @param candidate the candidate
	 * @param chaAttachmentLength the cha attachment length
	 * @param chaAttachmentStatus the cha attachment status
	 * @param chaAttachmentDescription the cha attachment description
	 */
	public CandidateHasAttachment(String chaAttachmentName,
			Date chaAttachmentCreatedOn, String chaAttachmentCreatedBy,
			Date chaAttachmentUpdatedOn, String chaAttachmentUpdatedBy,
			String chaAttachmentBasePath, Candidate candidate,
			Integer chaAttachmentLength, String chaAttachmentStatus,
			String chaAttachmentDescription) {
		this.chaAttachmentName = chaAttachmentName;
		this.chaAttachmentCreatedOn = chaAttachmentCreatedOn;
		this.chaAttachmentCreatedBy = chaAttachmentCreatedBy;
		this.chaAttachmentUpdatedOn = chaAttachmentUpdatedOn;
		this.chaAttachmentUpdatedBy = chaAttachmentUpdatedBy;
		this.chaAttachmentBasePath = chaAttachmentBasePath;
		this.candidate = candidate;
		this.chaAttachmentLength = chaAttachmentLength;
		this.chaAttachmentStatus = chaAttachmentStatus;
		this.chaAttachmentDescription = chaAttachmentDescription;
	}

	/**
	 * Gets the cha attachment id.
	 *
	 * @return the cha attachment id
	 */
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "cha_attachment_id", unique = true, nullable = false)
	public Integer getChaAttachmentId() {
		return this.chaAttachmentId;
	}

	/**
	 * Sets the cha attachment id.
	 *
	 * @param chaAttachmentId the new cha attachment id
	 */
	public void setChaAttachmentId(Integer chaAttachmentId) {
		this.chaAttachmentId = chaAttachmentId;
	}

	/**
	 * Gets the cha attachment name.
	 *
	 * @return the cha attachment name
	 */
	@Column(name = "cha_attachment_name", nullable = false, length = 100)
	@NotNull
	@Length(max = 100)
	public String getChaAttachmentName() {
		return this.chaAttachmentName;
	}

	/**
	 * Sets the cha attachment name.
	 *
	 * @param chaAttachmentName the new cha attachment name
	 */
	public void setChaAttachmentName(String chaAttachmentName) {
		this.chaAttachmentName = chaAttachmentName;
	}

	/**
	 * Gets the cha attachment created on.
	 *
	 * @return the cha attachment created on
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cha_attachment_created_on", length = 0)
	public Date getChaAttachmentCreatedOn() {
		return this.chaAttachmentCreatedOn;
	}

	/**
	 * Sets the cha attachment created on.
	 *
	 * @param chaAttachmentCreatedOn the new cha attachment created on
	 */
	public void setChaAttachmentCreatedOn(Date chaAttachmentCreatedOn) {
		this.chaAttachmentCreatedOn = chaAttachmentCreatedOn;
	}

	/**
	 * Gets the cha attachment created by.
	 *
	 * @return the cha attachment created by
	 */
	@Column(name = "cha_attachment_created_by", length = 50)
	@Length(max = 50)
	public String getChaAttachmentCreatedBy() {
		return this.chaAttachmentCreatedBy;
	}

	/**
	 * Sets the cha attachment created by.
	 *
	 * @param chaAttachmentCreatedBy the new cha attachment created by
	 */
	public void setChaAttachmentCreatedBy(String chaAttachmentCreatedBy) {
		this.chaAttachmentCreatedBy = chaAttachmentCreatedBy;
	}

	/**
	 * Gets the cha attachment updated on.
	 *
	 * @return the cha attachment updated on
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cha_attachment_updated_on", length = 0)
	public Date getChaAttachmentUpdatedOn() {
		return this.chaAttachmentUpdatedOn;
	}

	/**
	 * Sets the cha attachment updated on.
	 *
	 * @param chaAttachmentUpdatedOn the new cha attachment updated on
	 */
	public void setChaAttachmentUpdatedOn(Date chaAttachmentUpdatedOn) {
		this.chaAttachmentUpdatedOn = chaAttachmentUpdatedOn;
	}

	/**
	 * Gets the cha attachment updated by.
	 *
	 * @return the cha attachment updated by
	 */
	@Column(name = "cha_attachment_updated_by", length = 50)
	@Length(max = 50)
	public String getChaAttachmentUpdatedBy() {
		return this.chaAttachmentUpdatedBy;
	}

	/**
	 * Sets the cha attachment updated by.
	 *
	 * @param chaAttachmentUpdatedBy the new cha attachment updated by
	 */
	public void setChaAttachmentUpdatedBy(String chaAttachmentUpdatedBy) {
		this.chaAttachmentUpdatedBy = chaAttachmentUpdatedBy;
	}

	/**
	 * Gets the cha attachment base path.
	 *
	 * @return the cha attachment base path
	 */
	@Column(name = "cha_attachment_base_path", length = 50)
	@Length(max = 50)
	public String getChaAttachmentBasePath() {
		return this.chaAttachmentBasePath;
	}

	/**
	 * Sets the cha attachment base path.
	 *
	 * @param chaAttachmentBasePath the new cha attachment base path
	 */
	public void setChaAttachmentBasePath(String chaAttachmentBasePath) {
		this.chaAttachmentBasePath = chaAttachmentBasePath;
	}

	/**
	 * Gets the candidate.
	 *
	 * @return the candidate
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cha_attachment_candidate_id")
	public Candidate getCandidate() {
		return this.candidate;
	}

	/**
	 * Sets the candidate.
	 *
	 * @param candidate the new candidate
	 */
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}

	/**
	 * Gets the cha attachment length.
	 *
	 * @return the cha attachment length
	 */
	@Column(name = "cha_attachment_length")
	public Integer getChaAttachmentLength() {
		return this.chaAttachmentLength;
	}

	/**
	 * Sets the cha attachment length.
	 *
	 * @param chaAttachmentLength the new cha attachment length
	 */
	public void setChaAttachmentLength(Integer chaAttachmentLength) {
		this.chaAttachmentLength = chaAttachmentLength;
	}

	/**
	 * Gets the cha attachment status.
	 *
	 * @return the cha attachment status
	 */
	@Column(name = "cha_attachment_status", length = 10)
	@Length(max = 10)
	public String getChaAttachmentStatus() {
		return this.chaAttachmentStatus;
	}

	/**
	 * Sets the cha attachment status.
	 *
	 * @param chaAttachmentStatus the new cha attachment status
	 */
	public void setChaAttachmentStatus(String chaAttachmentStatus) {
		this.chaAttachmentStatus = chaAttachmentStatus;
	}

	/**
	 * Gets the cha attachment description.
	 *
	 * @return the cha attachment description
	 */
	@Column(name = "cha_attachment_description", length = 200)
	@Length(max = 200)
	public String getChaAttachmentDescription() {
		return this.chaAttachmentDescription;
	}

	/**
	 * Sets the cha attachment description.
	 *
	 * @param chaAttachmentDescription the new cha attachment description
	 */
	public void setChaAttachmentDescription(String chaAttachmentDescription) {
		this.chaAttachmentDescription = chaAttachmentDescription;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString(){
		return UploadUtils.humanReadableFileLength(chaAttachmentLength);		
	}	
	
	@Transient
	public String getExtension(){
		if(this.chaAttachmentName != null){
			return this.chaAttachmentName.substring(this.chaAttachmentName.lastIndexOf("."), this.chaAttachmentName.length());
		}
		return null;
	}
}
