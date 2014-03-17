package com.bosch.rts.bus;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;

import javax.transaction.NotSupportedException;
import javax.transaction.SystemException;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.transaction.Transaction;
import org.jboss.seam.transaction.UserTransaction;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.Faculty;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.entity.University;
import com.bosch.rts.entity.User;
import com.bosch.rts.entity.UserHasInterviewSchedule;
import com.bosch.rts.session.CandidateHome;
import com.bosch.rts.session.FacultyList;
import com.bosch.rts.session.InterviewScheduleHome;
import com.bosch.rts.session.SkillHome;
import com.bosch.rts.session.SkillList;
import com.bosch.rts.session.UniversityList;
import com.bosch.rts.session.UserHome;
import com.bosch.rts.utilities.ParseExcelUtil;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.utilities.UploadFileInfo;

/**
 * The Class ImportExcelData.
 */
@Name("importExcelData")
@Scope(ScopeType.EVENT)
public class ImportExcelData implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 1470067141616615999L;

	/** The candidate home. */
	@In(create = true)
	private transient CandidateHome candidateHome;

	/** The interview schedule home. */
	@In(create = true)
	private transient InterviewScheduleHome interviewScheduleHome;

	/** The user home. */
	@In(create = true)
	private transient UserHome userHome;
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;

	/** The university list. */
	@In(create = true, required = false)
	private transient UniversityList universityList;

	/** The faculty list. */
	@In(create = true, required = false)
	private transient FacultyList facultyList;

	/** The skill home. */
	@In(create = true, required = false)
	private transient SkillHome skillHome;

	/** The skill list. */
	@In(create = true, required = false)
	private transient SkillList skillList;

	/** The file. */
	private transient UploadFileInfo file;
	
	/** The is file uploaded. */
	private boolean isFileUploaded;

	/** The candidates. */
	private List<Candidate> candidates;
	
	/** The interview schedules. */
	private List<InterviewSchedule> interviewSchedules;

	/** The data holder2003. */
	private Vector<Vector<HSSFCell>> dataHolder2003;
	
	/** The data holder2007. */
	private Vector<Vector<XSSFCell>> dataHolder2007;

	/** The successful records no. */
	private int successfulRecordsNo;
	
	/** The fail record. */
	StringBuffer failRecord = null;

	/**
	 * Instantiates a new import excel data.
	 */
	public ImportExcelData() {
		candidates = new ArrayList<Candidate>();
		interviewSchedules = new ArrayList<InterviewSchedule>();
		dataHolder2003 = new Vector<Vector<HSSFCell>>();
		dataHolder2007 = new Vector<Vector<XSSFCell>>();
		file = new UploadFileInfo();
	}

	/**
	 * Upload candidate from Excel file.
	 *
	 * @return the string
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String uploadFile() throws IOException {
		statusMessages.clear();
		String validateMsg = null;
		String returnMsg = RTSConstants.FAILURE;
		failRecord = new StringBuffer();

		final String fileName = file.getName() != null ? file.getName() : null;

		if (file == null || StringUtils.isBlank(fileName)) {
			validateMsg = "com.bosch.rts.bus.ImportExcelData.FileRequired";
		}

		if (StringUtils.isBlank(validateMsg) && fileName != null
				&& !(fileName.endsWith("xls") || fileName.endsWith("xlsx"))) {
			validateMsg = "com.bosch.rts.bus.ImportExcelData.FileInvalid";
		}

		if (StringUtils.isBlank(validateMsg)) {
			try {				
				this.clearDataHolder();

				// Excel 1997-2003
				if (file.getName().endsWith("xls")) {
					/** Read an Excel File and Store in a Vector **/
					this.dataHolder2003 = ParseExcelUtil.readExcel2003File(file);
					if (dataHolder2003 != null && dataHolder2003.size() > 0) {
						parseData2003(dataHolder2003);						
					} else {
						validateMsg = "com.bosch.rts.bus.ImportExcelData.FileEmpty";
					}
				}

				// Excel 2007
				if (file.getName().endsWith("xlsx")) {
					/** Read an Excel File and Store in a Vector **/
					this.dataHolder2007 = ParseExcelUtil.readExcel2007File(file);
					if (dataHolder2007 != null && dataHolder2007.size() > 0) {
						parseData2007(dataHolder2007);
					} else {
						validateMsg = "com.bosch.rts.bus.ImportExcelData.FileEmpty";
					}
				}

				if (RTSUtils.isNotEmpty(candidates) && RTSUtils.isNotEmpty(interviewSchedules)) {
					saveData();				
				}
				
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.INFO,
						"com.bosch.rts.bus.ImportExcelData.successfulRecords", successfulRecordsNo);

				if (!StringUtils.isBlank(failRecord)) {
					
					final int failRecordLength = failRecord.length();
					if(failRecordLength > 1){
						statusMessages.addFromResourceBundle(Severity.ERROR,
								"com.bosch.rts.bus.ImportExcelData.failedNames", failRecord.substring(0, failRecordLength - 2));
					}else{
						statusMessages.addFromResourceBundle(Severity.ERROR,
								"com.bosch.rts.bus.ImportExcelData.failedNames", failRecord);
					}
					
				}
				
				returnMsg = RTSConstants.SUCCESS;
			} catch (Exception e) {
				returnMsg = RTSConstants.FAILURE;
			}
		}

		if (!StringUtils.isBlank(validateMsg)) {
			statusMessages.addFromResourceBundle(Severity.ERROR, validateMsg);
		}

		return returnMsg;
	}
	
	/*@In
	private EntityManager entityManager;*/
	
	/**
	 * Save data.
	 *
	 * @throws SecurityException the security exception
	 * @throws IllegalStateException the illegal state exception
	 */
	private void saveData() throws SecurityException, IllegalStateException{
		int i = -1;
		UserTransaction userTransaction = Transaction.instance();
		for (Candidate candidate : candidates) {
			i++;
			if (checkConditionOfCandidate(candidate)) {		
				try {
					userTransaction.begin();
				} catch (NotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SystemException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {					
					// Create new University
					final String university = candidate.getCddGraduationUniversity() != null ? candidate
							.getCddGraduationUniversity() : null;
					final String email = candidate.getCddEmail() != null ? candidate.getCddEmail() : null;

					final Candidate existingCandidate = candidateHome.checkEmailExisted(email);
					String updateStatus = null;

					// insert University
					if (!StringUtils.isBlank(university)) {
						if (!isExistingUniversity(university)) {
							candidateHome.saveNewUniversity(university);
						}
					}

					if (existingCandidate == null) {
						updateStatus = this.persistCandidate(candidate);
					} else {
						updateStatus = this.updateCandidate(candidate, existingCandidate);
					}

					if (updateStatus.equalsIgnoreCase(RTSConstants.PERSISTED)) {
						// insert interview schedule
						final InterviewSchedule interviewSchedule = interviewSchedules.get(i);
						this.persistInterviewSchedule(interviewSchedule, candidate);
					}

					if (!updateStatus.equalsIgnoreCase(RTSConstants.FAILURE)) {
						successfulRecordsNo++;
					}
					userTransaction.commit();
					
				} catch (Exception e) {
					try {
						userTransaction.rollback();
					} catch (SystemException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
							
			}else{
				failRecord.append(i + 1 + ", ");
			}
		}
	}

	/**
	 * Parses the data2003.
	 *
	 * @param dataHolder the data holder
	 */
	private void parseData2003(Vector<Vector<HSSFCell>> dataHolder) {
		/** Variables for every column in excel **/
		Date interviewTime = null;
		String interviewer = null;

		/**
		 * Get data of every vector of dataHolder as a record to insert into
		 * database
		 **/
		int dataHolderLength = dataHolder.size();
		for (int i = 0; i < dataHolderLength; i++) {
			Vector<HSSFCell> cellStoreVector = (Vector<HSSFCell>) dataHolder.elementAt(i);
			Candidate candidate = new Candidate();
			InterviewSchedule interviewSchedule = new InterviewSchedule();

			int cellStoreVectorLength = cellStoreVector.size();
			for (int j = 0; j < cellStoreVectorLength; j++) {
				HSSFCell myCell = (HSSFCell) cellStoreVector.elementAt(j);
				Object result = ParseExcelUtil.getCellValue2003(myCell);
				if (result == null){
					continue;
				}				
				String temp = result.toString().trim();
				/**
				 * Collecting a data row as a record, and inserting into
				 * database
				 **/
				if ((i + 1) > 0) {					
					switch (j) {
					case 0: // Sl NO
						break;
					case 1: // Source
						candidate.setCddSource(temp.length() > 25 ? temp.substring(0, 25) : temp);
						break;

					case 2: // Referral
						User referrer = null;
						if (candidate.getCddSource() != null && candidate.getCddSource().equalsIgnoreCase("Referral")) {
							referrer = userHome.findUserByUsername(temp.length() > 10 ? temp.substring(0, 10) : temp);
						} else {
							candidate.setJP_Consultancy_Name(temp);
						}
						if (referrer != null) {
							candidate.setCddEmployeeId(referrer.getUsrIdNumber());
							candidate.setCddEmployeeName(referrer.getUsrFullName());
						}
						break;

					case 3: // Prefix
						candidate.setCddPrefix(temp.length() > 5 ? temp.substring(0, 5) : temp);
						break;

					case 4: // Name
						candidate.setCddName(temp.length() > 50 ? temp.substring(0, 50) : temp);
						break;

					case 5: // Address
						candidate.setCddAddress(temp.length() > 100 ? temp.substring(0, 100) : temp);
						break;

					case 6: // Email
						final String email = temp.length() > 50 ? temp.substring(temp.length() - 50, temp.length())
								: temp;

						if (!StringUtils.isBlank(email)) {
							final Matcher emailMatcher = RTSConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
							if (emailMatcher.find()) {
								candidate.setCddEmail(email.toLowerCase());
							}
						}

						/*candidate.setCddEmail(email.toLowerCase());*/
						break;

					case 7: // Contact NO
						candidate.setCddPhoneNo(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 8: // Graduated degreee 1
						candidate.setCddDegree1(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 9: // Graduated university 1
						candidate.setCddGraduationUniversity(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 10: // Graduated year 1
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 11: // Graduated degreee 2
						candidate.setCddDegree2(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 12: // Graduated university 2
						candidate.setCddGraduationUniversity2(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 13: // Graduated year 2
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation2(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 14: // Graduated degreee 3
						candidate.setCddDegree3(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 15: // Graduated university 3
						candidate.setCddGraduationUniversity3(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 16: // Graduated year 3
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation3(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 17: // Year of Experience (overall)
						try {
							candidate.setCddYearOfExperience(Float.parseFloat(temp));
						} catch (NumberFormatException ex) {
							candidate.setCddYearOfExperience(Float.valueOf(0));
						}
						break;

					case 18: // Relevant Experience
						try {
							candidate.setCddRelevantExperience(Float.parseFloat(temp));
						} catch (NumberFormatException ex) {
							candidate.setCddRelevantExperience(Float.valueOf(0));
						}
						break;
					case 19: // Skill set
						candidate.setCddSkillSet(temp);
						break;

					case 20: // Interview Date
						try {
							if (result != null) {
								interviewTime = (Date) result;
							}
						} catch (Exception e) {
							log.error("parse interview date getting error");
						}

						break;

					case 21: // Time
						if (interviewTime != null) {
							try {
								Calendar cal = Calendar.getInstance();
								cal.setTime(interviewTime);
								cal.set(Calendar.HOUR_OF_DAY, getTime(1, temp));
								cal.set(Calendar.MINUTE, getTime(2, temp));
								cal.set(Calendar.SECOND, 0);
								interviewSchedule.setItsTechnicalInterviewTime(cal.getTime());
							} catch (Exception ex) {
								interviewSchedule.setItsTechnicalInterviewTime(new Date());
							}
						}
						break;

					case 22: // Interview Schedule Status
						interviewSchedule.setItsTechnicalStatus(temp.length() > 20 ? temp.substring(0, 20)
								.toUpperCase() : temp);
						interviewSchedule.setItsHrStatus(temp.length() > 20 ? temp.substring(0, 20).toUpperCase()
								: temp);
						break;

					case 23: // Interviewer Comments
						interviewSchedule.setItsTechnicalRemark(temp.length() > 200 ? temp.substring(0, 200) : temp);
						break;

					case 24: // priority
						int optionValuee;
						try {
							optionValuee = (int) Float.parseFloat(temp);
						} catch (NumberFormatException ex) {
							optionValuee = 2;
						}

						if (optionValuee < 1 || optionValuee > 4) {
							candidate.setCddPriority(2);
						} else {
							candidate.setCddPriority(optionValuee);
						}

						break;

					case 25: // Final Status
						final CandidateStatus candidateStatus = getCandidateStatus(temp.length() > 20 ? temp.substring(
								0, 20) : temp);
						candidate.setCddStatus(candidateStatus);
						break;

					case 26: // Descriptions
						candidate.setCddSkillDescription(temp.length() > 400 ? temp.substring(0, 400) : temp);
						break;

					case 27: // Interviewer list
						interviewer = temp;
						final String[] interviewers = interviewer.split(RTSConstants.SEMI_COLON);
						for (String intervier : interviewers) {
							intervier = intervier.trim();
							final User user = userHome.findUserByUsername(intervier);
							if (user != null && user.getUsrUserName() != null && !user.getUsrUserName().isEmpty()) {
								final UserHasInterviewSchedule uhIn = new UserHasInterviewSchedule(user);
								interviewSchedule.getUserHasInterviewSchedules().add(uhIn);
							}
						}
						break;

					case 28: // Recruit Request REF
						break;

					case 29: // Interview method
						interviewSchedule.setItsTechnicalInterviewType(temp.length() > 10 ? temp.substring(0, 10)
								: temp);
						break;

					default:
						break;
					}// end SWITCH
				}// end if (row > 0)
			}// end FOR j
			if (candidate != null) {
				if(!StringUtils.isBlank(candidate.getCddEmail())){
					final int index = candidates.indexOf(candidate);
					if (index >= 0) {
						candidates.remove(index);
					}
				}			
				candidates.add(candidate);
				interviewSchedules.add(interviewSchedule);
			}			
		}// end FOR i
	}

	/**
	 * Parses the data2007.
	 *
	 * @param dataHolder the data holder
	 */
	private void parseData2007(Vector<Vector<XSSFCell>> dataHolder) {
		/** Variables for every column in excel **/
		Date interviewTime = null;
		String interviewer = null;

		/**
		 * Get data of every vector of dataHolder as a record to insert into
		 * database
		 **/
		int dataHolderLength = dataHolder.size();
		for (int i = 0; i < dataHolderLength; i++) {
			Vector<XSSFCell> cellStoreVector = (Vector<XSSFCell>) dataHolder.elementAt(i);
			Candidate candidate = new Candidate();
			InterviewSchedule interviewSchedule = new InterviewSchedule();
			int cellStoreVectorLength = cellStoreVector.size();
			for (int j = 0; j < cellStoreVectorLength; j++) {
				XSSFCell myCell = (XSSFCell) cellStoreVector.elementAt(j);
				Object result = ParseExcelUtil.getCellValue2007(myCell);
				if (result == null)
					continue;
				/**
				 * Collecting a data row as a record, and inserting into
				 * database
				 **/
				if ((i + 1) > 0) {
					final String temp = result.toString().trim();
					switch (j) {
					case 0: // Sl NO
						break;
					case 1: // Source
						candidate.setCddSource(temp.length() > 25 ? temp.substring(0, 25) : temp);
						break;

					case 2: // Referral
						User referrer = null;
						if (candidate.getCddSource() != null && candidate.getCddSource().equalsIgnoreCase("Referral")) {
							referrer = userHome.findUserByUsername(temp.length() > 10 ? temp.substring(0, 10) : temp);
						} else {
							candidate.setJP_Consultancy_Name(temp);
						}
						if (referrer != null) {
							candidate.setCddEmployeeId(referrer.getUsrIdNumber());
							candidate.setCddEmployeeName(referrer.getUsrFullName());
						}
						break;

					case 3: // Prefix
						candidate.setCddPrefix(temp.length() > 5 ? temp.substring(0, 5) : temp);
						break;

					case 4: // Name
						candidate.setCddName(temp.length() > 50 ? temp.substring(0, 50) : temp);
						break;

					case 5: // Address
						candidate.setCddAddress(temp.length() > 100 ? temp.substring(0, 100) : temp);
						break;

					case 6: // Email
						final String email = temp.length() > 50 ? temp.substring(temp.length() - 50, temp.length())
								: temp;
						if (!StringUtils.isBlank(email)) {
							final Matcher emailMatcher = RTSConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(email);
							if (emailMatcher.find()) {
								candidate.setCddEmail(email.toLowerCase());
							}
						}						 
						
						/*candidate.setCddEmail(email.toLowerCase());*/
						break;

					case 7: // Contact NO
						candidate.setCddPhoneNo(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 8: // Graduated degreee 1
						candidate.setCddDegree1(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 9: // Graduated university 1
						candidate.setCddGraduationUniversity(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 10: // Graduated year 1
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 11: // Graduated degreee 2
						candidate.setCddDegree2(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 12: // Graduated university 2
						candidate.setCddGraduationUniversity2(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 13: // Graduated year 2
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation2(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 14: // Graduated degreee 3
						candidate.setCddDegree3(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 15: // Graduated university 3
						candidate.setCddGraduationUniversity3(temp.length() > 30 ? temp.substring(0, 30) : temp);
						break;

					case 16: // Graduated year 3
						try {
							final Date graduatedYear = (Date) result;
							final Calendar cal = Calendar.getInstance();
							cal.setTime(graduatedYear);

							candidate.setCddYearOfGraduation3(cal.getTime());
						} catch (Exception e) {
							log.equals("Cannot parse Graduated year 1");
						}
						break;

					case 17: // Year of Experience (overall)
						try {
							candidate.setCddYearOfExperience(Float.parseFloat(temp));
						} catch (NumberFormatException ex) {
							candidate.setCddYearOfExperience(Float.valueOf(0));
						}
						break;

					case 18: // Relevant Experience
						try {
							candidate.setCddRelevantExperience(Float.parseFloat(temp));
						} catch (NumberFormatException ex) {
							candidate.setCddRelevantExperience(Float.valueOf(0));
						}
						break;
					case 19: // Skill set
						candidate.setCddSkillSet(temp);
						break;

					case 20: // Interview Date
						try {
							if (result != null) {
								interviewTime = (Date) result;
							}
						} catch (Exception e) {
							log.error("parse interview date getting error");
						}

						break;

					case 21: // Time
						if (interviewTime != null) {
							try {
								Calendar cal = Calendar.getInstance();
								cal.setTime(interviewTime);
								cal.set(Calendar.HOUR_OF_DAY, getTime(1, temp));
								cal.set(Calendar.MINUTE, getTime(2, temp));
								cal.set(Calendar.SECOND, 0);
								interviewSchedule.setItsTechnicalInterviewTime(cal.getTime());
							} catch (Exception ex) {
								interviewSchedule.setItsTechnicalInterviewTime(new Date());
							}
						}
						break;

					case 22: // Interview Schedule Status
						interviewSchedule.setItsTechnicalStatus(temp.length() > 20 ? temp.substring(0, 20)
								.toUpperCase() : temp);
						interviewSchedule.setItsHrStatus(temp.length() > 20 ? temp.substring(0, 20).toUpperCase()
								: temp);
						break;

					case 23: // Interviewer Comments
						interviewSchedule.setItsTechnicalRemark(temp.length() > 200 ? temp.substring(0, 200) : temp);
						break;

					case 24: // priority
						int optionValuee;
						try {
							optionValuee = (int) Float.parseFloat(temp);
						} catch (NumberFormatException ex) {
							optionValuee = 2;
						}

						if (optionValuee < 1 || optionValuee > 4) {
							candidate.setCddPriority(2);
						} else {
							candidate.setCddPriority(optionValuee);
						}

						break;

					case 25: // Final Status
						final CandidateStatus candidateStatus = getCandidateStatus(temp.length() > 20 ? temp.substring(
								0, 20) : temp);
						candidate.setCddStatus(candidateStatus);
						break;

					case 26: // Descriptions
						candidate.setCddSkillDescription(temp.length() > 400 ? temp.substring(0, 400) : temp);
						break;

					case 27: // Interviewer list
						interviewer = temp;
						final String[] interviewers = interviewer.split(RTSConstants.SEMI_COLON);
						for (String intervier : interviewers) {
							intervier = intervier.trim();
							final User user = userHome.findUserByUsername(intervier);
							if (user != null && user.getUsrUserName() != null && !user.getUsrUserName().isEmpty()) {
								final UserHasInterviewSchedule uhIn = new UserHasInterviewSchedule(user);
								interviewSchedule.getUserHasInterviewSchedules().add(uhIn);
							}
						}
						break;

					case 28: // Recruit Request REF
						break;

					case 29: // Interview method
						interviewSchedule.setItsTechnicalInterviewType(temp.length() > 10 ? temp.substring(0, 10)
								: temp);
						break;

					default:
						break;
					}// end SWITCH
				}// end if (row > 0)
			}// end FOR j
			if (candidate != null) {
				if(!StringUtils.isBlank(candidate.getCddEmail())){
					final int index = candidates.indexOf(candidate);
					if (index >= 0) {
						candidates.remove(index);
					}
				}			
				candidates.add(candidate);
				interviewSchedules.add(interviewSchedule);
			}	
		}// end FOR i
	}

	/**
	 * Gets the candidate status.
	 *
	 * @param cdd_status the cdd_status
	 * @return the candidate status
	 */
	private CandidateStatus getCandidateStatus(String cdd_status) {
		if (cdd_status.equalsIgnoreCase("NEW")) {
			return CandidateStatus.NEW;
		}

		if (cdd_status.equalsIgnoreCase("Scheduled")) {
			//return CandidateStatus.SCHEDULED;
			return CandidateStatus.TECHNICAL_SCHEDULED;
		}

		if (cdd_status.equalsIgnoreCase("TECHNICAL_PASS"))
			return CandidateStatus.TECHNICAL_PASS;

		if (cdd_status.equalsIgnoreCase("TECHNICAL_FAILED"))
			return CandidateStatus.TECHNICAL_FAIL;

		if (cdd_status.equalsIgnoreCase("HR_PASS"))
			return CandidateStatus.HR_PASS;

		if (cdd_status.equalsIgnoreCase("HR_FAILED"))
			return CandidateStatus.HR_FAIL;

		if (cdd_status.equalsIgnoreCase("ON_HOLD"))
			/*return CandidateStatus.ON_HOLD;*/
			return CandidateStatus.TECHNICAL_ON_HOLD;

		if (cdd_status.equalsIgnoreCase("SELECTED"))
			return CandidateStatus.SELECTED;

		if (cdd_status.equalsIgnoreCase("OFFERED"))
			return CandidateStatus.OFFERED;

		if (cdd_status.equalsIgnoreCase("OFFER_ACCEPTED"))
			return CandidateStatus.OFFER_ACCEPTED;

		if (cdd_status.equalsIgnoreCase("OFFER_REFUSED"))
			return CandidateStatus.OFFER_REFUSED;

		if (cdd_status.equalsIgnoreCase("JOINED"))
			return CandidateStatus.JOINED;

		return null;
	}

	/**
	 * Check condition of candidate.
	 *
	 * @param candidate the candidate
	 * @return true, if successful
	 */
	private boolean checkConditionOfCandidate(final Candidate candidate) {
		if (candidate != null) {
			final String name = candidate.getCddName() != null ? candidate.getCddName() : null;
			final String email = candidate.getCddEmail() != null ? candidate.getCddEmail() : null;
			final String prefix = candidate.getCddPrefix() != null ? candidate.getCddPrefix() : null;
			CandidateStatus candidateStatus = candidate.getCddStatus() != null ? candidate.getCddStatus() : null;

			if (!StringUtils.isBlank(name) && !StringUtils.isBlank(email) && !StringUtils.isBlank(prefix)
					&& candidateStatus != null && !StringUtils.isBlank(candidateStatus.toString())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return candidates;
	}

	/**
	 * Sets the candidates.
	 *
	 * @param candidates the new candidates
	 */
	public void setCandidates(List<Candidate> candidates) {
		this.candidates = candidates;
	}

	/**
	 * Checks if is file uploaded.
	 *
	 * @return true, if is file uploaded
	 */
	public boolean isFileUploaded() {
		return isFileUploaded;
	}

	/**
	 * Sets the file uploaded.
	 *
	 * @param isFileUploaded the new file uploaded
	 */
	public void setFileUploaded(boolean isFileUploaded) {
		this.isFileUploaded = isFileUploaded;
	}

	/**
	 * Gets the file.
	 *
	 * @return the file
	 */
	public UploadFileInfo getFile() {
		return file;
	}

	/**
	 * Sets the file.
	 *
	 * @param file the new file
	 */
	public void setFile(UploadFileInfo file) {
		this.file = file;
	}

	/**
	 * Insert interview schedule.
	 *
	 * @param candidate the candidate
	 * @param interviewSchedule the interview schedule
	 * @throws Exception the exception
	 */
	public void insertInterviewSchedule(Candidate candidate, InterviewSchedule interviewSchedule) throws Exception {
		interviewScheduleHome.clearInstance();
		interviewSchedule.setCandidate(candidate);

		if (interviewSchedule.getRecruitRequest() == null) {
			if (candidate.getRecruitRequest() != null) {
				interviewSchedule.setRecruitRequest(candidate.getRecruitRequest());
			}
		}

		interviewSchedule.setItsCreatedOn(new Date());
		interviewSchedule.setItsCreatedBy(RTSConstants.USER_NAME_IMPORT);

		interviewScheduleHome.setInstance(interviewSchedule);
		interviewScheduleHome.persist();		

		/* Add interviewers for interview schedule */
		final Set<UserHasInterviewSchedule> hasInterviewSchedules = interviewScheduleHome.getInstance()
				.getUserHasInterviewSchedules();
		if (RTSUtils.isNotEmpty(hasInterviewSchedules)) {
			for (UserHasInterviewSchedule uhis : hasInterviewSchedules) {
				uhis.setInterviewSchedule(interviewSchedule);
				interviewScheduleHome.getEntityManager().persist(uhis);
			}
		}
		statusMessages.clear();
	}

	/** The current page. */
	private int currentPage;

	/**
	 * Gets the current page.
	 *
	 * @return the current page
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * Sets the current page.
	 *
	 * @param currentPage the new current page
	 */
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/**
	 * Checks if is existing university.
	 *
	 * @param name the name
	 * @return true, if is existing university
	 */
	public boolean isExistingUniversity(String name) {
		boolean result = false;

		List<University> universities = universityList.getUniversityByName(name);
		if (RTSUtils.isNotEmpty(universities)) {
			result = true;
		}
		return result;
	}

	/**
	 * Checks if is existing faculty.
	 *
	 * @param name the name
	 * @return true, if is existing faculty
	 */
	public boolean isExistingFaculty(String name) {
		boolean result = false;

		List<Faculty> faculties = facultyList.getFacultyByName(name);
		if (RTSUtils.isNotEmpty(faculties)) {
			result = true;
		}
		return result;
	}

	/**
	 * Gets the data holder2003.
	 *
	 * @return the data holder2003
	 */
	public Vector<Vector<HSSFCell>> getDataHolder2003() {
		return dataHolder2003;
	}

	/**
	 * Sets the data holder2003.
	 *
	 * @param dataHolder2003 the new data holder2003
	 */
	public void setDataHolder2003(Vector<Vector<HSSFCell>> dataHolder2003) {
		this.dataHolder2003 = dataHolder2003;
	}

	/**
	 * Gets the data holder2007.
	 *
	 * @return the data holder2007
	 */
	public Vector<Vector<XSSFCell>> getDataHolder2007() {
		return dataHolder2007;
	}

	/**
	 * Sets the data holder2007.
	 *
	 * @param dataHolder2007 the new data holder2007
	 */
	public void setDataHolder2007(Vector<Vector<XSSFCell>> dataHolder2007) {
		this.dataHolder2007 = dataHolder2007;
	}

	/**
	 * Clear data holder.
	 */
	private void clearDataHolder() {
		RTSUtils.resetList(dataHolder2003);
		RTSUtils.resetList(dataHolder2007);
		RTSUtils.resetList(candidates);
		RTSUtils.resetList(interviewSchedules);
	}

	/**
	 * Existed skill.
	 *
	 * @param skillName the skill name
	 * @return true, if successful
	 */
	public boolean existedSkill(String skillName) {
		boolean result = false;
		final List<Skill> skills = skillList.getSkillBySkillName(skillName.toLowerCase());
		if (skills != null && !skills.isEmpty()) {
			result = true;
		}
		return result;
	}

	/**
	 * Save new skill.
	 *
	 * @param skillName the skill name
	 */
	public void saveNewSkill(String skillName) {
		skillHome.clearInstance();
		skillHome.getInstance().setSklName(skillName);
		skillHome.persist();
		statusMessages.clear();
	}

	/**
	 * Save candidate skills.
	 *
	 * @param candidate the candidate
	 */
	private void saveCandidateSkills(final Candidate candidate) {
		if (candidate != null) {
			final String skillSet = candidate.getCddSkillSet() != null ? candidate.getCddSkillSet() : null;
			if (!StringUtils.isBlank(skillSet)) {
				String[] candidateSkills = skillSet.split(RTSConstants.SEMI_COLON);
				for (String skillName : candidateSkills) {
					Skill skill = null;
					try {
						skillName = skillName.trim();
						skillName = skillName.length() > 25 ? skillName.substring(0, 25) : skillName;

						if (!existedSkill(skillName)) {
							saveNewSkill(skillName);
						}
						final List<Skill> skills = skillList.getSkillBySkillName(skillName);
						if (RTSUtils.isNotEmpty(skills)) {
							skill = (Skill) skills.get(0);
							CandidateHasSkill candidateHasSkill = new CandidateHasSkill();
							candidateHasSkill.setCandidate(candidate);
							candidateHasSkill.setSkill(skill);
							candidateHasSkill.setChs_Exerinece_Years(Integer.valueOf(0));
							candidateHasSkill.setSkillLevel("");
							candidateHome.getEntityManager().persist(candidateHasSkill);
							statusMessages.clear();
						}
					} catch (Exception e) {
						log.info("Cannot add skill: " + skill.getSklName() + " for candidate "
								+ candidate.getCddName());
					}

				}
			}
		}
	}

	/**
	 * Removes the candidate skill.
	 *
	 * @param candidate the candidate
	 */
	private void removeCandidateSkill(Candidate candidate) {
		if (candidate != null) {
			Set<CandidateHasSkill> candidateHasSkills = candidate.getCandidateHasSkills();
			if (RTSUtils.isNotEmpty(candidateHasSkills)) {
				for (CandidateHasSkill cdHasSkill : candidateHasSkills) {
					candidateHome.getEntityManager().remove(cdHasSkill);
				}
			}
		}
	}

	/**
	 * Gets the time.
	 *
	 * @param type the type
	 * @param time the time
	 * @return the time
	 */
	private int getTime(int type, String time) {
		int result = 0;
		final int index = time.indexOf(":");

		if (time != null && !time.isEmpty()) {
			if (type == 1) {
				final String sHours = time.substring(0, index);
				result = Integer.parseInt(sHours);

				if (time.endsWith("pm") || time.endsWith("PM")) {
					result = result + 12;
				}
				if (result >= 24) {
					result = 0;
				}
			} else if (type == 2) {
				String sMinute = time.substring(index + 1, time.length());

				if (sMinute.endsWith("am")) {
					sMinute = sMinute.replace("am", "");
				} else if (sMinute.endsWith("AM")) {
					sMinute = sMinute.replace("AM", "");
				} else if (sMinute.endsWith("pm")) {
					sMinute = sMinute.replace("pm", "");
				} else if (sMinute.endsWith("PM")) {
					sMinute = sMinute.replace("PM", "");
				}
				result = Integer.parseInt(sMinute.trim());

				if (result >= 60) {
					result = 0;
				}
			}
		}
		return result;
	}

	/**
	 * Persist candidate.
	 *
	 * @param candidate the candidate
	 * @return the string
	 */
	private String persistCandidate(final Candidate candidate) {
		String persisted = RTSConstants.FAILURE;

		if (candidate != null) {
			final Date currentDate = new Date();
			candidate.setCddCreatedDate(currentDate);
			candidate.setCddCreatedBy(RTSConstants.USER_NAME_IMPORT);

			candidate.setCddUpdatedDate(currentDate);
			candidate.setCddUpdatedBy(RTSConstants.USER_NAME_IMPORT);

			candidateHome.clearInstance();
			candidateHome.setInstance(candidate);

			try {
				persisted = candidateHome.persist();				
			} catch (Exception e) {
				statusMessages.clear();
				statusMessages.addFromResourceBundle(Severity.ERROR, "ERROR_CANDIDATE_UPDATE");
				log.error("Error in persisting candidate: " + candidate.getCddName() + ". " + e);
			}

			if (persisted.equalsIgnoreCase(RTSConstants.PERSISTED)) {
				/* save skill set */
				final String skillSet = candidate.getCddSkillSet() != null ? candidate.getCddSkillSet() : null;
				if (!StringUtils.isBlank(skillSet)) {
					this.saveCandidateSkills(candidateHome.getInstance());
				}
			}
		}
		statusMessages.clear();
		return persisted;
	}

	/**
	 * Update candidate.
	 *
	 * @param candidate the candidate
	 * @param existingCandidate the existing candidate
	 * @return the string
	 */
	private String updateCandidate(final Candidate candidate, Candidate existingCandidate) {
		String updated = RTSConstants.FAILURE;

		if (candidate != null) {
			final Date currentDate = new Date();
			candidate.setCddUpdatedDate(currentDate);
			candidate.setCddUpdatedBy(RTSConstants.USER_NAME_IMPORT);
			candidateHome.copyingCandidateInfo(candidate, existingCandidate);
			candidateHome.clearInstance();
			try {
				candidateHome.setInstance(existingCandidate);

				// remove old skills
				this.removeCandidateSkill(existingCandidate);
				candidateHome.getInstance().getCandidateHasSkills().clear();
				updated = candidateHome.update();

			} catch (Exception e) {
				log.error("Error in updating candidate: " + candidate.getCddName() + ". " + e);
			}

			// save skill set
			if (updated.equalsIgnoreCase(RTSConstants.UPDATED)) {
				// save skill set
				final String skillSet = candidate.getCddSkillSet() != null ? candidate.getCddSkillSet() : null;
				if (!StringUtils.isBlank(skillSet) && !RTSUtils.isNotEmpty(existingCandidate.getCandidateHasSkills())) {
					this.saveCandidateSkills(existingCandidate);
				}
			}
		}
		
		statusMessages.clear();
		return updated;
	}

	/**
	 * Persist interview schedule.
	 *
	 * @param interviewSchedule the interview schedule
	 * @param candidate the candidate
	 * @return the string
	 */
	private String persistInterviewSchedule(final InterviewSchedule interviewSchedule, final Candidate candidate) {
		String updated = RTSConstants.FAILURE;

		interviewScheduleHome.clearInstance();
		interviewSchedule.setCandidate(candidate);

		interviewSchedule.setItsCreatedOn(new Date());
		interviewSchedule.setItsCreatedBy(RTSConstants.USER_NAME_IMPORT);

		interviewScheduleHome.setInstance(interviewSchedule);
		interviewScheduleHome.persist();

		/* Add interviewers for interview schedule */
		final Set<UserHasInterviewSchedule> hasInterviewSchedules = interviewScheduleHome.getInstance()
				.getUserHasInterviewSchedules();
		if (RTSUtils.isNotEmpty(hasInterviewSchedules)) {
			for (UserHasInterviewSchedule uhis : hasInterviewSchedules) {
				uhis.setInterviewSchedule(interviewSchedule);
				interviewScheduleHome.getEntityManager().persist(uhis);
			}
		}
		statusMessages.clear();
		return updated;
	}

}
