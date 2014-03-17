package com.bosch.rts.utilities;

import java.io.Serializable;
import java.util.Map;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

/**
 * The Class CandidatePriorityHelper.
 */
@Name("candidatePriorityHelper")
@Scope(ScopeType.SESSION)
public class CandidatePriorityHelper implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5163312806171325683L;
	
	/** The Constant priorityString. */
	private static final String[] priorityString = { "low", "normal", "high" };
	
	/** The messages. */
	@In
	Map<String, String> messages;

	/**
	 * Gets the priority str.
	 *
	 * @param priority the priority
	 * @return the priority str
	 */
	public String getPriorityStr(Integer priority) {
		if (priority != null && priority >= 1 && priority <= 3) {
			return messages.get("com.bosch.ui." + priorityString[priority - 1]);
		}
		return "";
	}
}
