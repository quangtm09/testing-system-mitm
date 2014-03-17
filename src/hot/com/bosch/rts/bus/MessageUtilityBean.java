package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.HrResult;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.TechnicalResult;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.validator.RTSHost;

/**
 * The Class MessageUtilityBean.
 */
@Name("messageUtility")
public class MessageUtilityBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -7987456832160243761L;

	/** The log. */
	@Logger
	private transient Log log;

	/** The resource bundle key. */
	private String resourceBundleKey = null;

	/** The params. */
	private Object[] params = null;

	/** The severity. */
	private Severity severity = null;

	/**
	 * Adds the message from resource bundle.
	 * 
	 * @param severity
	 *            the severity
	 * @param resourceBundleKey
	 *            the resource bundle key
	 * @param params
	 *            the params
	 */
	public void addMessageFromResourceBundle(Severity severity,
			String resourceBundleKey, Object... params) {
		this.resourceBundleKey = resourceBundleKey;
		this.params = params;
		this.severity = severity;
	}

	/**
	 * Show message.
	 */
	public void showMessage() {
		if (this.resourceBundleKey != null && this.params != null
				&& this.severity != null) {
			FacesMessages.instance().addFromResourceBundle(severity,
					resourceBundleKey, params);
			clear();
		}
	}

	/**
	 * Clear.
	 */
	public void clear() {
		this.resourceBundleKey = null;
		this.params = null;
		this.severity = null;
	}

	/** The renderer. */
	@In(create = true)
	private Renderer renderer;

	/**
	 * Send.
	 */
	public void send() {
		try {
			renderer.render("/layout/emails.xhtml");
		} catch (Exception e) {
			log.error("Email sending failed." + e.getMessage());
		}
	}

	/**
	 * Send cancel interview email.
	 * 
	 * @param owner
	 *            the owner
	 * @param updatedByUser
	 *            the updated by user
	 * @param approvedByUser
	 *            the approved by user
	 */
	@Asynchronous
	@Observer("sendForApprovingMail")
	public void sendForApprovingMail(final User owner,
			final User updatedByUser, final User approvedByUser) {
		String rtsAddress = RTSHost.getRTSAddress();
		Contexts.getEventContext().set("rtsAddress", rtsAddress);
		Contexts.getEventContext().set("owner", owner);
		Contexts.getEventContext().set("updatedByUser", updatedByUser);
		Contexts.getEventContext().set("approvedByUser", approvedByUser);
		try {
			if (owner != null) {
				final String EMAIL_TEMPLATE_PAGE = "/layout/forApprovingMail.xhtml";
				renderer.render(EMAIL_TEMPLATE_PAGE);
			}
		} catch (Exception e) {
			log.error("Error in sending mails to owner." + e.getMessage());
		}
	}

	/**
	 * Send cancel interview email.
	 * 
	 * @param owner
	 *            the owner
	 * @param updatedByUser
	 *            the updated by user
	 * @param approvedByUser
	 *            the approved by user
	 */
	@Asynchronous
	@Observer("sendApprovedMail")
	public void sendApprovedMail(final User owner, final User updatedByUser,
			final User approvedByUser, final List<User> recipients) {
		String rtsAddress = RTSHost.getRTSAddress();
		Contexts.getEventContext().set("rtsAddress", rtsAddress);
		Contexts.getEventContext().set("owner", owner);
		Contexts.getEventContext().set("updatedByUser", updatedByUser);
		Contexts.getEventContext().set("approvedByUser", approvedByUser);
		Contexts.getEventContext().set("recipients", recipients);
		try {
			if (owner != null) {
				final String EMAIL_TEMPLATE_PAGE = "/layout/approvedMail.xhtml";
				renderer.render(EMAIL_TEMPLATE_PAGE);
			}
		} catch (Exception e) {
			log.error("Error in sending mails to owner." + e.getMessage());
		}
	}

	/**
	 * Send error email.
	 * 
	 * @param recepients
	 *            the recepients
	 */
	public void sendErrorEmail(final List<User> recepients) {
		try {
			renderer.render("/layout/emailsErrors.xhtml");
		} catch (Exception e) {
			log.error("Email sending failed." + e.getMessage());
		}
	}

	/**
	 * Send mail after recruit request created.
	 * 
	 * @param onwnerBy
	 *            the onwner by
	 * @param recruitRequest
	 *            the recruit request
	 * @param recipients
	 *            the recipients
	 * @param action
	 *            the action
	 */
	@Observer("sendMailAfterRecruitRequestCreated")
	@Asynchronous
	public void sendMailAfterRecruitRequestCreated(final User onwnerBy,
			final RecruitRequest recruitRequest, final List<User> recipients,
			final String action) {

		if (RTSUtils.isNotEmpty(recipients)) {
			String rtsAddress = RTSHost.getRTSAddress();
			Contexts.getEventContext().set("rtsAddress", rtsAddress);
			Contexts.getEventContext().set("recipients", recipients);
			Contexts.getEventContext().set("rr", recruitRequest);
			Contexts.getEventContext().set("onwnerBy", onwnerBy);

			String emailTemplate = null;
			if (action.equals("created")) {
				emailTemplate = "/layout/sendMailAfterRecruitRequestCreated.xhtml";
			} else if (action.equals("updated")) {
				emailTemplate = "/layout/sendMailAfterRecruitRequestUpdated.xhtml";
			} else if (action.equals("Closed")) {
				emailTemplate = "/layout/sendMailAfterRecruitRequestClosed.xhtml";
			} else if (action.equals("assigned")) {
				emailTemplate = "/layout/sendMailAfterRecruitRequestAssigned.xhtml";
			}

			if (StringUtils.isNotEmpty(emailTemplate)) {
				try {
					renderer.render(emailTemplate);
				} catch (Exception e) {
					log.error("Error in sending mails after recruited request created. "
							+ e.getMessage());
				}
			}

			String emailApproveTemplate = null;
			if (action.equals("approval")) {
				emailApproveTemplate = "/layout/sendMailAfterRecruitRequestApproval.xhtml";
			} else if (action.equals("Approved")) {
				emailApproveTemplate = "/layout/sendMailAfterRecruitRequestApproved.xhtml";
			} else if (action.equals("Rejected")) {
				emailApproveTemplate = "/layout/sendMailAfterRecruitRequestRejected.xhtml";
			}

			if (StringUtils.isNotEmpty(emailApproveTemplate)) {
				try {
					renderer.render(emailApproveTemplate);
				} catch (Exception e) {
					log.error("Error in sending mails for approving recruit request. "
							+ e.getMessage());
				}

			}
		}

	}

	/**
	 * Send mail after technical result created.
	 * 
	 * @param ownerBy
	 *            the owner by
	 * @param feedbackBy
	 *            the feedback by
	 * @param technicalResult
	 *            the technical result
	 * @param recipients
	 *            the recipients
	 * @param action
	 *            the action
	 */
	@Observer("sendMailAfterTechnicalResultCreated")
	@Asynchronous
	public void sendMailAfterTechnicalResultCreated(final User ownerBy,
			final User feedbackBy, final TechnicalResult technicalResult,
			final List<User> recipients, final String action) {

		if (RTSUtils.isNotEmpty(recipients)) {
			String rtsAddress = RTSHost.getRTSAddress();
			Contexts.getEventContext().set("rtsAddress", rtsAddress);
			Contexts.getEventContext().set("recipients", recipients);
			Contexts.getEventContext().set("technicalResult", technicalResult);
			Contexts.getEventContext().set("ownerBy", ownerBy);
			Contexts.getEventContext().set("feedbackBy", feedbackBy);

			String emailTemplate = null;
			if (action.equals("created")) {
				emailTemplate = "/layout/sendMailAfterTechnicalResultCreated.xhtml";
			} else if (action.equals("updated")) {
				emailTemplate = "/layout/sendMailAfterTechnicalResultUpdated.xhtml";
			}

			if (StringUtils.isNotEmpty(emailTemplate)) {
				try {
					renderer.render(emailTemplate);
				} catch (Exception e) {
					log.error("Error in sending mails after technical result created. "
							+ e.getMessage());
				}
			}
		}

	}
	


	@Observer("sendMailAfterHRInterviewed")
	@Asynchronous
	public void sendMailAfterHRInterviewed(final User candidateCreatedBy, User interviewCreatedBy, 
			final User feedbackBy, final HrResult hrResult,
			final List<User> recipients, final String action) {

		if (RTSUtils.isNotEmpty(recipients)) {
			String rtsAddress = RTSHost.getRTSAddress();
			Contexts.getEventContext().set("rtsAddress", rtsAddress);
			Contexts.getEventContext().set("recipients", recipients);
			Contexts.getEventContext().set("hrResult", hrResult);			
			Contexts.getEventContext().set("candidateCreatedBy", candidateCreatedBy);
			Contexts.getEventContext().set("interviewCreatedBy", interviewCreatedBy);
			Contexts.getEventContext().set("feedbackBy", feedbackBy);			
			

			String emailTemplate = null;
			if (action.equals("add")) {
				emailTemplate = "/layout/sendMailAfterHRInterviewCreated.xhtml";
			} else if (action.equals("edit")) {
				emailTemplate = "/layout/sendMailAfterHRInterviewUpdated.xhtml";
			}

			if (StringUtils.isNotEmpty(emailTemplate)) {
				try {
					renderer.render(emailTemplate);
				} catch (Exception e) {
					log.error("Error in sending mails after HR Interview created. "
							+ e.getMessage());
				}
			}
		}

	}

	/**
	 * Send invitation.
	 * 
	 * @param ownerMail
	 *            the owner mail
	 * @param candiddateName
	 *            the candiddate name
	 * @param interviewers
	 *            the interviewers
	 * @param iCalendarUID
	 *            the i calendar uid
	 * @param startDate
	 *            the start date
	 * @param start
	 *            the start
	 * @param endDate
	 *            the end date
	 * @param now
	 *            the now
	 * @param location
	 *            the location
	 * @param iMethod
	 *            the i method
	 * @param appliedForLevel
	 *            the applied for level
	 * @param recruitName
	 *            the recruit name
	 * @param technicalStatus
	 *            the technical status
	 * @param technicalRemark
	 *            the technical remark
	 * @param mode
	 *            the mode
	 * @param flag
	 *            the flag
	 * @param newInterviwers
	 *            the new interviwers
	 * @param interviewScheduleId
	 *            the interview schedule id
	 */
	private void sendInvitation(final String ownerMail, final String candiddateName, final List<User> interviewers,
			final String iCalendarUID, final String startDate, final Date start, final String endDate, final String now, final String location,
			final String iMethod, final String appliedForLevel, final String recruitName, final String technicalStatus, final String technicalRemark,
			final String mode, final boolean flag, final List<User> newInterviwers, final int interviewScheduleId){
		try {
			
			Properties prop = new Properties();
			prop.put("mail.smtp.host", "rb-smtp-int.bosch.com");
			prop.put("mail.smtp.port", "25");

			Session session = Session.getDefaultInstance(prop, null);
			// Define message
			MimeMessage message = new MimeMessage(session);			
			message.addHeaderLine("method="+iMethod);
			message.addHeaderLine("charset=UTF-8");
			message.addHeaderLine("component=VEVENT");
			message.setFrom(new InternetAddress(ownerMail));
			
			String attendees = "";
			for(User interviewer : interviewers){
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(interviewer.getUsrEmail()));				
				attendees += "ATTENDEE;ROLE=REQ-PARTICIPANT;RSVP=TRUE:MAILTO:" + interviewer.getUsrEmail() + "\n";
			}
				
			String subject = ""; 
			String requested = "";
			if(iMethod.equals("REQUEST")){
				subject = "RTS - Technical Interview Schedule Invitation";
				requested = "invited";
			} else if(iMethod.equals("CANCEL")){
				subject = "CANCELED: RTS - Technical Interview Schedule Invitation";
				requested = "no longer invited";
			} else if(iMethod.equals("PUBLISH")){
				subject = "UPDATED: RTS - Technical Interview Schedule Invitation";
				requested = "invited";
			}
			
			message.setSubject(subject);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("BEGIN:VCALENDAR\n");
			buffer.append("PRODID:-//Microsoft Corporation//Outlook 11.0 MIMEDIR//EN\n");
			buffer.append("VERSION:2.0\n");
			buffer.append("METHOD:");			
			buffer.append(iMethod);
			buffer.append("\n");			
			buffer.append("BEGIN:VEVENT\n");
			buffer.append(attendees);
			buffer.append("ORGANIZER:MAILTO:");
			buffer.append(ownerMail);
			buffer.append("\n");
			buffer.append("DTSTART:" );
			buffer.append(startDate);
			buffer.append("\n");
			buffer.append("DTEND:");
			buffer.append(endDate);
			buffer.append("\n");
			buffer.append("LOCATION:");
			buffer.append(location);
			buffer.append("\n");
			buffer.append("TRANSP:OPAQUE\n");
			buffer.append("SEQUENCE:0\n");
			buffer.append("UID:");
			buffer.append(iCalendarUID);
			buffer.append("\n");
			buffer.append("DTSTAMP:");
			buffer.append(now);
			buffer.append("\n");
			buffer.append("CATEGORIES:Appointment\n");
			buffer.append("DESCRIPTION:");
			buffer.append("You are ");			
			buffer.append(requested);
			buffer.append(" to interview for candidate: ");
			buffer.append(candiddateName);
			buffer.append(".\n\n");			
			buffer.append("SUMMARY:");
			buffer.append(subject);
			buffer.append("\n" );
			buffer.append("PRIORITY:2\n");
			buffer.append("CLASS:PUBLIC\n" );
			buffer.append("BEGIN:VALARM\n");
			buffer.append("TRIGGER:PT15M\n");
			buffer.append("ACTION:DISPLAY\n");
			buffer.append("DESCRIPTION:Reminder\n" );
			buffer.append("END:VALARM\n");
			buffer.append("END:VEVENT\n" + "END:VCALENDAR");
			
			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Fill the message
			messageBodyPart.setHeader("Content-Class","urn:content-classes:calendarmessage");
			messageBodyPart.setHeader("Content-ID", "calendar_message");
			messageBodyPart.setDataHandler(new DataHandler(new ByteArrayDataSource(buffer.toString(), "text/calendar")));						
			
			Multipart multipart = new MimeMultipart();	
			
			Locale locale = Locale.ENGLISH;
			java.util.ResourceBundle resourceBundle = SeamResourceBundle.getBundle("messages", locale);

			final String rts = resourceBundle.getString("rts.title");
			final String interviewersTitle = resourceBundle.getString("int.sch.interviewers");			
			final String rtsAddress = RTSHost.getRTSAddress();
			final String scheduleInfo = resourceBundle.getString("interview.schdedule.info");
			final String candidateTitle = resourceBundle.getString("int.sch.candidate");
			final String appliedForLevelTitle = resourceBundle.getString("interview.apply.for.level");
			final String technicalInterviewTitle = resourceBundle.getString("interview.schedule.technical");
			final String at = resourceBundle.getString("interview.at");
			final String locationTitle = resourceBundle.getString("interview.schedule.technical.location");
			final String recruitTitle = resourceBundle.getString("com.bosch.ui.applyforrequest");
			final String technicalStatusTitle = resourceBundle.getString("interview.schedule.technical.status");
			final String modeTitle = resourceBundle.getString("interview.schedule.technical.mode");
			final String technicalTitle = resourceBundle.getString("int.sch.technical");
			final String technicalRemarkTitle = resourceBundle.getString("com.bosch.ui.remark");
			
			BodyPart descriptionPart = buildHTMLBodyPart(rtsAddress, rts, candiddateName, interviewersTitle, interviewers,
					  scheduleInfo, candidateTitle,	appliedForLevelTitle, appliedForLevel, technicalInterviewTitle,
						at, start.toLocaleString(), locationTitle, location, recruitTitle, recruitName, technicalStatusTitle, technicalStatus,
						modeTitle, mode, technicalTitle, technicalRemarkTitle,	technicalRemark, requested, flag, newInterviwers,
						interviewScheduleId);
			multipart.addBodyPart(descriptionPart);
			
			// Add part one
			multipart.addBodyPart(messageBodyPart);

			// Put parts in message
			message.setContent(multipart);

			// send message
			Transport.send(message);
			
		} catch (MessagingException me) {
			log.error("Error in sending interview schedule: " + me);
		} catch (Exception ex) {
			log.error("Error in sending interview schedule: " + ex);
		}
	}

	/**
	 * Interview schedule event.
	 * 
	 * @param ownerMail
	 *            the owner mail
	 * @param candiddateName
	 *            the candiddate name
	 * @param interviewers
	 *            the interviewers
	 * @param iCalendarUID
	 *            the i calendar uid
	 * @param startDate
	 *            the start date
	 * @param start
	 *            the start
	 * @param endDate
	 *            the end date
	 * @param now
	 *            the now
	 * @param location
	 *            the location
	 * @param iMethod
	 *            the i method
	 * @param appliedForLevel
	 *            the applied for level
	 * @param recruitName
	 *            the recruit name
	 * @param technicalStatus
	 *            the technical status
	 * @param technicalRemark
	 *            the technical remark
	 * @param mode
	 *            the mode
	 * @param oldInterviewers
	 *            the old interviewers
	 * @param unchangedInterviewers
	 *            the unchanged interviewers
	 * @param newInterviewers
	 *            the new interviewers
	 * @param changedStatus
	 *            the changed status
	 * @param changedTime
	 *            the changed time
	 * @param canceled
	 *            the canceled
	 * @param interviewScheduleId
	 *            the interview schedule id
	 */
	@Observer("interviewScheduleEvent")
	@Asynchronous
	public void interviewScheduleEvent(final String ownerMail,
			final String candiddateName, final List<User> interviewers,
			final String iCalendarUID, final String startDate,
			final Date start, final String endDate, final String now,
			final String location, final String iMethod,
			final String appliedForLevel, final String recruitName,
			final String technicalStatus, final String technicalRemark,
			final String mode, final List<User> oldInterviewers,
			final List<User> unchangedInterviewers,
			final List<User> newInterviewers, final boolean changedStatus,
			final boolean changedTime, final boolean canceled,
			final int interviewScheduleId) {

		// send cancel email to old interviewers(the persons be removed
		// interviewer list)
		if (canceled) {
			try {
				sendInvitation(ownerMail, candiddateName, interviewers,
						iCalendarUID, startDate, start, endDate, now, location,
						"CANCEL", appliedForLevel, recruitName,
						technicalStatus, technicalRemark, mode, true,
						newInterviewers, interviewScheduleId);
			} catch (Exception e) {
				log.error("Error in sending canceled interview schedule mails to interviewers."
						+ e.getMessage());
			}
		} else {
			if (RTSUtils.isNotEmpty(oldInterviewers)) {
				try {
					sendInvitation(ownerMail, candiddateName, oldInterviewers,
							iCalendarUID, startDate, start, endDate, now,
							location, "CANCEL", appliedForLevel, recruitName,
							technicalStatus, technicalRemark, mode, true,
							newInterviewers, interviewScheduleId);
				} catch (Exception e) {
					log.error("Error in sending canceled interview schedule mails to old interviewers."
							+ e.getMessage());
				}
			}
			// send update interview schedule email interviewers
			// when interview schedule change time or interviewers

			boolean changed_Time_Interviewer = false;
			if (changedTime || RTSUtils.isNotEmpty(oldInterviewers)
					|| RTSUtils.isNotEmpty(newInterviewers)) {
				changed_Time_Interviewer = true;
			}
			if (RTSUtils.isNotEmpty(unchangedInterviewers)
					&& changed_Time_Interviewer) {
				try {
					sendInvitation(ownerMail, candiddateName,
							unchangedInterviewers, iCalendarUID, startDate,
							start, endDate, now, location, "PUBLISH",
							appliedForLevel, recruitName, technicalStatus,
							technicalRemark, mode, false, null,
							interviewScheduleId);
				} catch (Exception e) {
					log.error("Error in sending update interview schedule mails to interviewers. "
							+ e.getMessage());
				}
			}

			// interview status change to canceled or declined
			try {
				if (RTSUtils.isNotEmpty(unchangedInterviewers) && changedStatus) {
					sendInvitation(ownerMail, candiddateName,
							unchangedInterviewers, iCalendarUID, startDate,
							start, endDate, now, location, "PUBLISH",
							appliedForLevel, recruitName, technicalStatus,
							technicalRemark, mode, false, null,
							interviewScheduleId);
				}
			} catch (Exception e) {
				log.error("Error in sending update interview schedule mails to interviewers. "
						+ e.getMessage());
			}

			// send email to new interviewers
			try {
				if (RTSUtils.isNotEmpty(newInterviewers)) {
					sendInvitation(ownerMail, candiddateName, newInterviewers,
							iCalendarUID, startDate, start, endDate, now,
							location, "REQUEST", appliedForLevel, recruitName,
							technicalStatus, technicalRemark, mode, false,
							null, interviewScheduleId);
				}
			} catch (Exception e) {
				log.error("Error in sending mails to new interviewers."
						+ e.getMessage());
			}
		}
	}

	/**
	 * Builds the HTML body part.
	 * 
	 * @param rtsAddress
	 *            the rts address
	 * @param rts
	 *            the rts
	 * @param candidateName
	 *            the candidate name
	 * @param interviewersTitle
	 *            the interviewers title
	 * @param selectedInterviewers
	 *            the selected interviewers
	 * @param scheduleInfo
	 *            the schedule info
	 * @param candidateTitle
	 *            the candidate title
	 * @param appliedForLevelTitle
	 *            the applied for level title
	 * @param applyForLevel
	 *            the apply for level
	 * @param technicalInterviewTitle
	 *            the technical interview title
	 * @param at
	 *            the at
	 * @param technicalInterviewTime
	 *            the technical interview time
	 * @param locationTitle
	 *            the location title
	 * @param location
	 *            the location
	 * @param recruitTitle
	 *            the recruit title
	 * @param recruitName
	 *            the recruit name
	 * @param technicalStatusTitle
	 *            the technical status title
	 * @param technicalStatus
	 *            the technical status
	 * @param modeTitle
	 *            the mode title
	 * @param mode
	 *            the mode
	 * @param technicalTitle
	 *            the technical title
	 * @param technicalRemarkTitle
	 *            the technical remark title
	 * @param technicalRemark
	 *            the technical remark
	 * @param requested
	 *            the requested
	 * @param flag
	 *            the flag
	 * @param newInterviewers
	 *            the new interviewers
	 * @param interviewScheduleId
	 *            the interview schedule id
	 * @return the body part
	 * @throws MessagingException
	 *             the messaging exception
	 */
	private BodyPart buildHTMLBodyPart(final String rtsAddress,
			final String rts, final String candidateName,
			final String interviewersTitle,
			final List<User> selectedInterviewers, final String scheduleInfo,
			final String candidateTitle, final String appliedForLevelTitle,
			final String applyForLevel, final String technicalInterviewTitle,
			final String at, final String technicalInterviewTime,
			final String locationTitle, final String location,
			final String recruitTitle, final String recruitName,
			final String technicalStatusTitle, final String technicalStatus,
			final String modeTitle, final String mode,
			final String technicalTitle, final String technicalRemarkTitle,
			final String technicalRemark, final String requested,
			final boolean flag, List<User> newInterviewers,
			final int interviewScheduleId) throws MessagingException {
		MimeBodyPart descriptionPart = new MimeBodyPart();
		StringBuilder content = new StringBuilder();

		content.append("<div style=\"color:#1f49aa;\">");
		content.append("Dear Sir or Madam,");
		content.append("<br/><br/>");
		content.append("You are " + requested + " to interview Candidate: ");
		content.append("<strong>");
		content.append(candidateName + ".");
		content.append("</strong>");

		content.append("<br />Kindly find this interview schedule information as below,");
		content.append("<br/><br/>");
		content.append("<strong>" + candidateTitle + "</strong>: "
				+ candidateName + "<br /> ");
		content.append("<strong>" + "Interview Schedule Id" + "</strong>: "
				+ interviewScheduleId + "<br /> ");
		content.append("<strong>" + appliedForLevelTitle + "</strong>: "
				+ applyForLevel + "<br /> ");
		content.append("<strong>" + technicalInterviewTitle + " " + at
				+ "</strong>: " + technicalInterviewTime + "<br />  ");
		content.append("<strong>" + locationTitle + "</strong>: " + location
				+ "<br />  ");
		content.append("<strong>" + recruitTitle + "</strong>: " + recruitName
				+ "<br />  ");
		content.append("<strong>" + technicalTitle + " " + technicalRemarkTitle
				+ "</strong>: " + technicalRemark + "<br /> ");
		content.append("<strong>");
		content.append(interviewersTitle + ":");
		content.append("</strong>");
		content.append("<ul>");
		if (RTSUtils.isNotEmpty(newInterviewers)) {
			for (final User user : newInterviewers) {
				content.append("<li style=\"list-style-type:decimal;color:#1f49aa;\">");
				content.append(user.getUsrFullName());
				content.append("</li>");
			}
		} else {
			for (final User user : selectedInterviewers) {
				content.append("<li style=\"list-style-type:decimal;color:#1f49aa;\">");
				content.append(user.getUsrFullName());
				content.append("</li>");
			}
		}

		content.append("</ul>");

		content.append("<br />To view your interview schedule list, please go to RTS system ("
				+ rtsAddress + ") ");
		content.append("<br />and navigate to > Interview Schedule > Search Interview Schedules.");
		content.append("<br /><br />Please refer to the manuals which are linked on tab \"Help\" and which describe how to work with the RTS system.");

		content.append("<br /><br /> Best regards,");
		// content.append("<br />" + ownerName);
		content.append("<br />");

		content.append("<div style=\"font-weight:bold;\"><a style=\"color:#1f49aa;\" href=\"");
		content.append(rtsAddress);
		content.append("\">");
		content.append(rts);
		content.append("</a></div>");

		content.append("</div>");

		descriptionPart.setContent(content.toString(),
				"text/html; charset=utf-8");
		return descriptionPart;
	}

}