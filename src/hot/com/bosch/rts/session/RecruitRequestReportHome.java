/*
 * /rts/src/hot/com/bosch/rts/session/RecruitRequestReportHome.java
 */
package com.bosch.rts.session;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.log.Log;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.Cell;
import com.bosch.rts.entity.Column;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.RecruitRequestHasSkill;
import com.bosch.rts.entity.Row;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.utilities.RecruitRequestReportHeaders;

/**
 * Recruit Request Report Home for Recruit Request module.
 *
 * @author khb1hc
 */
@Name("rrrHome")
@Scope(ScopeType.CONVERSATION)
public class RecruitRequestReportHome {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7776384819770855260L;
	
	/** The log. */
	@Logger
	private transient Log log;	

	@In(create = true)
	private transient RecruitRequestReportList recruitRequestReportList;
	
	/** The recruit request list. */
	@In(create = true)
	private transient RecruitRequestList recruitRequestList;
	
	@In(create = true)
	private transient OrgUnitList orgUnitList;
	
	public void onloadAvailableOrgUnits(){
		availableOrgUnits.clear();		
		availableOrgUnits = orgUnitList.getOrgUnits(Boolean.TRUE);
	}
	
	/**
	 * 
	 */
	public String export(){	
		String result = RTSConstants.FAILURE;
		log.info("Method export() called.");
		if(RTSUtils.isNotEmpty(rrReportList)){
			try {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.responseComplete();
				HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-disposition", "attachment; filename=\""	+ "recruitment-report.xls" + "\"");
				ServletOutputStream servletOutputStream = response.getOutputStream();
				
				HSSFWorkbook workbook = new HSSFWorkbook();
				HSSFSheet sheet = workbook.createSheet(RecruitRequestReportHeaders.RECRUITMENT_REPORT);						
			
				sheet.setColumnWidth(7, (short)3200);				
				
				sheet.setColumnWidth(11, (short)3200);
				sheet.setColumnWidth(12, (short)3200);
				sheet.setColumnWidth(13, (short)3200);
				sheet.setColumnWidth(14, (short)3200);
				
				sheet.setColumnWidth(16, (short)6400);
				
				sheet.setColumnWidth(17, (short)3200);
				sheet.setColumnWidth(18, (short)3200);
				sheet.setColumnWidth(19, (short)3200);
				
				sheet.setColumnWidth(40, (short)10000);
				sheet.setColumnWidth(41, (short)10000);
				
				//Master
				org.apache.poi.ss.usermodel.Row masterRow = sheet.createRow(0);
				masterRow.setHeight((short) 800);
				
				for(int i = 0; i< 10; i++){
					org.apache.poi.ss.usermodel.Cell infoMasterCell = masterRow.createCell(i);
					infoMasterCell.setCellValue(RecruitRequestReportHeaders.INFORMATION);
					infoMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.GREY_25_PERCENT.getIndex(), false));					
				}		
				
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,0,9));
				
				for(int i = 10; i< 16; i++){
					org.apache.poi.ss.usermodel.Cell reqMasterCell = masterRow.createCell(i);
					reqMasterCell.setCellValue(RecruitRequestReportHeaders.REQUEST_DURATION_RECORD);
					reqMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.GREY_50_PERCENT.getIndex(), false));
				}					
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,10,15));
				
				org.apache.poi.ss.usermodel.Cell perMasterCell = masterRow.createCell(16);
				perMasterCell.setCellValue(RecruitRequestReportHeaders.PERSON_IN_CHARGE);		
				CellStyle perCellStyle = getMasterCellStyle(workbook, IndexedColors.PINK.getIndex(),false);				
				perMasterCell.setCellStyle(perCellStyle);
				
				
				for(int i = 17; i < 20; i++){
					org.apache.poi.ss.usermodel.Cell tarMasterCell = masterRow.createCell(i);
					tarMasterCell.setCellValue(RecruitRequestReportHeaders.TARGET_AND_DELIVER);					
					CellStyle tarCellStyle = getMasterCellStyle(workbook, IndexedColors.AQUA.getIndex(),false);					
					tarMasterCell.setCellStyle(tarCellStyle);
				}
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,17,19));
				
				for(int i = 20; i < 34; i++){
					org.apache.poi.ss.usermodel.Cell appMasterCell = masterRow.createCell(i);
					appMasterCell.setCellValue(RecruitRequestReportHeaders.APPLICATION_STATUS);
					appMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.BRIGHT_GREEN.getIndex(),false));
				}
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,20,33));				
				
				for(int i = 34; i < 40; i++){
					org.apache.poi.ss.usermodel.Cell offMasterCell = masterRow.createCell(i);
					offMasterCell.setCellValue(RecruitRequestReportHeaders.OFFER_STATUS);				
					offMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.TEAL.getIndex(),false));
				}			
				
				sheet.addMergedRegion(new CellRangeAddress(0,0,34,39));
				
				org.apache.poi.ss.usermodel.Cell noteMasterCell = masterRow.createCell(40);
				noteMasterCell.setCellValue(RecruitRequestReportHeaders.NOTE);
				noteMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.YELLOW.getIndex(),false));
				
				org.apache.poi.ss.usermodel.Cell weeMasterCell = masterRow.createCell(41);
				weeMasterCell.setCellStyle(getMasterCellStyle(workbook, IndexedColors.YELLOW.getIndex(),false));
				weeMasterCell.setCellValue(RecruitRequestReportHeaders.WEEKLY_STATUS_UPDATE);
							
				//Headers
				org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(1);
				int headerNum = 0;
				for(Column column : columns){
					 org.apache.poi.ss.usermodel.Cell headerCell = headerRow.createCell(headerNum++);
					 headerCell.setCellValue(column.getHeader());
					 
					 CellStyle cellStyle = null;					 
					 if(headerNum <= 10){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.GREY_25_PERCENT.getIndex(),true);
					 } else if( headerNum <= 16){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.GREY_50_PERCENT.getIndex(),true);
					 } else if( headerNum == 17){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.PINK.getIndex(),true);
					 } else if( headerNum <= 20){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.AQUA.getIndex(),true);
					 } else if( headerNum <= 34){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.BRIGHT_GREEN.getIndex(),true);
					 } else if( headerNum <= 40){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.TEAL.getIndex(),true);
					 } else if( headerNum == 41){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.YELLOW.getIndex(),true);
					 } else if( headerNum == 42){
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.YELLOW.getIndex(),true);						 
					 } else {
						 cellStyle = getMasterCellStyle(workbook, IndexedColors.WHITE.getIndex(),true); 
					 }
					 
					 headerCell.setCellStyle(cellStyle);
				}
				headerRow.setHeight((short)1200);
				
				
				int rownum = 2;
				final String ytdFormula = "TODAY()";
				
				for(final Row row : rrReportList){
					 org.apache.poi.ss.usermodel.Row normalRow = sheet.createRow(rownum++);
					 normalRow.setHeight((short)600);
					 int cellnum = 0;
					 
					 final int len = row.getActualDataList().size();
					 for(int i = 0; i < len; i++){
						Cell cell = row.getActualDataList().get(i);
						org.apache.poi.ss.usermodel.Cell exportCell = normalRow.createCell(cellnum++);
						final String cellValue = cell.getValue();
						
						if(StringUtils.isNotEmpty(cellValue)){
							switch (i) {
							case 0:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;

							case 11:
								exportCell.setCellValue(RTSUtils.convertDate(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, true));
								break;
							case 12:
								exportCell.setCellValue(RTSUtils.convertDate(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, true));
								break;
							case 13:
								//YTD
								exportCell.setCellFormula(ytdFormula);
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, true));
								break;	
							case 14:
								exportCell.setCellValue(RTSUtils.convertDate(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, true));
								break;
								
							case 15:
								exportCell.setCellType(HSSFCell.CELL_TYPE_FORMULA);
								exportCell.setCellFormula("N"+rownum+"-M"+rownum);
								CellStyle cellStyle = getNormalCellStyle(workbook, false, false);
								cellStyle.setFillForegroundColor(IndexedColors.RED.getIndex());
								cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
								exportCell.setCellStyle(cellStyle);
								break;
								
								
							case 17:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 18:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 19:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 20:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 21:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 22:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 23:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 24:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 25:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 26:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
								
							case 28:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 29:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 30:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 31:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
								
								
							case 33:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 34:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 35:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 36:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 37:
								exportCell.setCellValue(Integer.valueOf(cellValue));
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							case 39:
								exportCell.setCellValue(Integer.valueOf(cellValue));	
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;					

							default:
								exportCell.setCellValue(cellValue);
								exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
								break;
							}							
						}	 else {
							exportCell.setCellValue(cellValue);
							exportCell.setCellStyle(getNormalCellStyle(workbook, false, false));
						}					
					 }
					 
				}
				
				
				//Filter
				sheet.setAutoFilter(new CellRangeAddress(1, rrReportList.size(), 0, columns.size()));			
						
				
				log.info("Excel written successfully.");
			    
			    workbook.write(servletOutputStream);
			    servletOutputStream.flush();
			    servletOutputStream.close();
				facesContext.responseComplete();
			     
			} catch (FileNotFoundException fnfe) {
				log.error("Error in exporting Recruit Requests to Excel: " + fnfe);
			} catch (IOException ioe) {
				log.error("Error in exporting Recruit Requests to Excel: " + ioe);
			} catch (Exception e) {
				log.error("Error in exporting Recruit Requests to Excel: " + e);
			}
		}
		
		return result;
		
	}
	
	public String search(){
		String result = RTSConstants.FAILURE;
		
		System.out.println("Method searching() called.");
		rrReportList.clear();
		columns.clear();
		
		final List<RecruitRequest> recruitRequests = recruitRequestReportList.getRecruitRequests(
				createdFrom,
				createdTo, 
				requestedFrom, 
				requestedTo, 
				closedFrom, 
				closedTo, 
				createdBy != null ? createdBy.getUsrUserName() : null,
				approvedBy,
				handledBy != null ? handledBy.getUsrUserName() : null,	
				orgUnit,					
				recruitRequest != null ? recruitRequest.getRecruitRequestName() : null, 
				status); //week
		
		if(RTSUtils.isNotEmpty(recruitRequests)){
			final String pattern = SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("calendar.pattern");
			final Date today = new Date();
			
			final String[] months = new DateFormatSymbols(Locale.ENGLISH).getMonths();
			if(months != null && months.length > 0){
				final int length = months.length;
				for (int i = 0; i < length; i++) {					
					if(monthNames.get(i) == null){
						monthNames.put(i, months[i]);
					}					
				}
			}			
			
			int rowIndex = 0;
			
			for(RecruitRequest recruitRequest : recruitRequests){	
				Row row = new Row();
				List<Cell> actualDataList = new ArrayList<Cell>();
				final Date updatedOn = recruitRequest.getUpdatedDate();
				Calendar calendar = GregorianCalendar.getInstance();
				calendar.setTime(updatedOn);
				
				Cell yearCell = new Cell();
				yearCell.setValue(String.valueOf(calendar.get(Calendar.YEAR)));
				actualDataList.add(0, yearCell);
				
				Cell monthCell = new Cell();
				monthCell.setValue(String.valueOf(monthNames.get(calendar.get(Calendar.MONTH))));
				actualDataList.add(1, monthCell);
				
				Cell weekCell = new Cell();
				weekCell.setValue(RecruitRequestReportHeaders.CW + String.valueOf(calendar.get(Calendar.WEEK_OF_MONTH)));				
				actualDataList.add(2, weekCell);	
				
				Cell deptCell = new Cell();
				deptCell.setValue(recruitRequest.getOrgUnit().getUnitName());				
				actualDataList.add(3, deptCell);
				
				Cell groupCell = new Cell();
				groupCell.setValue(recruitRequest.getOrgUnit().getUnitName());				
				actualDataList.add(4, groupCell);
				
				Cell teamCell = new Cell();
				teamCell.setValue(recruitRequest.getOrgUnit().getUnitName());				
				actualDataList.add(5, teamCell);
				
				Cell statusCell = new Cell();
				statusCell.setValue(recruitRequest.getStatus());				
				actualDataList.add(6, statusCell);
				
				StringBuffer skillBuffer = new StringBuffer();
				int counter = 0;
				final Set<RecruitRequestHasSkill> skillRequirements = recruitRequest.getRecruitRequestHasSkills();
				if(RTSUtils.isNotEmpty(skillRequirements)){
					for(RecruitRequestHasSkill skillRequirement : skillRequirements){
						if(counter != 0){
							skillBuffer.append(", ");
							skillBuffer.append(skillRequirement.getSkill().getSklName());
							
						} else {
							skillBuffer.append(skillRequirement.getSkill().getSklName());
						}
						counter++;	
					}
				}
				
				Cell competencyCell = new Cell();
				competencyCell.setValue(skillBuffer.toString());				
				actualDataList.add(7, competencyCell);
				
				Cell titleCell = new Cell();
				titleCell.setValue(recruitRequest.getRecruitRequestName());				
				actualDataList.add(8, titleCell);
				
				Cell reasonCell = new Cell();
				reasonCell.setValue(recruitRequest.getAdditionalTraining());			
				actualDataList.add(9, reasonCell);
				
				Cell requesterCell = new Cell();
				requesterCell.setValue(recruitRequest.getCreatedBy());				
				actualDataList.add(10, requesterCell);				
					
				Cell requestDateCell = new Cell();
				final Date requestedDate = recruitRequest.getRequestedDate();
				requestDateCell.setValue(convertDate(requestedDate, pattern));				
				actualDataList.add(11, requestDateCell);
				
				Cell approveDateCell = new Cell();
				final Date approvedDate = recruitRequest.getApprovedDate();
				approveDateCell.setValue(convertDate(approvedDate, pattern));				
				actualDataList.add(12, approveDateCell);
				
				Cell ytdCell = new Cell();
				ytdCell.setValue(convertDate(today, pattern));				
				actualDataList.add(13, ytdCell);
				
				Cell closeCell = new Cell();
				closeCell.setValue(convertDate(recruitRequest.getClosedDate(), pattern));				
				actualDataList.add(14, closeCell);
				
				Cell durationCell = new Cell();
				durationCell.setValue(String.valueOf(daysBetween(today, approvedDate)));				
				actualDataList.add(15, durationCell);
				
				Cell handledByCell = new Cell();
				handledByCell.setValue(recruitRequest.getHandledBy());				
				actualDataList.add(16, handledByCell);
				
				Cell numberOfRequestedCell = new Cell();
				int numberOfCandidates = 0;
				if(recruitRequest.getNumberOfPersons() != null){
					numberOfCandidates = recruitRequest.getNumberOfPersons() <= 0 ? 0 : recruitRequest.getNumberOfPersons();
				}								
				numberOfRequestedCell.setValue(String.valueOf(numberOfCandidates));				
				actualDataList.add(17, numberOfRequestedCell);
				
				Cell numberOfRecruitedCell = new Cell();
				final int numberOfRecruited = recruitRequest.getNumberRecruited();
				numberOfRecruitedCell.setValue(String.valueOf(numberOfRecruited));				
				actualDataList.add(18, numberOfRecruitedCell);
				
				Cell numberOfRemainingCell = new Cell();
				numberOfRemainingCell.setValue(String.valueOf(numberOfCandidates-numberOfRecruited));				
				actualDataList.add(19, numberOfRemainingCell);
				//TARGET AND DELIVER
				
				//APPLICATION STATUS
				
				final Set<Candidate> candidates = recruitRequest.getCandidates();
				int noCVreceived = 0;
				int noCVscreened = 0;
				int noCVshortList = 0;
				
				int noCV1stInterviewed = 0;
				int noCV1stInterviewedPassed = 0;
				int noCV1stInterviewedFailed = 0;
				int noCV1stInterviewedOnHold = 0;
				int noCV1stInterviewedScheduled = 0;
				
				int noCV2ndInterviewed = 0;
				int noCV2ndInterviewedScheduled = 0;
				int noCV2ndInterviewedPassed = 0;
				int noCV2ndInterviewedFailed = 0;
				int noCV2ndInterviewedOnHold = 0;
				
				//offer status
				int noOfferToSend = 0;
				int noOfferSent = 0;
				int noOfferAccepted = 0;
				int noOfferRefused = 0;
				int noCandidateJoined = 0;
				
				if(RTSUtils.isNotEmpty(candidates)){
					noCVreceived = candidates.size();
					for(Candidate candidate : candidates){
						if(candidate.getCddStatus().equals(CandidateStatus.SCREENED)){
							noCVscreened++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.SHORT_LISTED)){
							noCVshortList++;							
						} else if(candidate.getCddStatus().equals(CandidateStatus.TECHNICAL_PASS)){
							noCV1stInterviewedPassed++;
							noCV1stInterviewed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.TECHNICAL_FAIL)){
							noCV1stInterviewedFailed++;
							noCV1stInterviewed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.TECHNICAL_ON_HOLD)){
							noCV1stInterviewedOnHold++;
							noCV1stInterviewed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.TECHNICAL_SCHEDULED)){
							noCV1stInterviewedScheduled++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.HR_SCHEDULED)){
							noCV2ndInterviewedScheduled++;
							noCV1stInterviewed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.HR_PASS)){
							noCV1stInterviewed++;
							noCV2ndInterviewed++;
							noCV2ndInterviewedPassed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.HR_ON_HOLD)){
							noCV1stInterviewed++;
							noCV2ndInterviewed++;
							noCV2ndInterviewedOnHold++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.HR_FAIL)){
							noCV1stInterviewed++;
							noCV2ndInterviewed++;
							noCV2ndInterviewedFailed++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.TO_OFFER)){
							noOfferToSend++;
						}  else if(candidate.getCddStatus().equals(CandidateStatus.OFFERED)){
							noOfferSent++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.OFFER_ACCEPTED)){
							noOfferSent++;
							noOfferAccepted++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.OFFER_REFUSED)){
							noOfferSent++;
							noOfferRefused++;
						} else if(candidate.getCddStatus().equals(CandidateStatus.JOINED)){
							noOfferSent++;
							noOfferAccepted++;
							noCandidateJoined++;							
						}
						
					}
				} 
				
				Cell noCVreceivedCell = new Cell();
				noCVreceivedCell.setValue(String.valueOf(noCVreceived));				
				actualDataList.add(20, noCVreceivedCell);					
				
				Cell noCVscreenedCell = new Cell();
				noCVscreenedCell.setValue(String.valueOf(noCVscreened));				
				actualDataList.add(21, noCVscreenedCell);
				
				Cell noCVshortListCell = new Cell();
				noCVshortListCell.setValue(String.valueOf(noCVshortList));				
				actualDataList.add(22, noCVshortListCell);
								
				Cell noCV1stInterviewedCell = new Cell();
				noCV1stInterviewedCell.setValue(String.valueOf(noCV1stInterviewed));				
				actualDataList.add(23, noCV1stInterviewedCell);
				
				Cell noCV1stInterviewPassedCell = new Cell();
				noCV1stInterviewPassedCell.setValue(String.valueOf(noCV1stInterviewedPassed));				
				actualDataList.add(24, noCV1stInterviewPassedCell);
				
				Cell noCV1stInterviewFailedCell = new Cell();
				noCV1stInterviewFailedCell.setValue(String.valueOf(noCV1stInterviewedFailed));				
				actualDataList.add(25, noCV1stInterviewFailedCell);
				
				Cell noCV1stInterviewOnHoldCell = new Cell();
				noCV1stInterviewOnHoldCell.setValue(String.valueOf(noCV1stInterviewedOnHold));				
				actualDataList.add(26, noCV1stInterviewOnHoldCell);
				
				Cell noCV1stInterviewReasonsCell = new Cell();
				noCV1stInterviewReasonsCell.setValue(String.valueOf(""));				
				actualDataList.add(27, noCV1stInterviewReasonsCell);
				
				Cell noCV2ndInterviewCell = new Cell();
				noCV2ndInterviewCell.setValue(String.valueOf(noCV2ndInterviewed));				
				actualDataList.add(28, noCV2ndInterviewCell);
				
				Cell noCV2ndInterviewPassedCell = new Cell();
				noCV2ndInterviewPassedCell.setValue(String.valueOf(noCV2ndInterviewedPassed));				
				actualDataList.add(29, noCV2ndInterviewPassedCell);
				
				Cell noCV2ndInterviewFailedCell = new Cell();
				noCV2ndInterviewFailedCell.setValue(String.valueOf(noCV2ndInterviewedFailed));				
				actualDataList.add(30, noCV2ndInterviewFailedCell);
				
				Cell noCV2ndInterviewOnHoldCell = new Cell();
				noCV2ndInterviewOnHoldCell.setValue(String.valueOf(noCV2ndInterviewedOnHold));				
				actualDataList.add(31, noCV2ndInterviewOnHoldCell);
				
				Cell noCV2ndInterviewReasonCell = new Cell();
				noCV2ndInterviewReasonCell.setValue(String.valueOf(""));				
				actualDataList.add(32, noCV2ndInterviewReasonCell);
				
				Cell noCVinterviewedCell = new Cell();
				noCVinterviewedCell.setValue(String.valueOf(noCV1stInterviewedScheduled));				
				actualDataList.add(33, noCVinterviewedCell);
				//APPLICATIONS STATUS
				
				//OFFER STATUS
				Cell noOfferToSendCell = new Cell();
				noOfferToSendCell.setValue(String.valueOf(noOfferToSend));				
				actualDataList.add(34, noOfferToSendCell);
				
				Cell noOfferSentCell = new Cell();
				noOfferSentCell.setValue(String.valueOf(noOfferSent));				
				actualDataList.add(35, noOfferSentCell);
				
				Cell noOfferAcceptedCell = new Cell();
				noOfferAcceptedCell.setValue(String.valueOf(noOfferAccepted));				
				actualDataList.add(36, noOfferAcceptedCell);
				
				Cell noOfferRefusedCell = new Cell();
				noOfferRefusedCell.setValue(String.valueOf(noOfferRefused));				
				actualDataList.add(37, noOfferRefusedCell);				

				Cell offerReasonsCell = new Cell();
				offerReasonsCell.setValue("");				
				actualDataList.add(38, offerReasonsCell);
				
				Cell noCandidateJoinedCell = new Cell();
				noCandidateJoinedCell.setValue(String.valueOf(noCandidateJoined));				
				actualDataList.add(39, noCandidateJoinedCell);		
				//OFFER STATUS
				
				//NOTE
				Cell recruiterNoteCell = new Cell();
				recruiterNoteCell.setValue(recruitRequest.getDescription());				
				actualDataList.add(40, recruiterNoteCell);
				//NOTE
				
				//WEEKLY STATUS
				Cell weeklyStatusUpdateCell = new Cell();
				weeklyStatusUpdateCell.setValue(String.valueOf(""));				
				actualDataList.add(41, weeklyStatusUpdateCell);
				//WEEKLY STATUS
				
				row.setActualDataList(actualDataList);
				row.setRowIndex(rowIndex);
				
				System.out.println("rowIndex: " + rowIndex);
				rrReportList.add(rowIndex, row);
				
				rowIndex++;	
			}
			
			
			//add row to list
			//rrReportList.add(row);
			
			if(RTSUtils.isNotEmpty(rrReportList)){
				initColumnsHeaders();
				
				System.out.println("rrReportList..........." + rrReportList.size());
			}
			
			
		}
		
		System.out.println("Searching done...........");
		return result;
		
		
		
	}		
	
	//@Out(required = false, scope = ScopeType.EVENT)
	private List<Row> rrReportList = new ArrayList<Row>();
	

	public void setRrReportList(List<Row> rrReportList) {
		this.rrReportList = rrReportList;
	}

	public List<Row> getRrReportList() {
		return rrReportList;
	}

	public void setColumns(ArrayList<Column> columns) {
		this.columns = columns;
	}

	public ArrayList<Column> getColumns() {
		return columns;
	}

	private ArrayList<Column> columns = new ArrayList<Column>();
	
	public void initColumnsHeaders() {
		columns.clear();
		String header;
		String background;
		String footer = "";
		
		header = RecruitRequestReportHeaders.COLUMN_YEAR;
		background = "#d0d0d0";
		Column column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_MONTH;
		background = "#d0d0d0";
		column = new Column(header, footer, background);		
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_WEEK;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_DEPT;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_GROUP;
		background = "d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_TEAM_PROJECT;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_REQUEST_STATUS;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_COMPETENCY;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_TITLE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
		header = RecruitRequestReportHeaders.COLUMN_REASON;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_REQUESTER;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_REQUEST_DATE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_APPROVE_DATE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_YTD;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_CLOSE_DATE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_DURATION;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_HANDLED_BY;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NUMBER_REQUESTED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NUMBER_RECRUITED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NUMBER_REMAINING;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_RECEIVED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_SCRENED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_SHORTLIST;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_INTERVIEWED_1ST_ROUND;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_PASSED_1ST_ROUND;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_SHORTLIST;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_ON_HOLD;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_REASONS_OF_ON_HOLD_CASES;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_INTERVIEWED_2ND_ROUND;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_PASSED_2ND_ROUND;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_FAILED_2ND_ROUND;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_ON_HOLD;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_REASONS_OF_ON_HOLD_CASES;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_TO_INTERVIEW;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_CVS_TO_OFFER;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_OFFER_SENT;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_OFFER_CONFIRMED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NO_OF_OFFER_REFUSED;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);		
		header = RecruitRequestReportHeaders.COLUMN_REASONS;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_NUMBER_OF_JOINED_CANDIDATE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = RecruitRequestReportHeaders.COLUMN_RECRUITER_NOTE;
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		header = "";
		background = "#d0d0d0";
		column = new Column(header, footer, background);
		columns.add(column);
		
	}	
	
	private Map<Integer, String> monthNames = new HashMap<Integer, String>();
	
	private String convertDate(Date component, String pattern){
		if(component != null){
			Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
			calendar.setTime(component);
			
			Date date = calendar.getTime();
			String formattedDate = new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);
			return formattedDate;
		}
		
		return null;
	}
	
	private int daysBetween(Date startDate, Date endDate){	
		return Days.daysBetween(new LocalDate(endDate), new LocalDate(startDate)).getDays();
	}
	
	
	private CellStyle getMasterCellStyle(HSSFWorkbook workbook, final short backgroundColor, final boolean wrap){
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);		
		cellStyle.setFillForegroundColor(backgroundColor);
		cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		
		if(wrap){
			cellStyle.setWrapText(true);
		}
		
		return cellStyle;
	}
	
	private CellStyle getNormalCellStyle(HSSFWorkbook workbook, final boolean wrap, final boolean isDate){
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		if(wrap){
			cellStyle.setWrapText(true);
		}
		if(isDate){
			CreationHelper creationHelper = workbook.getCreationHelper();
			cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd/MM/yyyy"));
		}
		
		
		return cellStyle;
	}
	
	
	public void setAvailableOrgUnits(List<OrgUnit> availableOrgUnits) {
		this.availableOrgUnits = availableOrgUnits;
	}

	public List<OrgUnit> getAvailableOrgUnits() {
		return availableOrgUnits;
	}

	private List<OrgUnit> availableOrgUnits = new ArrayList<OrgUnit>();
	
	public void setAvailableRecruitRequests(List<RecruitRequest> availableRecruitRequests) {
		this.availableRecruitRequests = availableRecruitRequests;
	}

	public List<RecruitRequest> getAvailableRecruitRequests() {
		return availableRecruitRequests;
	}

	private List<RecruitRequest> availableRecruitRequests = new ArrayList<RecruitRequest>();
	
	/** The created from. */
	private Date createdFrom;
	
	/** The created to. */
	private Date createdTo;
	

	/** The org unit. */
	private OrgUnit orgUnit;
	
	/** The recruit request. */
	private RecruitRequest recruitRequest;
	
	
	/** The created by. */
	private User createdBy;	
	
	/** The handled by. */
	private User handledBy;
	
	/** The short list by. */
	private User shortListBy;

	/**
	 * @return the createdFrom
	 */
	public Date getCreatedFrom() {
		return this.createdFrom;
	}

	/**
	 * @param createdFrom the createdFrom to set
	 */
	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}

	/**
	 * @return the createdTo
	 */
	public Date getCreatedTo() {
		return this.createdTo;
	}

	/**
	 * @param createdTo the createdTo to set
	 */
	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
	}

	/**
	 * @return the orgUnit
	 */
	public OrgUnit getOrgUnit() {
		return this.orgUnit;
	}

	/**
	 * @param orgUnit the orgUnit to set
	 */
	public void setOrgUnit(OrgUnit orgUnit) {
		this.orgUnit = orgUnit;
	}

	/**
	 * @return the recruitRequest
	 */
	public RecruitRequest getRecruitRequest() {
		return this.recruitRequest;
	}

	/**
	 * @param recruitRequest the recruitRequest to set
	 */
	public void setRecruitRequest(RecruitRequest recruitRequest) {
		this.recruitRequest = recruitRequest;
	}

	/**
	 * @return the createdBy
	 */
	public User getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the handledBy
	 */
	public User getHandledBy() {
		return this.handledBy;
	}

	/**
	 * @param handledBy the handledBy to set
	 */
	public void setHandledBy(User handledBy) {
		this.handledBy = handledBy;
	}

	/**
	 * @return the shortListBy
	 */
	public User getShortListBy() {
		return this.shortListBy;
	}

	/**
	 * @param shortListBy the shortListBy to set
	 */
	public void setShortListBy(User shortListBy) {
		this.shortListBy = shortListBy;
	}	
	
	private String status;	
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Populate recruit requests.
	 */
	public void populateRecruitRequests(){
		availableRecruitRequests.clear();
		if (this.orgUnit != null){
			final String LEVEL_PATH = this.orgUnit.getLevelPath();
			availableRecruitRequests = recruitRequestList.getRecruitRequestReportByOrgUnitLevelPath(LEVEL_PATH);			
		}	
	}
	
	
	public void resetFormFields(){
		this.setCreatedFrom(null);
		this.setCreatedTo(null);
		this.setOrgUnit(null);
		this.getAvailableRecruitRequests().clear();
		this.setStatus(null);
		this.getRrReportList().clear();
		this.getColumns().clear();
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

	private Date requestedFrom;
	private Date requestedTo;
	private Date closedFrom;
	private Date closedTo;
	private User approvedBy;

	/**
	 * @return the requestedFrom
	 */
	public Date getRequestedFrom() {
		return this.requestedFrom;
	}

	/**
	 * @param requestedFrom the requestedFrom to set
	 */
	public void setRequestedFrom(Date requestedFrom) {
		this.requestedFrom = requestedFrom;
	}

	/**
	 * @return the requestedTo
	 */
	public Date getRequestedTo() {
		return this.requestedTo;
	}

	/**
	 * @param requestedTo the requestedTo to set
	 */
	public void setRequestedTo(Date requestedTo) {
		this.requestedTo = requestedTo;
	}

	/**
	 * @return the closedFrom
	 */
	public Date getClosedFrom() {
		return this.closedFrom;
	}

	/**
	 * @param closedFrom the closedFrom to set
	 */
	public void setClosedFrom(Date closedFrom) {
		this.closedFrom = closedFrom;
	}

	/**
	 * @return the closedTo
	 */
	public Date getClosedTo() {
		return this.closedTo;
	}

	/**
	 * @param closedTo the closedTo to set
	 */
	public void setClosedTo(Date closedTo) {
		this.closedTo = closedTo;
	}

	/**
	 * @return the approvedBy
	 */
	public User getApprovedBy() {
		return this.approvedBy;
	}

	/**
	 * @param approvedBy the approvedBy to set
	 */
	public void setApprovedBy(User approvedBy) {
		this.approvedBy = approvedBy;
	}	
	
	
}