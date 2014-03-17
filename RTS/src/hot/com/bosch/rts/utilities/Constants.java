/**
 * 
 */
package com.bosch.rts.utilities;

/**
 * The Class Constants.
 *
 * @author bit71hc
 */
public class Constants {
	
	/**
	 * The Enum PrivilegeConstants.
	 */
	public enum PrivilegeConstants {
		
		/** The add interview schedule. */
		ADD_INTERVIEW_SCHEDULE, 
		
		/** The add recruit request. */
		ADD_RECRUIT_REQUEST, 
		
		/** The add candidate. */
		ADD_CANDIDATE, 
		
		/** The add technical feedback. */
		ADD_TECHNICAL_FEEDBACK, 
		
		/** The add hr feedback. */
		ADD_HR_FEEDBACK, 
		
		/** The edit interview schedule. */
		EDIT_INTERVIEW_SCHEDULE, 
		
		/** The search interview schedule. */
		SEARCH_INTERVIEW_SCHEDULE, 
		
		/** The edit candidate. */
		EDIT_CANDIDATE, 
		
		/** The search candidate. */
		SEARCH_CANDIDATE, 
		
		/** The edit recruit request. */
		EDIT_RECRUIT_REQUEST, 
		
		/** The edit technical feedback. */
		EDIT_TECHNICAL_FEEDBACK, 
		
		/** The edit hr feedback. */
		EDIT_HR_FEEDBACK, 
		
		/** The view technical feedback. */
		VIEW_TECHNICAL_FEEDBACK, 
		
		/** The view hr feedback. */
		VIEW_HR_FEEDBACK, 
		
		/** The view candidate. */
		VIEW_CANDIDATE, 
		
		/** The view recruitrequest. */
		VIEW_RECRUITREQUEST;
	}

	/**
	 * The Enum InterviewScheduleStatus.
	 */
	public enum InterviewScheduleStatus {
		
		/** The new. */
		NEW, 
		
		/** The taken. */
		TAKEN, 
		
		/** The declined. */
		DECLINED, 
		
		/** The canceled. */
		CANCELED, 
		
		/** The technical on hold. */
		TECHNICAL_ON_HOLD, 
		
		/** The hr on hold. */
		HR_ON_HOLD ;
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		public String toString() {
			String value = super.toString();
			if (value.equals("NEW")) {
				return "To Interview";
			} else if (value.equals("TAKEN")) {
				return "Interviewed";
			} else if (value.equals("DECLINED")) {
				return "Declined";
			} else if (value.equals("CANCELED")) {
				return "Canceled";
			} else if (value.equals("TECHNICAL_ON_HOLD")) {
				return "Technical On Hold";
			} else if (value.equals("HR_ON_HOLD")) {
				return "HR On Hold";				
			} else return value;
		};

		/**
		 * Status from display text.
		 *
		 * @param text the text
		 * @return the interview schedule status
		 */
		public static InterviewScheduleStatus statusFromDisplayText(String text) {

			if (text == null) {
				throw new NullPointerException();
			}
			for (InterviewScheduleStatus is : InterviewScheduleStatus.values()) {
				if (is.toString().equalsIgnoreCase(text)) {
					return is;
				}
			}
			throw new IllegalArgumentException(
					"No status with display string: " + text);

		}

		/**
		 * Db string.
		 *
		 * @return the string
		 */
		public String dbString() {
			return super.toString();
		}
	}
	
	/**
	 * The Enum TechnicalStatuses.
	 */
	public enum TechnicalStatuses {

		/** The new. */
		NEW("To Interview"),

		/** The taken. */
		TAKEN("Interviewed"),
		
		/** The declined. */
		DECLINED("Declined"),

		/** The canceled. */
		CANCELED("Canceled"),

		/** The technical on hold. */
		TECHNICAL_ON_HOLD("Technical On Hold");	
		
		/** The application. */
		private String status;

		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public String getStatus() {
			return this.status;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the status to set
		 */
		public void setStatus(final String status) {
			this.status = status;
		}
		
		/**
		 * Instantiates a new technical statuses.
		 *
		 * @param status the status
		 */
		private TechnicalStatuses(final String status) {
			this.status = status;
		}
		
	}
	
	/**
	 * The Enum HRStatuses.
	 */
	public enum HRStatuses {

		/** The new. */
		NEW("To Interview"),

		/** The taken. */
		TAKEN("Interviewed"),
		
		/** The declined. */
		DECLINED("Declined"),

		/** The canceled. */
		CANCELED("Canceled"),

		/** The hr on hold. */
		HR_ON_HOLD("HR On Hold");	
		
		/** The application. */
		private String status;

		/**
		 * Gets the status.
		 *
		 * @return the status
		 */
		public String getStatus() {
			return this.status;
		}

		/**
		 * Sets the status.
		 *
		 * @param status the status to set
		 */
		public void setStatus(final String status) {
			this.status = status;
		}
		
		/**
		 * Instantiates a new hR statuses.
		 *
		 * @param status the status
		 */
		private HRStatuses(final String status) {
			this.status = status;
		}
		
	}

}