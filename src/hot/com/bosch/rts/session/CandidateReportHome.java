/*
 * com.bosch.rts.session.CandidateReportHome.java
 */
package com.bosch.rts.session;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.DateTime;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.beanutils.BeanComparator;
import org.apache.commons.lang3.StringUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateHasAttachment;
import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.HrResult;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.entity.TechnicalResult;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.CandidateReportHeaders;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;

/**
 * Candidate Report Home for Candidate Report module.
 *
 * @author khb1hc
 */
@Name("candidateReportHome")
@Scope(value = ScopeType.CONVERSATION)
public class CandidateReportHome {

	/** serialVersionUID. */
	private static final long serialVersionUID = 7787648111195627228L;
	
	/** The log. */
	@Logger
	private transient Log log;

	/** The created from. */
	private Date createdFrom;
	
	/** The created to. */
	private Date createdTo;
	
	/** The source. */
	private String source;
	
	/** The org unit. */
	private OrgUnit orgUnit;
	
	/** The recruit request. */
	private RecruitRequest recruitRequest;
	
	/** The available recruit requests. */
	private List<RecruitRequest> availableRecruitRequests = new ArrayList<RecruitRequest>();
	
	/** The candidate status. */
	private CandidateStatus candidateStatus;
	
	/** The created by. */
	private User createdBy;
	
	/** The priority. */
	private String priority;
	
	/** The handled by. */
	private User handledBy;
	
	/** The short list by. */
	private User shortListBy;
	
	/** The skills. */
	private List<Skill> skills = new ArrayList<Skill>();
	
	/** The candidates. */
	private List<Candidate> candidates = new ArrayList<Candidate>();
	
	/**
	 * Reset form fields.
	 */
	public void resetFormFields(){
		this.setCreatedFrom(null);
		this.setCreatedTo(null);
		availableRecruitRequests.clear();
		this.setSource(null);
		this.setOrgUnit(null);
		this.setCandidateStatus(null);
		this.setCreatedBy(null);
		this.setHandledBy(null);
		this.setShortListBy(null);
		skills.clear();
		candidates.clear();
	}
	
	/** The recruit request list. */
	@In(create = true)
	private transient RecruitRequestList recruitRequestList;
	
	/** The candidate list. */
	@In(create = true)
	private transient CandidateList candidateList;
	
	@In
	private transient StatusMessages statusMessages;	
	
	/**
	 * Populate recruit requests.
	 */
	public void populateRecruitRequests(){
		availableRecruitRequests.clear();
		if (this.orgUnit != null){
			final String LEVEL_PATH = this.orgUnit.getLevelPath();
			availableRecruitRequests = recruitRequestList.getRecruitRequestByOrgUnitLevelPath(LEVEL_PATH);			
		}	
	}
	
	/**
	 * Gets the available recruit requests.
	 *
	 * @return the availableRecruitRequests
	 */
	public List<RecruitRequest> getAvailableRecruitRequests() {
		return this.availableRecruitRequests;
	}

	/**
	 * Sets the available recruit requests.
	 *
	 * @param availableRecruitRequests the availableRecruitRequests to set
	 */
	public void setAvailableRecruitRequests(
			List<RecruitRequest> availableRecruitRequests) {
		this.availableRecruitRequests = availableRecruitRequests;
	}
	
	/**
	 * Gets the created from.
	 *
	 * @return the createdFrom
	 */
	public Date getCreatedFrom() {
		return this.createdFrom;
	}
	
	/**
	 * Sets the created from.
	 *
	 * @param createdFrom the createdFrom to set
	 */
	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}
	
	/**
	 * Gets the created to.
	 *
	 * @return the createdTo
	 */
	public Date getCreatedTo() {
		return this.createdTo;
	}
	
	/**
	 * Sets the created to.
	 *
	 * @param createdTo the createdTo to set
	 */
	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
	}
	
	/**
	 * Gets the source.
	 *
	 * @return the source
	 */
	public String getSource() {
		return this.source;
	}
	
	/**
	 * Sets the source.
	 *
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Gets the org unit.
	 *
	 * @return the orgUnit
	 */
	public OrgUnit getOrgUnit() {
		return this.orgUnit;
	}
	
	/**
	 * Sets the org unit.
	 *
	 * @param orgUnit the orgUnit to set
	 */
	public void setOrgUnit(OrgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}
	
	/**
	 * Gets the recruit request.
	 *
	 * @return the recruitRequest
	 */
	public RecruitRequest getRecruitRequest() {
		return this.recruitRequest;
	}
	
	/**
	 * Sets the recruit request.
	 *
	 * @param recruitRequest the recruitRequest to set
	 */
	public void setRecruitRequest(RecruitRequest recruitRequest) {
		this.recruitRequest = recruitRequest;
	}
	
	/**
	 * Gets the candidate status.
	 *
	 * @return the candidateStatus
	 */
	public CandidateStatus getCandidateStatus() {
		return this.candidateStatus;
	}
	
	/**
	 * Sets the candidate status.
	 *
	 * @param candidateStatus the candidateStatus to set
	 */
	public void setCandidateStatus(CandidateStatus candidateStatus) {
		this.candidateStatus = candidateStatus;
	}
	
	/**
	 * Gets the created by.
	 *
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return this.createdBy;
	}
	
	/**
	 * Sets the created by.
	 *
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	/**
	 * Gets the priority.
	 *
	 * @return the priority
	 */
	public String getPriority() {
		return this.priority;
	}
	
	/**
	 * Sets the priority.
	 *
	 * @param priority the new priority
	 */
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	/**
	 * Gets the handled by.
	 *
	 * @return the handledBy
	 */
	public User getHandledBy() {
		return this.handledBy;
	}
	
	/**
	 * Sets the handled by.
	 *
	 * @param handledBy the handledBy to set
	 */
	public void setHandledBy(User handledBy) {
		this.handledBy = handledBy;
	}
	
	/**
	 * Gets the short list by.
	 *
	 * @return the shortListBy
	 */
	public User getShortListBy() {
		return this.shortListBy;
	}
	
	/**
	 * Sets the short list by.
	 *
	 * @param shortListBy the shortListBy to set
	 */
	public void setShortListBy(User shortListBy) {
		this.shortListBy = shortListBy;
	}
	
	/**
	 * Gets the skills.
	 *
	 * @return the skills
	 */
	public List<Skill> getSkills() {
		return this.skills;
	}
	
	/**
	 * Sets the skills.
	 *
	 * @param skills the skills to set
	 */
	public void setSkills(List<Skill> skills) {
		this.skills = skills;
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
	 * Gets the candidates.
	 *
	 * @return the candidates
	 */
	public List<Candidate> getCandidates() {
		return candidates;
	}
	
	/**
	 * Search by this week.
	 *
	 * @return the string
	 */
	public String searchByThisWeek(){
		String result = RTSConstants.FAILURE;
		Calendar createdFromCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdFromCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		createdFromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdFromCalendar.set(Calendar.MINUTE, 0);
		createdFromCalendar.set(Calendar.SECOND, 0);
		this.setCreatedFrom(createdFromCalendar.getTime());
		this.setCreatedTo(null);
		
		search();
		
		return result;
	}
	
	/**
	 * Search by last week.
	 *
	 * @return the string
	 */
	public String searchByLastWeek(){
		String result = RTSConstants.FAILURE;
		Calendar createdFromCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdFromCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		createdFromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdFromCalendar.set(Calendar.MINUTE, 0);
		createdFromCalendar.set(Calendar.SECOND, 0);
		createdFromCalendar.add(Calendar.WEEK_OF_MONTH, -1);
		
		Calendar createdToCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdToCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		createdToCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdToCalendar.set(Calendar.MINUTE, 0);
		createdToCalendar.set(Calendar.SECOND, 0);		
		
		this.setCreatedFrom(createdFromCalendar.getTime());		
		this.setCreatedTo(createdToCalendar.getTime());
		
		search();
		
		return result;
	}
	
	/**
	 * Search by this month.
	 *
	 * @return the string
	 */
	public String searchByThisMonth(){
		String result = RTSConstants.FAILURE;
		Calendar createdFromCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdFromCalendar.set(Calendar.DAY_OF_MONTH, 1);
		createdFromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdFromCalendar.set(Calendar.MINUTE, 0);
		createdFromCalendar.set(Calendar.SECOND, 0);
		
		this.setCreatedFrom(createdFromCalendar.getTime());
		this.setCreatedTo(null);
		
		search();
		
		return result;
	}
	
	/**
	 * Search by last month.
	 *
	 * @return the string
	 */
	public String searchByLastMonth(){
		String result = RTSConstants.FAILURE;
		Calendar createdFromCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdFromCalendar.add(Calendar.MONTH, -1);	
		createdFromCalendar.add(Calendar.WEEK_OF_MONTH, 1);	
		createdFromCalendar.set(Calendar.DAY_OF_MONTH, 1);
		createdFromCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdFromCalendar.set(Calendar.MINUTE, 0);
		createdFromCalendar.set(Calendar.SECOND, 0);		
	
		this.setCreatedFrom(createdFromCalendar.getTime());
		
		Calendar createdToCalendar = Calendar.getInstance(Locale.ENGLISH);
		createdToCalendar.set(Calendar.DAY_OF_MONTH, 1);
		createdToCalendar.set(Calendar.HOUR_OF_DAY, 0);
		createdToCalendar.set(Calendar.MINUTE, 0);
		createdToCalendar.set(Calendar.SECOND, 0);		
		
		this.setCreatedTo(createdToCalendar.getTime());
		
		search();
		
		return result;
	}
	
	/**
	 * Search candidates.
	 *
	 * @return the string
	 */
	public String search(){
		String result = RTSConstants.FAILURE;
		
		statusMessages.clear();
		candidates.clear();
		
		try{
			candidates = candidateList.searchCandidates(
					createdFrom, 
					createdTo, 
					createdBy != null ? createdBy.getUsrUserName() : null,
					orgUnit != null ? orgUnit : null, 
					recruitRequest != null ? recruitRequest : null, 
					source, 
					candidateStatus, 
					handledBy != null ? handledBy.getUsrUserName() : null , 
					shortListBy != null ? shortListBy.getUsrUserName() : null, 
					RTSUtils.isNotEmpty(skills) ? skills : null,
					priority);		
		} catch (Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.error.search.report");	
			log.error("Error in searching candidates for report." + e);
		}
		
		return result;
	}
	
	/**
	 * Export candidates to Excel based on the returned candidates from searching.
	 *
	 * @return the string
	 */
	public String export(){
		String result = RTSConstants.FAILURE;
		statusMessages.clear();
		try {
			exportCandidates();
		} catch (WriteException e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.error.report");	
			log.error("Error in exporting candidates for report." + e);
		} catch (IOException e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.error.report");	
			log.error("Error in exporting candidates for report." + e);
		}
		return result;
	}
	
	
	/**
	 * Export candidates.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws WriteException the write exception
	 */
	private void exportCandidates() throws IOException, WriteException {
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.responseComplete();

		final HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.setContentType("application/vnd.ms-excel");
		response.addHeader("Content-disposition", "attachment; filename=CandidateReport.xls");
		try {
			final ServletOutputStream outputStream = response.getOutputStream();			
		    WorkbookSettings wbSettings = new WorkbookSettings();
		    wbSettings.setLocale(new Locale("en", "EN"));

		    WritableWorkbook workbook = Workbook.createWorkbook(outputStream, wbSettings);
		    workbook.createSheet("short-list", 0);
		    WritableSheet excelSheet = workbook.getSheet(0);
		    createLabel(excelSheet);
		    createContent(excelSheet);

		    workbook.write();
		    workbook.close();
			
		    outputStream.flush();
		    outputStream.close();
			facesContext.responseComplete();
		} catch(Exception e) {
			statusMessages.addFromResourceBundle(Severity.ERROR, "candidate.error.report");	
			log.error("Error in exporting candidates for report." + e);
		}
	  
	  }

	/** The headers. */
	private WritableCellFormat headers;
	
	/** The labels. */
	private WritableCellFormat labels; 
	
	/**
	 * Creates the label.
	 *
	 * @param sheet the sheet
	 * @throws WriteException the write exception
	 */
	private void createLabel(WritableSheet sheet) throws WriteException {
	  // Lets create a labels font
	  WritableFont labels10pt = new WritableFont(WritableFont.ARIAL, 11);
	  // Define the cell format
	  labels = new WritableCellFormat(labels10pt);
	  // Lets automatically wrap the cells
	  labels.setWrap(true);
	 // labels.setShrinkToFit(true);
	  
	  WritableFont labels10ptBoldUnderline = new WritableFont(WritableFont.ARIAL, 11, WritableFont.BOLD, false);
	 // RGB rgb = new RGB(146, 208, 80);
	  
	  //labels10ptBoldUnderline.setColour(Colour.GREEN);
	  
	  //#92D050
	  headers = new WritableCellFormat(labels10ptBoldUnderline);
	  // Lets automatically wrap the cells
	  headers.setShrinkToFit(true);
	  headers.setWrap(true);
	  //headers.setIndentation(i)
	  headers.setBackground(Colour.GREEN);
	  headers.setBorder(Border.ALL, BorderLineStyle.THIN);
	  headers.setAlignment(Alignment.CENTRE);
	  headers.setVerticalAlignment(VerticalAlignment.CENTRE);
	  
	  //headers.setLocked(true);
	  //headers.setShrinkToFit(true);	  
	  
	  CellView cv = new CellView();
	  cv.setFormat(labels);
	  cv.setFormat(headers);
	  cv.setAutosize(true);
	  //cv.setSize(d)
	
	  // Write a few headers
	  addCaption(sheet, 0, 0, CandidateReportHeaders.NO); sheet.getSettings().setDefaultColumnWidth(12);
	  addCaption(sheet, 1, 0, CandidateReportHeaders.FULL_NAME);
	  addCaption(sheet, 2, 0, CandidateReportHeaders.UPDATE_DATE);
	  addCaption(sheet, 3, 0, CandidateReportHeaders.POSITION_APPLIED);
	  addCaption(sheet, 4, 0, CandidateReportHeaders.COMPETENCY);
	  addCaption(sheet, 5, 0, CandidateReportHeaders.TEAM);
	  addCaption(sheet, 6, 0, CandidateReportHeaders.YEARS_OF_EXPERIENCES);
	  addCaption(sheet, 7, 0, CandidateReportHeaders.RELEVANT_EXPERIENCES);
	  addCaption(sheet, 8, 0, CandidateReportHeaders.YOB);
	  addCaption(sheet, 9, 0, CandidateReportHeaders.UNIVERSITY);
	  addCaption(sheet, 10, 0, CandidateReportHeaders.CURRENT_COMAPANY);
	  addCaption(sheet, 11, 0, CandidateReportHeaders.CURRENT_POSITION); 
	  addCaption(sheet, 12, 0, CandidateReportHeaders.MOBILE);
	  addCaption(sheet, 13, 0, CandidateReportHeaders.EMAIL);
	  addCaption(sheet, 14, 0, CandidateReportHeaders.FILE);
	  addCaption(sheet, 15, 0, CandidateReportHeaders.LINK);
	  addCaption(sheet, 16, 0, CandidateReportHeaders.LINK_CV);
	  addCaption(sheet, 17, 0, CandidateReportHeaders.LOCATION);
	  addCaption(sheet, 18, 0, CandidateReportHeaders.SOURCE);
	  addCaption(sheet, 19, 0, CandidateReportHeaders.INTRODUCED_BY);
	  addCaption(sheet, 20, 0, CandidateReportHeaders.TIME_OF_CV_RECEIVED);
	  addCaption(sheet, 21, 0, CandidateReportHeaders.HANDLED_BY);
	  addCaption(sheet, 22, 0, CandidateReportHeaders.TIME_OF_CV_SENT);
	  addCaption(sheet, 23, 0, CandidateReportHeaders.SHORT_LIST_BY);
	  addCaption(sheet, 24, 0, CandidateReportHeaders.SHORT_LIST_RESULT);
	  addCaption(sheet, 25, 0, CandidateReportHeaders.TEST_DATE);
	  addCaption(sheet, 26, 0, CandidateReportHeaders.TEST_RESULT);
	  addCaption(sheet, 27, 0, CandidateReportHeaders.FIRST_INTERVIEW);
	  addCaption(sheet, 28, 0, CandidateReportHeaders.INTERVIEWER);
	  addCaption(sheet, 29, 0, CandidateReportHeaders.FIRST_ROUND_INTERVIEW_STATUS);
	  addCaption(sheet, 30, 0, CandidateReportHeaders.SECOND_INTERVIEW);
	  addCaption(sheet, 31, 0, CandidateReportHeaders.INTERVIEWER);
	  addCaption(sheet, 32, 0, CandidateReportHeaders.SECOND_ROUND_INTERVIEW_STATUS);
	  addCaption(sheet, 33, 0, CandidateReportHeaders.FINAL_STATUS);
	  addCaption(sheet, 34, 0, CandidateReportHeaders.OFFER_STATUS);
	  addCaption(sheet, 35, 0, CandidateReportHeaders.SUMMARY_ASSESSMENT);
	  addCaption(sheet, 36, 0, CandidateReportHeaders.NOTE);
	
	}		
	
	/**
	 * Creates the content.
	 *
	 * @param sheet the sheet
	 * @throws WriteException the write exception
	 * @throws RowsExceededException the rows exceeded exception
	 * @throws MalformedURLException the malformed url exception
	 */
	private void createContent(WritableSheet sheet) throws WriteException, RowsExceededException, MalformedURLException {
			final ResourceBundle resourceBundle = SeamResourceBundle.getBundle("messages", Locale.ENGLISH);					
			final String CDD_DOC_LOCATION = resourceBundle.getString("com.bosch.storage.recruit_request_doc");
			
			final String pattern = resourceBundle.getString("calendar.pattern");
			final String suitable = resourceBundle.getString("candidate.shortlist.result.0");
			final String notSuitable = resourceBundle.getString("candidate.shortlist.result.1");
			
			final String pass = resourceBundle.getString("candidate.tested.result.0");
			final String failure = resourceBundle.getString("candidate.tested.result.1");			
			
			final int candidate_size = candidates.size();
			
			for(int i = 0; i < candidate_size; i++){
				addNumber(sheet, 0, i+1, i+1);
				
				final Candidate candidate = (Candidate)candidates.get(i);
				
				addLabel(sheet, 1, i+1, candidate.getCddName());
				
				if(candidate.getCddUpdatedDate() != null){
					addDate(sheet, 2, i+1, candidate.getCddUpdatedDate(), pattern);
				}				
				
				addLabel(sheet, 3, i+1, candidate.getPositionApplied());
				
				final Set<CandidateHasSkill> skills = candidate.getCandidateHasSkills();
				if(RTSUtils.isNotEmpty(skills)){
					StringBuilder stringBuilder = new StringBuilder();
					for(CandidateHasSkill candidateHasSkill : skills ){
						if(candidateHasSkill != null){
							final Skill skill = candidateHasSkill.getSkill();
							if(skill != null){
								stringBuilder.append(skill.getSklName());
								stringBuilder.append('\n');								
							}
						}						
					}	
					addLabel(sheet, 4, i+1, stringBuilder.toString()); //"Competency" item.skill.sklName
				}
				
				
				addLabel(sheet, 5, i+1, candidate.getOrgUnit().getUnitName());
				
				final String yearOfExp = String.valueOf(candidate.getCddYearOfExperience());
				if(StringUtils.isNotEmpty(yearOfExp)){
					addLabel(sheet, 6, i+1, yearOfExp);
				}
				
				final String relExp = String.valueOf(candidate.getCddRelevantExperience());
				if(StringUtils.isNotEmpty(relExp)){
					if(relExp.equals("null")){
						addLabel(sheet, 7, i+1, "0.0");
					} else {
						addLabel(sheet, 7, i+1, relExp);
					}					
				} 
				
				addLabel(sheet, 8, i+1, candidate.getCddDob());
				addLabel(sheet, 9, i+1, candidate.getCddGraduationUniversity());
				addLabel(sheet, 10, i+1, candidate.getCurrentCompany());
				addLabel(sheet, 11, i+1, candidate.getCurrentPosition()); 
				addLabel(sheet, 12, i+1, candidate.getCddPhoneNo());
				addLabel(sheet, 13, i+1, candidate.getCddEmail());
				
				final Set<CandidateHasAttachment> candidateHasAttachments = candidate.getAttachments();
				if(RTSUtils.isNotEmpty(candidateHasAttachments)){
					StringBuilder extentionBuffer = new StringBuilder();					
					StringBuilder attachmentNameBuffer = new StringBuilder();
					StringBuilder attachmenFileBuffer = new StringBuilder();
					
					for(CandidateHasAttachment candidateHasAttachment : candidateHasAttachments){
						final String attachmentName = candidateHasAttachment.getChaAttachmentName();
						if(StringUtils.isNotEmpty(attachmentName)){
							if(attachmentName.lastIndexOf(".") != -1){
								final String extention = attachmentName.substring(attachmentName.lastIndexOf("."), attachmentName.length());
								extentionBuffer.append(extention);
								extentionBuffer.append('\n');								
								
								attachmenFileBuffer.append(CDD_DOC_LOCATION);	
								attachmenFileBuffer.append('\\');	
								attachmenFileBuffer.append(attachmentName);
								attachmenFileBuffer.append('\n');
								
								attachmentNameBuffer.append(attachmentName);
								attachmentNameBuffer.append('\n');					
								
							}							
						}
					}
					addLabel(sheet, 14, i+1, extentionBuffer.toString()); //File					
					//addFile(sheet, 15, i+1, attachmenFileBuffer.toString(), attachmentNameBuffer.toString()); //LINK	
					addLabel(sheet, 15, i+1, attachmentNameBuffer.toString()); //LINK	
				}				
				addLabel(sheet, 16, i+1, "");
				addLabel(sheet, 17, i+1, candidate.getCddAddress());
				addLabel(sheet, 18, i+1, candidate.getCddSource());
				addLabel(sheet, 19, i+1, candidate.getCddEmployeeName());
				
				if(candidate.getTimeOfCvReceived() != null){
					addDate(sheet, 20, i+1, candidate.getTimeOfCvReceived(), pattern);
				}
				
				addLabel(sheet, 21, i+1, candidate.getHandledBy());
				
				if(candidate.getTimeOfCvSent() != null){
					addDate(sheet, 22, i+1, candidate.getTimeOfCvSent(), pattern);
				}
				
				addLabel(sheet, 23, i+1, candidate.getShortListBy());
				
				final String shortListResult = candidate.getShortListResult();
				if(StringUtils.isNotEmpty(shortListResult)){
					if(shortListResult.equals(String.valueOf(0))){
						addLabel(sheet, 24, i+1, suitable);
					} else if(shortListResult.equals(String.valueOf(1))){
						addLabel(sheet, 24, i+1,  notSuitable);
					}	
				}							
				
				if(candidate.getTestedOn() != null){
					addDate(sheet, 25, i+1, candidate.getTestedOn(), pattern);
				}
				
				final String testedResult = candidate.getTestedResult();
				if(StringUtils.isNotEmpty(testedResult)){
					if(testedResult.equals(String.valueOf(0))){
						addLabel(sheet, 26, i+1, pass);
					} else if(testedResult.equals(String.valueOf(1))){
						addLabel(sheet, 26, i+1, failure);
					}
				}				
				
				String technicalResult = null;
				final Set<InterviewSchedule> interviewSchedules = candidate.getInterviewSchedules();
				
				if(RTSUtils.isNotEmpty(interviewSchedules)){
					final List<InterviewSchedule> schedules = new ArrayList<InterviewSchedule>();
					schedules.addAll(interviewSchedules);
					
					if(RTSUtils.isNotEmpty(schedules)){
						
						//Sort to get the latest interview schedule
						BeanComparator interviewScheduleIdComparator = new BeanComparator();
						interviewScheduleIdComparator.setProperty("itsInterviewScheduleId");
						Collections.sort(schedules, interviewScheduleIdComparator);
						final InterviewSchedule interviewSchedule = schedules.get(schedules.size()-1);
						if(interviewSchedule != null){
							if(RTSUtils.isNotEmpty(interviewSchedule.getTechnicalResultList())){								
								final TechnicalResult resultEntity = interviewSchedule.getTechnicalResultList().get(0);
								//Interview time
								final Date interviewDate = resultEntity.getUpdatedOn();
								if(interviewDate!= null){									
									addDate(sheet, 27, i+1, interviewDate, pattern); //"1st Interview"	
								} else {
									final Date interviewCreatedOn = resultEntity.getCreatedOn();
									if(interviewCreatedOn != null){
										addDate(sheet, 27, i+1, interviewCreatedOn, pattern);
									}
								}
															
								
								//Interviewers
								
								final List<User> interviewers = interviewSchedule.getInterviewers();
								if(RTSUtils.isNotEmpty(interviewers)){
									StringBuilder intervier = new StringBuilder();
									for(User user : interviewers){		
										intervier.append(user.getUsrFullName());
										intervier.append('\n');										
									}
									addLabel(sheet, 28, i+1, intervier.toString());
								}
								
								//Technical result
								
								final short result = resultEntity.getOverallEvaluation();
								switch (result) {
								case 1: 
									technicalResult = CandidateStatus.TECHNICAL_PASS.toString();
									break;
								case 2: 
									technicalResult = CandidateStatus.TECHNICAL_PASS.toString();							
									break;
								case 3: 
									technicalResult = CandidateStatus.TECHNICAL_PASS.toString();						
								
								default:
									technicalResult = CandidateStatus.TECHNICAL_FAIL.toString();
									break;
								}	
								
								addLabel(sheet, 29, i+1, technicalResult); //"1st Interview"								
								
								
							}
							
							
							//HR
							if(RTSUtils.isNotEmpty(interviewSchedule.getHrResultList())){								
								final HrResult hrResultEntity = interviewSchedule.getHrResultList().get(0);
								//Interview time
								final Date hrUpdatedOn = hrResultEntity.getUpdatedOn();
								if(hrUpdatedOn != null){									
									addDate(sheet, 30, i+1, hrUpdatedOn, pattern); //"2nd Interview"										
									addLabel(sheet, 31, i+1, hrResultEntity.getUpdatedBy());//"2nd Interviewers"	
								} else {
									final Date hrCreatedOn = hrResultEntity.getCreatedOn();
									if(hrCreatedOn != null){
										addDate(sheet, 30, i+1, hrCreatedOn, pattern);
										addLabel(sheet, 31, i+1, hrResultEntity.getCreatedBy());//"2nd Interviewers"
									}
								}						
								
								final String hrRecommended = hrResultEntity.getIhrRecommended();
								if(StringUtils.isNotEmpty(hrRecommended)){
									String hrStatus = null;
									if(hrRecommended.equals(String.valueOf(0))){
										hrStatus = pass;
									} else if(hrRecommended.equals(String.valueOf(1))){
										hrStatus = failure;
									} else if(hrRecommended.equals(String.valueOf(2))){
										hrStatus = pass;
									}
									addLabel(sheet, 32, i+1, hrStatus); //2nd round interview Status		
								}
							
								addLabel(sheet, 35, i+1, hrResultEntity.getIhrComment()); //"Summary Assessment" -> HR comment
							}							
						}						
					}				
				}				
				
				final CandidateStatus cadidateStatus = candidate.getCddStatus();
				addLabel(sheet, 33, i+1, cadidateStatus.toString());				
			
				//TO_OFFER, OFFERED, OFFER_ACCEPTED, OFFER_REFUSED, 
				if(cadidateStatus.toString().equals(CandidateStatus.TO_OFFER.toString())){
					addLabel(sheet, 34, i+1, cadidateStatus.toString());
				} else if (cadidateStatus.toString().equals(CandidateStatus.OFFERED.toString())){
					addLabel(sheet, 34, i+1, cadidateStatus.toString());
				} else if (cadidateStatus.toString().equals(CandidateStatus.OFFER_ACCEPTED.toString())){
					addLabel(sheet, 34, i+1, cadidateStatus.toString());
				} else if (cadidateStatus.toString().equals(CandidateStatus.OFFER_REFUSED.toString())){
					addLabel(sheet, 34, i+1, cadidateStatus.toString());
				}
				
				addLabel(sheet, 36, i+1, candidate.getCddSkillDescription()); //Note
			}		
		
	}
	
	/**
	 * Adds the caption.
	 *
	 * @param sheet the sheet
	 * @param column the column
	 * @param row the row
	 * @param s the s
	 * @throws RowsExceededException the rows exceeded exception
	 * @throws WriteException the write exception
	 */
	private void addCaption(WritableSheet sheet, int column, int row, String s)
			throws RowsExceededException, WriteException {
		Label label;
		label = new Label(column, row, s, headers);
		sheet.addCell(label);
	}

	/**
	 * Adds the number.
	 *
	 * @param sheet the sheet
	 * @param column the column
	 * @param row the row
	 * @param integer the integer
	 * @throws WriteException the write exception
	 * @throws RowsExceededException the rows exceeded exception
	 */
	private void addNumber(WritableSheet sheet, int column, int row,
			Integer integer) throws WriteException, RowsExceededException {
		jxl.write.Number number;
		number = new jxl.write.Number(column, row, integer, labels);
		sheet.addCell(number);
	}
	
	/**
	 * Adds the file.
	 *
	 * @param sheet the sheet
	 * @param column the column
	 * @param row the row
	 * @param hyperlink the hyper-link
	 * @param desc the description
	 * @throws WriteException the write exception
	 * @throws RowsExceededException the rows exceeded exception
	 */
	private void addFile(WritableSheet sheet, int column, int row,
			String hyperlink, String desc) throws WriteException, RowsExceededException {
		//WritableHyperlink hl = new WritableHyperlink(0, 0, new URL(hyperlink), "");
		
		WritableHyperlink hl = new WritableHyperlink(column, row, new File(hyperlink), desc);
	    sheet.addHyperlink(hl);
	}
	
	/**
	 * Adds the date.
	 *
	 * @param sheet the sheet
	 * @param column the column
	 * @param row the row
	 * @param date the date
	 * @param pattern the pattern
	 * @throws WriteException the write exception
	 * @throws RowsExceededException the rows exceeded exception
	 */
	private void addDate(WritableSheet sheet, int column, int row, Date date,
			String pattern) throws WriteException, RowsExceededException {

		DateFormat customDateFormat = new DateFormat(pattern);
		WritableCellFormat dateFormat = new WritableCellFormat(customDateFormat);
		DateTime dateCell = new DateTime(column, row, date, dateFormat);
		sheet.addCell(dateCell);
	}

	/**
	 * Adds the label.
	 *
	 * @param sheet the sheet
	 * @param column the column
	 * @param row the row
	 * @param s the s
	 * @throws WriteException the write exception
	 * @throws RowsExceededException the rows exceeded exception
	 */
	private void addLabel(WritableSheet sheet, int column, int row, String s)
			throws WriteException, RowsExceededException {
		Label label;
		label = new Label(column, row, s, labels);
		sheet.addCell(label);
	}

}	