package com.bosch.rts.utilities;

import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;
import javax.servlet.http.HttpServletResponse;

/**
 * The listener interface for receiving cacheControlPhase events.
 * The class that is interested in processing a cacheControlPhase
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addCacheControlPhaseListener<code> method. When
 * the cacheControlPhase event occurs, that object's appropriate
 * method is invoked.
 *
 * @see CacheControlPhaseEvent
 */
public class CacheControlPhaseListener implements PhaseListener {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3118818508193718033L;

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#afterPhase(javax.faces.event.PhaseEvent)
	 */
	public void afterPhase(PhaseEvent event) {
	}

	/* (non-Javadoc)
	 * @see javax.faces.event.PhaseListener#beforePhase(javax.faces.event.PhaseEvent)
	 */
	public void beforePhase(PhaseEvent event) {
		FacesContext facesContext = event.getFacesContext();
		HttpServletResponse response = (HttpServletResponse) facesContext
				.getExternalContext().getResponse();
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");
		// Stronger according to blog comment below that references HTTP spec
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Cache-Control", "must-revalidate");
		// some date in the past
		response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
	}
}
