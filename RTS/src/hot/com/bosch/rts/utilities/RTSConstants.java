/**
 * RTSConstants.java
 */
package com.bosch.rts.utilities;

import java.util.regex.Pattern;

/**
 * The Interface RTSConstants.
 * 
 * @author khb1hc
 * 
 *         SQL constants for HQL queries
 */
public interface RTSConstants {

	/** The Constant SQL_ORDER_BY. */
	public static final String SQL_ORDER_BY = " order by ";

	/** The Constant ASCENDING_ORDER. */
	public static final String ASCENDING_ORDER = "ASC";

	/** The Constant DESCENDING_ORDER. */
	public static final String DESCENDING_ORDER = "DESC";

	/** The Constant SQL_RECRUIT_REQUEST_CREATED_DATE. */
	public static final String SQL_RECRUIT_REQUEST_CREATED_DATE = "recruitRequest.createdDate";

	/** The Constant RECRUIT_REQUEST_INVALID_DATE. */
	public static final String RECRUIT_REQUEST_INVALID_DATE = "recruit.request.invalidDate";

	/** The Constant INVALID_CANDIDATE_YEAR_OF_BIRTHDAY. */
	public static final String INVALID_CANDIDATE_YEAR_OF_BIRTHDAY = "invalid.candidate.year.of.birthday";

	/** The Constant SQL_DEPARTMENT_DPM_NAME. */
	public static final String SQL_DEPARTMENT_DPM_NAME = "department.dpmName";

	/** The Constant SQL_RECRUIT_REQUEST_NAME. */
	public static final String SQL_RECRUIT_REQUEST_NAME = "recruitRequestName";

	/** The Constant SQL_PROJECT_ID. */
	public static final String SQL_PROJECT_ID = "podProjectDepartmentId";

	/** The Constant SQL_CREATED_BY. */
	public static final String SQL_CREATED_BY = "createdBy";

	/** The Constant SQL_UPDATED_BY. */
	public static final String SQL_UPDATED_BY = "updatedBy";

	/** The Constant SQL_USR_NAME. */
	public static final String SQL_USR_NAME = "usrName";

	/** The Constant USR_USER_NAME. */
	public static final String USR_USER_NAME = "usrUserName";

	/** The Constant SQL_USR_EMAIL. */
	public static final String SQL_USR_EMAIL = "usrEmail";

	/** The Constant SQL_CCD_EMAIL. */
	public static final String SQL_CCD_EMAIL = "cddEmail";

	/** The Constant SQL_CANDIDATE_ID. */
	public static final String SQL_CANDIDATE_ID = "cddCandidateId";

	/** The Constant SQL_CDD_STATUS. */
	public static final String SQL_CDD_STATUS = "cddStatus";

	/** The Constant SQL_CDD_STATUS1. */
	public static final String SQL_CDD_STATUS1 = "cddStatus1";

	/** The Constant SQL_CDD_STATUS2. */
	public static final String SQL_CDD_STATUS2 = "cddStatus2";

	/** The Constant SQL_CDD_EMAIL. */
	public static final String SQL_CDD_EMAIL = "cddEmail";

	/** The Constant SQL_CDD_PHONE_NO. */
	public static final String SQL_CDD_PHONE_NO = "cddPhoneNo";

	/** The Constant SQL_CDD_NAME. */
	public static final String SQL_CDD_NAME = "cddName";

	/** The Constant SQL_USR_FULL_NAME. */
	public static final String SQL_USR_FULL_NAME = "usrFullName";

	/** The Constant SQL_FROM_DATE. */
	public static final String SQL_FROM_DATE = "fromDate";

	/** The Constant SQL_TO_DATE. */
	public static final String SQL_TO_DATE = "toDate";

	/** The Constant SQL_ITS_STATUS. */
	public static final String SQL_ITS_STATUS = "itsStatus";

	/** The Constant SQL_ITS_TECHNICAL_STATUS. */
	public static final String SQL_ITS_TECHNICAL_STATUS = "itsTechnicalStatus";

	/** The Constant SQL_LEVEL_PATH. */
	public static final String SQL_LEVEL_PATH = "levelPath";

	/** The Constant SQL_ITS_HR_STATUS. */
	public static final String SQL_ITS_HR_STATUS = "itsHrStatus";

	/** The Constant SQL_ITS_INTERVIEW_ROUND. */
	public static final String SQL_ITS_INTERVIEW_ROUND = "itsInterviewRound";

	/** The Constant SQL_ITS_INTERVIEW_TYPE. */
	public static final String SQL_ITS_INTERVIEW_TYPE = "itsInterviewType";

	/** The Constant SQL_PRIORITY. */
	public static final String SQL_PRIORITY = "cddPriority";

	/** The Constant SQL_PHONE_NO. */
	public static final String SQL_PHONE_NO = "cddPhoneNo";

	/** The Constant SQL_DEGREE1. */
	public static final String SQL_DEGREE1 = "cddDegree1";

	/** The Constant SQL_YOE_FROM. */
	public static final String SQL_YOE_FROM = "yoeFrom";

	/** The Constant SQL_YOE_TO. */
	public static final String SQL_YOE_TO = "yoeTo";

	/** The Constant SQL_PRIVILEGE. */
	public static final String SQL_PRIVILEGE = "privilege";

	/** The Constant APPLIED_FOR_LEVEL. */
	public static final String APPLIED_FOR_LEVEL = "appliedForLevel";

	/** The Constant SQL_USER_ID_NUMBER. */
	public static final String SQL_USER_ID_NUMBER = "usrIdNumber";

	/** The Constant NEW. */
	public static final String NEW = "NEW";

	/** The Constant TAKEN. */
	public static final String TAKEN = "TAKEN";

	/** The Constant OPEN. */
	public static final String OPEN = "Open";

	/** The Constant CLOSED. */
	public static final String CLOSED = "Closed";

	/** The Constant DPM_NAME. */
	public static final String DPM_NAME = "dpmName";

	/** The Constant PROJECT_NAME. */
	public static final String PROJECT_NAME = "projectName";

	/** The Constant RECRUIT_REQUEST_NAME. */
	public static final String RECRUIT_REQUEST_NAME = "recruitRequestName";

	/** The Constant STATUS. */
	public static final String STATUS = "status";

	/** The Constant REQUESTED_DATE. */
	public static final String REQUESTED_DATE = "requestedDate";

	/** The Constant CLOSED_DATE. */
	public static final String CLOSED_DATE = "closedDate";

	/** The Constant REQUIRED_TILL. */
	public static final String REQUIRED_TILL = "requiredTill";

	/** The Constant USR_USER_ID. */
	public static final String USR_USER_ID = "usrUserId";

	/** The Constant USER_ID. */
	public static final String USER_ID = "userID";

	/** The Constant DPM_ID. */
	public static final String DPM_ID = "dpmDepartmentId";

	/** The Constant ORG_UNIT_ID. */
	public static final String ORG_UNIT_ID = "orgUnitId";

	/** The Constant PROJECT_ID. */
	public static final String PROJECT_ID = "podProjectDepartmentId";

	/** The Constant RECRUIT_REQUEST_ID. */
	public static final String RECRUIT_REQUEST_ID = "rcrRecruitRequestId";

	/** The Constant TEXT. */
	public static final String TEXT = "TEXT";

	/** The Constant NAME. */
	public static final String NAME = "name";

	/** The Constant FAILURE. */
	public static final String FAILURE = "failure";

	/** The Constant SUCCESS. */
	public static final String SUCCESS = "success";

	/** The Constant PERSISTED. */
	public static final String PERSISTED = "persisted";

	/** The Constant UPDATED. */
	public static final String UPDATED = "updated";

	/** The Constant REMOVED. */
	public static final String REMOVED = "removed";

	/** The Constant ERROR. */
	public static final String ERROR = "error";

	/** The Constant SEARCH. */
	public static final String SEARCH = "search";

	/** The Constant EDIT. */
	public static final String EDIT = "edit";

	/** The Constant VIEW. */
	public static final String VIEW = "view";

	/** The Constant ADD. */
	public static final String ADD = "add";

	/** The Constant CREATE. */
	public static final String CREATE = "create";

	/** The Constant YES. */
	public static final String YES = "yes";

	/** The Constant NO. */
	public static final String NO = "no";

	/** The Constant BLANK. */
	public static final String BLANK = "";

	/** The Constant SPACER. */
	public static final String SPACER = " ";

	/** The Constant SEMI_COLON. */
	public static final String SEMI_COLON = ";";

	/** The Constant OTHER. */
	public static final String OTHER = "other";

	/** The Constant BACK_SLASH. */
	public static final String BACK_SLASH = "\\";

	/** The Constant LEFT_BRACKET. */
	public static final char LEFT_BRACKET = '(';

	/** The Constant RIGHT_BRACKET. */
	public static final char RIGHT_BRACKET = ')';

	/** The Constant COLON. */
	public static final char COLON = ',';

	/** The Constant SUGGESTION_VALUES. */
	public static final String SUGGESTION_VALUES = "suggestionValues";

	/** The Constant CANDIDATE_DOCS_STORAGE. */
	public static final String CANDIDATE_DOCS_STORAGE = "com.bosch.storage.candidate_doc";

	/** The Constant DATE_PATTERN_IMPORT_CANDIDATE. */
	public static final String DATE_PATTERN_IMPORT_CANDIDATE = "dd/MM/yyyy HH:mm";

	/** The Constant SCH_ALL_ROUND. */
	public static final String SCH_ALL_ROUND = "All Round";

	/** The Constant SCH_TECHNICAL. */
	public static final String SCH_TECHNICAL = "Technical";

	/** The Constant SCH_HR. */
	public static final String SCH_HR = "HR";

	/** The Constant SCH_ALL_STATUS. */
	public static final String SCH_ALL_STATUS = "All Status";

	/** The Constant ROUND_TO_INTERVIEW. */
	public static final String ROUND_TO_INTERVIEW = "To Interview";

	/** The Constant ROUND_NEW. */
	public static final String ROUND_NEW = "NEW";

	/** The Constant ROUND_INTERVIEWED. */
	public static final String ROUND_INTERVIEWED = "Interviewed";

	/** The Constant ROUND_TAKEN. */
	public static final String ROUND_TAKEN = "TAKEN";

	/** The Constant ROUND_DECLINED. */
	public static final String ROUND_DECLINED = "Declined";

	/** The Constant ROUND_CANCELED. */
	public static final String ROUND_CANCELED = "Canceled";

	/** The Constant NAME_VALIDATION. */
	public static final String NAME_VALIDATION = "Empty Name";

	/** The Constant NAME_INVALID. */
	public static final String NAME_INVALID = "Invalid Name";

	/** The Constant EMAIL_VALIDATION. */
	public static final String EMAIL_VALIDATION = "Empty Email";

	/** The Constant EMAIL_VALIDATION_2. */
	public static final String EMAIL_VALIDATION_2 = "Invalid Email";

	/** The Constant VALID_EMAIL_ADDRESS_REGEX. */
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = java.util.regex.Pattern
			.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]+$",
					Pattern.CASE_INSENSITIVE);

	/** The Constant VALID_STRING. */
	public static final Pattern VALID_STRING = java.util.regex.Pattern
			.compile("^[A-Za-z0-9 -/()]*$");

	/** The Constant UNIT_NAME. */
	public static final String UNIT_NAME = "unitName";

	/** The Constant TODAY. */
	public static final String TODAY = "today";

	/** The Constant DEPT_ID. */
	public static final String DEPT_ID = "deptID";

	/** The Constant LEVEL_48_TO_51. */
	public static final String LEVEL_48_TO_51 = "Level 48 to 51";

	/** The Constant LEVEL_52_TO_53. */
	public static final String LEVEL_52_TO_53 = "Level 52 to 53";

	/** The Constant LEVEL_54_TO_56. */
	public static final String LEVEL_54_TO_56 = "Level 54 to 56";

	/** The Constant LEVEL_57. */
	public static final String LEVEL_57 = "Level 57";

	/** The Constant USER_NAME_IMPORT. */
	public static final String USER_NAME_IMPORT = "Imported";

	/** The Constant ITS_INTERVIEW_SCHEDULE_ID. */
	public static final String ITS_INTERVIEW_SCHEDULE_ID = "itsInterviewScheduleId";

	/** The Constant ATTRIBUTE_ID. */
	public static final String ATTRIBUTE_ID = "attributeId";

	/** The Constant PASSED. */
	public static final String PASSED = "Passed";

	/** The Constant FAIL. */
	public static final String FAIL = "Fail";

	/** The Constant MAX_COLUMNS. */
	public static final int MAX_COLUMNS = 30;

	/** The Constant SKL_NAME. */
	public static final String SKL_NAME = "sklName";

	/** The Constant LEVEL_DEFAULT. */
	public static final int LEVEL_DEFAULT = 1;

	/** The Constant SKILL_NAME_LENGTH. */
	public static final int SKILL_NAME_LENGTH = 25;

	/** The Constant EXCEL_FILE. */
	public static final String EXCEL_FILE = ".xls";

	/** The Constant EXCEL_2007_FILE. */
	public static final String EXCEL_2007_FILE = ".xlsx";

	public static final String SQL_APPROVED_BY = "approvedBy";

	public static final String ASSIGNED = "assigned";

	public static final int USER_APPOVER_RIGHT_NUMBER = 8;

	public static final int RECRUITMENT_APPOVER_RIGHT_NUMBER = 9;

}