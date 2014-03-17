package com.bosch.rts.session;

import java.util.List;

import org.jboss.seam.Component;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Observer;
import org.jboss.seam.annotations.async.Asynchronous;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.faces.Renderer;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.UserHasUserRole;
import com.bosch.rts.entity.UserRole;

/**
 * The listener interface for receiving recruitRequestEvent events.
 * The class that is interested in processing a recruitRequestEvent
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addRecruitRequestEventListener<code> method. When
 * the recruitRequestEvent event occurs, that object's appropriate
 * method is invoked.
 *
 * @see RecruitRequestEventEvent
 */
@Name("recruitRequestEventListener")
public class RecruitRequestEventListener {
	
	/** The log. */
	@Logger
	private transient Log log;

	/**
	 * On recruit request update.
	 *
	 * @param recruitRequestId the recruit request id
	 */
	@Observer("recruitRequestUpdated")
	public void onRecruitRequestUpdate(Integer recruitRequestId) {
		// sendUpdateEmail(recruitRequestId);

	}

	/**
	 * Invoked when on recruit request is created.
	 *
	 * @param recruitRequestId the recruit request id
	 */
	@Observer("recruitRequestCreated")
	public void onRecruitRequestCreated(Integer recruitRequestId) {
		// sendCreatedEmail(recruitRequestId);
	}

	/**
	 * Send created email.
	 *
	 * @param recruitRequestId the recruit request id
	 */
	@Asynchronous
	public void sendCreatedEmail(Object recruitRequestId) {
		try {
			RecruitRequestHome recruitRequestHome = (RecruitRequestHome) Component
					.getInstance(RecruitRequestHome.class, true);
			recruitRequestHome.setId(recruitRequestId);
			Context context = Contexts.getEventContext();
			context.set("hrMails", getUserHasRoleHr());
			context.set("mailSubject", "[RTS] New Request Created");
			String downloadPath = ((String) Component.getInstance("basePath"))
					+ "ResourceProvider.seam?recruitRequestId=";
			Contexts.getEventContext().set("downloadPath", downloadPath);
			RecruitRequestEventListener.instance().renderMail();
		} catch (Exception e) {
			log.error("Execute sendCreatedEmail method getting error------------------");
		}
	}

	/**
	 * Send update email.
	 *
	 * @param recruitRequestId the recruit request id
	 */
	@Asynchronous
	public void sendUpdateEmail(Object recruitRequestId) {
		try {
			RecruitRequestHome recruitRequestHome = (RecruitRequestHome) Component
					.getInstance(RecruitRequestHome.class, true);
			recruitRequestHome.setId(recruitRequestId);
			Context context = Contexts.getEventContext();
			context.set("hrMails", getUserHasRoleHr());
			context.set("mailSubject", "[RTS] Request "
					+ recruitRequestHome.getInstance().getRecruitRequestName()
					+ " Updated");
			String downloadPath = ((String) Component.getInstance("basePath"))
					+ "ResourceProvider.seam?recruitRequestId=";
			Contexts.getEventContext().set("downloadpath", downloadPath);
			RecruitRequestEventListener.instance().renderMail();
		} catch (Exception e) {
			log.error("Execute sendUpdateEmail method getting error------------------");
		}
	}

	/**
	 * Gets the user has role hr.
	 *
	 * @return the user has role hr
	 */
	private List<UserHasUserRole> getUserHasRoleHr() {
		UserRoleList userRoleList = (UserRoleList) Component.getInstance(
				UserRoleList.class, true);
		userRoleList.getUserRole().setUsrRoleName("HR");
		List<UserRole> userRoles = userRoleList.getResultList();
		UserRole userRole = userRoles.get(0);
		UserHasUserRoleList userHasUserRoleList = (UserHasUserRoleList) Component
				.getInstance(UserHasUserRoleList.class, true);
		userHasUserRoleList.getUserHaseUserRole().setUserRole(userRole);
		return userHasUserRoleList.getResultList();
	}

	/**
	 * Render mail.
	 */
	public void renderMail() {
		Renderer.instance().render(
				"/mail template/recruitRequestCreatedMail.xhtml");
	}

	/**
	 * Instance.
	 *
	 * @return the recruit request event listener
	 */
	public static RecruitRequestEventListener instance() {
		return (RecruitRequestEventListener) Component
				.getInstance(RecruitRequestEventListener.class);
	}

}
