package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.Date;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.session.CandidateHome;
import com.bosch.rts.session.InterviewHistoryHome;
import com.bosch.rts.session.RecruitRequestHome;

/**
 * The listener interface for receiving candidateEvent events.
 * The class that is interested in processing a candidateEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCandidateEventListener<code> method. When
 * the candidateEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see CandidateEventEvent
 */
@Name("canidateEventListener")
public class CandidateEventListener implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1873127239931405642L;
	
	/** The renderer. */
	@In(create = true)
	private Renderer renderer;

	/** The log. */
	@Logger
	private transient Log log;

	/**
	 * Handle candidate created event.
	 */
	@Observer("candidateCreated")
	public void handleCandidateCreatedEvent() {
		try {
			renderer.render("/mail template/emailTransferTemplate.xhtml");
		} catch (Exception e) {
			log.error("Execute handleCandidateCreatedEvent method getting error------------------");
		}
	}

	/**
	 * Handle candidate updated event.
	 */
	@Observer("candidateUpdated")
	public void handleCandidateUpdatedEvent() {
		try {
			// renderer.render("/mail template/candidateAddedNotification.xhtml");
			// candidateHome.clearInstance();
			// candidateHome.setId(id);
			// candidateHome.getInstance();
		} catch (Exception e) {
			// TODO: handle exception
			log.error("Execute handleCandidateUpdatedEvent method getting error------------------");
		}
	}

	/**
	 * Handle candidate transfered event.
	 *
	 * @param oldRequest the old request
	 * @param newRequest the new request
	 * @param oldStatus the old status
	 */
	@Observer("candidate_transfered")
	@Asynchronous
	public void handleCanidateTransferedEvent(RecruitRequest oldRequest, RecruitRequest newRequest,
			CandidateStatus oldStatus) {
		try {
			String basePath = ((String) Component.getInstance("basePath")) + "ResourceProvider.seam?candidateId=";
			Contexts.getEventContext().set("cvPath", basePath);
			sendMail();
			FacesMessages.instance().clear();
			FacesMessages.instance().addFromResourceBundle("com.bosch.ui.sendmail.success");
		} catch (Exception ex) {
			FacesMessages.instance().clear();
			FacesMessages.instance().addFromResourceBundle("com.bosch.ui.sendmail.fail");
			log.error("Execute handleCanidateTransferedEvent method getting error------------------");
		}
	}

	/**
	 * Send mail.
	 */
	private void sendMail() {
		renderer.render("/mail template/emailTransferTemplate.xhtml");
	}

	/**
	 * Handle candidate status change event.
	 *
	 * @param candidateId the candidate id
	 * @param oldStatus the old status
	 */
	@Observer("candidate_status_changed")
	public void handleCandidateStatusChangeEvent(Integer candidateId, CandidateStatus oldStatus) {
		CandidateHome candidateHome = (CandidateHome) Component.getInstance("candidateHome", true);
		candidateHome.setId(candidateId);
		RecruitRequestHome recruitRequestHome = (RecruitRequestHome) Component.getInstance("recruitRequestHome");
		InterviewHistoryHome interviewHistoryHome = (InterviewHistoryHome) Component.getInstance(
				"interviewHistoryHome", true);
		if (candidateHome != null) {
			interviewHistoryHome.clearInstance();
			interviewHistoryHome.getInstance().setCandidate(candidateHome.getInstance());
			interviewHistoryHome.getInstance().setRecruitRequest(candidateHome.getInstance().getRecruitRequest());
			interviewHistoryHome.getInstance().setIthUpdateDate(new Date());
			interviewHistoryHome.getInstance().setIthStatus(candidateHome.getInstance().getCddStatus().toString());
			interviewHistoryHome.getInstance().setIthRequestNumber(candidateHome.getInstance().getCddRequestNumber());
			interviewHistoryHome.persist();
			if (candidateHome.getInstance().getCddStatus().equals(CandidateStatus.OFFER_ACCEPTED)
					|| oldStatus.equals(CandidateStatus.OFFER_ACCEPTED)) {

				RecruitRequest rr = candidateHome.getInstance().getRecruitRequest() != null ? candidateHome
						.getInstance().getRecruitRequest() : null;

				if (rr != null) {
					recruitRequestHome.setId(rr.getRcrRecruitRequestId());
					Integer fullfiledCandidate = recruitRequestHome.getInstance().getNumberRecruited();
					if (fullfiledCandidate == null) {
						fullfiledCandidate = 0;
					}
					if (fullfiledCandidate != 0) {
						if (oldStatus.equals(CandidateStatus.OFFER_ACCEPTED)) {
							recruitRequestHome.getInstance().setNumberRecruited(fullfiledCandidate - 1);
						} else {
							recruitRequestHome.getInstance().setNumberRecruited(fullfiledCandidate + 1);
						}
					} else {
						recruitRequestHome.getInstance().setNumberRecruited(1);
					}
					recruitRequestHome.getInstance().setFulfilledCandidateLastUpdate(new Date());
					recruitRequestHome.persist();
				}
			}
		}
		FacesMessages.instance().clear();
		FacesMessages.instance().addFromResourceBundle("com.bosch.candidatestatus.updated");
	}
}
