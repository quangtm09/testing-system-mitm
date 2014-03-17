package com.bosch.rts.bus;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.activation.MailcapCommandMap;
import javax.activation.MimetypesFileTypeMap;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.Validator;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.CalendarComponent;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStamp;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.Uid;
import net.fortuna.ical4j.model.property.Version;

/**
 * The Class MailEvent.
 */
public class MailEvent {

	/** The password. */
	private String password;
	
	/** The username. */
	private String username;
	
	/** The sender. */
	private String sender;
	
	/** The recipients. */
	private List<Address> recipients;
	
	/** The message. */
	private String message;
	
	/** The start date. */
	private Calendar startDate;
	
	/** The end date. */
	private Calendar endDate;
	
	/** The subject. */
	private String subject;
	
	/** The location. */
	private String location;
	
	/** The description. */
	private String description;
	
	/** The priority. */
	private Priority priority;
	
	/** The product id. */
	private String productID;
	
	/** The uid. */
	private Uid uid;

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public Uid getUid() {
		return uid;
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid the new uid
	 */
	public void setUid(Uid uid) {
		this.uid = uid;
	}

	/** The reminder duration. */
	private Dur reminderDuration = new Dur(0, 0, 5, 0);
	// private String timezoneId = Calendar.getInstance().getTimeZone().getID();
	/** The attendees. */
	private List<Attendee> attendees;
	
	/** The method. */
	private Method method;

	/**
	 * Gets the method.
	 *
	 * @return the method
	 */
	public Method getMethod() {
		return method;
	}

	/**
	 * Gets the recipients.
	 *
	 * @return the recipients
	 */
	public List<Address> getRecipients() {
		return recipients;
	}

	/**
	 * Sets the recipients.
	 *
	 * @param recipients the new recipients
	 */
	public void setRecipients(List<Address> recipients) {
		this.recipients = recipients;
	}

	/**
	 * Sets the method.
	 *
	 * @param method the new method
	 */
	public void setMethod(Method method) {
		this.method = method;
	}

	/**
	 * Instantiates a new mail event.
	 */
	public MailEvent() {
		attendees = new ArrayList<Attendee>();
	}

	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public Priority getPriority() {
		return priority;
	}

	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	/**
	 * Gets the product id.
	 *
	 * @return the product id
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * Sets the product id.
	 *
	 * @param productID the new product id
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	/**
	 * Gets the reminder duration.
	 *
	 * @return the reminder duration
	 */
	public Dur getReminderDuration() {
		return reminderDuration;
	}

	/**
	 * Sets the reminder duration.
	 *
	 * @param reminderDuration the new reminder duration
	 */
	public void setReminderDuration(Dur reminderDuration) {
		this.reminderDuration = reminderDuration;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the location.
	 *
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Sets the location.
	 *
	 * @param location the new location
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Gets the subject.
	 *
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the subject.
	 *
	 * @param subject the new subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public Calendar getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public Calendar getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the sender.
	 *
	 * @return the sender
	 */
	public String getSender() {
		return sender;
	}

	/**
	 * Sets the sender.
	 *
	 * @param sender the new sender
	 */
	public void setSender(String sender) {
		this.sender = sender;
	}

	/**
	 * Send appointment.
	 *
	 * @param session the session
	 * @throws Exception the exception
	 */
	public void sendAppointment(Session session) throws Exception {
		// // TODO Auto-generated method stub
		/*// register the text/calendar mime type
		// Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", "rb-smtp-int.bosch.com");
		// create some properties and get the default Session
		Session session = Session.getDefaultInstance(props, null);
		session.setDebug(true);*/
		MimetypesFileTypeMap mimetypes = (MimetypesFileTypeMap) MimetypesFileTypeMap.getDefaultFileTypeMap();
		mimetypes.addMimeTypes("text/calendar ics ICS");
		// register the handling of text/calendar mime type
		MailcapCommandMap mailcap = (MailcapCommandMap) MailcapCommandMap.getDefaultCommandMap();
		mailcap.addMailcap("text/calendar;; x-java-content-handler=com.sun.mail.handlers.text_plain");
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(sender));

		message.setRecipients(RecipientType.TO, recipients.toArray(new Address[recipients.size()]));		
		message.setSubject(subject);
		// Create an alternative Multipart
		Multipart multipart = new MimeMultipart("alternative");
		// part 1, html text
		BodyPart messageBodyPart = buildHtmlTextPart();
		multipart.addBodyPart(messageBodyPart);
		// Add part two, the calendar
		BodyPart calendarPart = buildCalendarPart();
		multipart.addBodyPart(calendarPart);
		// Put the multipart in message
		message.setContent(multipart);
		message.setSentDate(new Date());
		// send the message
		Transport transport = session.getTransport("smtp");
		transport.connect();
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

	/**
	 * Builds the html text part.
	 *
	 * @return the body part
	 * @throws MessagingException the messaging exception
	 */
	private BodyPart buildHtmlTextPart() throws MessagingException {
		MimeBodyPart descriptionPart = new MimeBodyPart();
		descriptionPart.setContent(message, "text/html; charset=utf-8");
		return descriptionPart;
	}

	// define somewhere the icalendar date format
	/**
	 * Builds the calendar part.
	 *
	 * @return the body part
	 * @throws Exception the exception
	 */
	private BodyPart buildCalendarPart() throws Exception {
		BodyPart calendarPart = new MimeBodyPart();
		// check the icalendar spec in order to build a more complicated meeting
		String calendarContent = getMeetingString();
		calendarPart.addHeader("Content-Class",
				"urn:content-classes:calendarmessage");
		calendarPart.setContent(calendarContent, "text/calendar;method=CANCEL");
		return calendarPart;
	}

	/**
	 * Gets the attendees.
	 *
	 * @return the attendees
	 */
	public List<Attendee> getAttendees() {
		return attendees;
	}

	/**
	 * Sets the attendees.
	 *
	 * @param attendees the new attendees
	 */
	public void setAttendees(List<Attendee> attendees) {
		this.attendees = attendees;
	}

	/**
	 * Gets the meeting string.
	 *
	 * @return the meeting string
	 * @throws URISyntaxException the uRI syntax exception
	 */
	public String getMeetingString() throws URISyntaxException {
		// Create a TimeZone
		DateTime start = new DateTime(startDate.getTime());
		start.setUtc(true);
		DateTime end = new DateTime(endDate.getTime());
		end.setUtc(true);
		DtStart dtStart = new DtStart(start);
		DtEnd dtEnd = new DtEnd(end);
		PropertyList meetingProperties = new PropertyList();
		meetingProperties.add(new DtStamp(new DateTime(Calendar.getInstance()
				.getTimeInMillis())));
		meetingProperties.add(dtStart);
		meetingProperties.add(dtEnd);
		meetingProperties.add(new Summary(subject));
		meetingProperties.add(uid);
		meetingProperties.add(new Organizer("MAILTO:" + sender));
		for (Attendee attendee : attendees) {
			meetingProperties.add(attendee);
		}
		/*meetingProperties.add(new Location(location));*/
		meetingProperties.add(new Location("ABC"));
		meetingProperties.add(priority);
		meetingProperties.add(new Clazz("PUBLIC"));
		meetingProperties.add(new Status("STATUS:CONFIRMED"));
		meetingProperties.add(new Transp("OPAQUE"));		
		VAlarm alarm = new VAlarm(reminderDuration);		
		alarm.getProperties().add(Action.DISPLAY);
		alarm.getProperties().add(new Description("REMINDER"));
		VEvent meeting = new VEvent(meetingProperties);
		meeting.getAlarms().add(alarm);
		net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
		icsCalendar.getProperties().add(CalScale.GREGORIAN);
		icsCalendar.getProperties().add(Method.REQUEST);
		icsCalendar.getProperties().add(new ProdId(productID));
		icsCalendar.getProperties().add(new Version("2.0", "2.0"));
		icsCalendar.getComponents().add(meeting);
		return icsCalendar.toString();
	}
}
