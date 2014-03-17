package com.bosch.rts.session;

import com.bosch.rts.entity.*;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import java.util.Arrays;

/**
 * The Class InterviewHistoryList.
 */
@Name("interviewHistoryList")
public class InterviewHistoryList extends EntityQuery<InterviewHistory> {

	/** serialVersionUID. */
	private static final long serialVersionUID = -8619339073845586279L;

	/** The Constant EJBQL. */
	private static final String EJBQL = "select interviewHistory from InterviewHistory interviewHistory";

	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = { "lower(interviewHistory.ithStatus) like lower(concat(#{interviewHistoryList.interviewHistory.ithStatus},'%'))", };

	/** The interview history. */
	private InterviewHistory interviewHistory = new InterviewHistory();

	/**
	 * Instantiates a new interview history list.
	 */
	public InterviewHistoryList() {
		setEjbql(EJBQL);
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
		setMaxResults(25);
	}

	/**
	 * Gets the interview history.
	 *
	 * @return the interview history
	 */
	public InterviewHistory getInterviewHistory() {
		return interviewHistory;
	}
}
