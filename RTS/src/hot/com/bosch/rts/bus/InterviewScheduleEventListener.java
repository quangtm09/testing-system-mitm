package com.bosch.rts.bus;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.util.SimpleHostInfo;
import net.fortuna.ical4j.util.UidGenerator;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.log.Log;
import org.jboss.seam.mail.MailSession;

import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.User;

/**
 * The listener interface for receiving interviewScheduleEvent events.
 * The class that is interested in processing a interviewScheduleEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addInterviewScheduleEventListener<code> method. When
 * the interviewScheduleEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see InterviewScheduleEventEvent
 */
@Name("interviewscheduleeventlistener")
public class InterviewScheduleEventListener {

	/** The log. */
	@Logger
	private transient Log log;
	
	/**
	 * Handle interview schedule added.
	 *
	 * @param is the is
	 * @param interviewer1 the interviewer1
	 * @param interviewer2 the interviewer2
	 */
	@Observer("interviewSchedule_added")
	@Asynchronous
	public void handleInterviewScheduleAdded(InterviewSchedule is,
			User interviewer1, User interviewer2) {

		List<User> interviewers = new ArrayList<User>(2);
		if (interviewer1 != null) {
			interviewers.add(interviewer1);
		}
		if (interviewer2 != null) {
			interviewers.add(interviewer2);
		}
		MailEvent meetingRequest = buildMailEvent(is, interviewers,
				Method.REQUEST, "");
		try {
			meetingRequest.sendAppointment(MailSession.instance());
			// saving UID
//			InterviewScheduleHome interviewScheduleHome = (InterviewScheduleHome) Component
//					.getInstance("interviewScheduleHome");
//			interviewScheduleHome.setId(is.getItsInterviewScheduleId());
//			interviewScheduleHome.getInstance().setIcalendarUID(
//					meetingRequest.getUid().getValue());
//			interviewScheduleHome.update();

		} catch (Exception e) {
			log.error("Execute handleInterviewScheduleAdded method getting error ------------------");
		}

	}

	/**
	 * Builds the mail event.
	 *
	 * @param is the is
	 * @param interviewers the interviewers
	 * @param method the method
	 * @param message the message
	 * @return the mail event
	 */
	private MailEvent buildMailEvent(InterviewSchedule is,
			List<User> interviewers, Method method, String message) {
		MailEvent mailEvent = new MailEvent();
		// adding property for mail event
		// adding UID
		UidGenerator uidGenerator = new UidGenerator(new SimpleHostInfo(
				"rts.vn.bosch.com"), String.valueOf(Thread.currentThread()
				.getId()));
		Uid uid = uidGenerator.generateUid();
		mailEvent.setUid(uid);
		// add attendees
		List<Attendee> attendees = new ArrayList<Attendee>(3);
		List<Address> recipients = new ArrayList<Address>(2);
		for (User interviewer : interviewers) {
			Attendee itw1 = new Attendee(URI.create("mailto:"
					+ interviewer.getUsrEmail()));
			itw1.getParameters().add(Role.REQ_PARTICIPANT);
			itw1.getParameters().add(new Cn(interviewer.getUsrFullName()));
			attendees.add(itw1);
			try {
				recipients.add(new InternetAddress(interviewer.getUsrEmail()));
			} catch (AddressException e) {
				log.error("Execute buildMailEvent method getting error ------------------");
			}
		}
		mailEvent.setAttendees(attendees);
		// add subject
		mailEvent.setSubject("Interview Request");
		// add recipients
		mailEvent.setRecipients(recipients);
		// add meeting location
		// TODO: add meeting location for interview schedule
		// add sender email address
//		String mailTo="";
//		for(User interviewer : interviewers)
//		{
//			mailTo = interviewer.getUsrEmail();
//			mailEvent.setSender(mailTo);
//		}
		
		// add time
		Calendar startDate = new GregorianCalendar();
		/*startDate.setTime(is.getItsInterviewTime());*/
		startDate.setTime(is.getItsTechnicalInterviewTime());
		mailEvent.setStartDate(startDate);
		Dur dur = new Dur(0, 1, 0, 0);
		Calendar endDate = new GregorianCalendar();
		/*endDate.setTime(dur.getTime(is.getItsInterviewTime()));*/
		endDate.setTime(dur.getTime(is.getItsTechnicalInterviewTime()));
		mailEvent.setEndDate(endDate);
		// set priority
		mailEvent.setPriority(Priority.HIGH);
		// set description
		mailEvent.setDescription("Interview Schedule");
		// set product id
		mailEvent.setProductID("Recruitment Tracking System");
		// set method
		mailEvent.setMethod(method);
		// set reminder duration
		mailEvent.setReminderDuration(new Dur(0, 0, 15, 0));
		// set message
		mailEvent.setMessage(message);
		// set user name and password from mail session
		// mailEvent.setUsername(mailSession.getUsername());
		// mailEvent.setPassword(mailSession.getPassword());
		// set the content of
		return mailEvent;
	}

	/**
	 * Handle interview schedule modified.
	 *
	 * @param old the old
	 * @param newIS the new is
	 * @param oldInterviewers the old interviewers
	 * @param newInterviewers the new interviewers
	 */
	@Observer("interviewSchedule_modified")
	@Asynchronous
	public void handleInterviewScheduleModified(InterviewSchedule old,
			InterviewSchedule newIS, List<User> oldInterviewers,
			List<User> newInterviewers) {
		List<User> cancelInterviewer = new ArrayList<User>();
		for (User interviewer : oldInterviewers) {
			if (!newInterviewers.contains(interviewer)) {
				cancelInterviewer.add(interviewer);
			}
		}

		if (cancelInterviewer.size() > 0) {
			MailEvent meetingRequest = buildMailEvent(old, cancelInterviewer,
					Method.CANCEL, "the interview has been canceled");
			if (old.getIcalendarUID() != null
					&& !old.getIcalendarUID().isEmpty()) {
				Uid uid = new Uid(old.getIcalendarUID());
				meetingRequest.setUid(uid);
			}
			try {
				meetingRequest.sendAppointment(MailSession.instance());
			} catch (Exception e) {
				log.error("Execute handleInterviewScheduleModified method getting error ------------------");
			}
		}
/*		if (!old.getItsInterviewTime().equals(newIS.getItsInterviewTime())) {*/
		if (!old.getItsTechnicalInterviewTime().equals(newIS.getItsTechnicalInterviewTime())) {
			MailEvent meetingRequest = buildMailEvent(newIS, newInterviewers,
					Method.REQUEST, "");
			if (old.getIcalendarUID() != null
					&& !old.getIcalendarUID().isEmpty()) {
				Uid uid = new Uid(old.getIcalendarUID());
				meetingRequest.setUid(uid);
			}
			try {
				meetingRequest.sendAppointment(MailSession.instance());
			} catch (Exception e) {
				log.error("Execute handleInterviewScheduleModified method getting error ------------------");
			}
		}
	}

}
