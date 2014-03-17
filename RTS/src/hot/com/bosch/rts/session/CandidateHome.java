/*
 * com.bosch.rts.session.CandidateHome.java
 */
package com.bosch.rts.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import javax.transaction.SystemException;

import org.hibernate.validator.Length;
import org.hibernate.validator.Pattern;
import org.jboss.seam.Component;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Transactional;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.SeamResourceBundle;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.framework.EntityHome;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.security.Credentials;

import com.bosch.rts.bus.CandidateListBean;
import com.bosch.rts.bus.CandidateListBean.FilterBy;
import com.bosch.rts.bus.ChosenSkill;
import com.bosch.rts.bus.FileUploadBean;
import com.bosch.rts.bus.SkillPickBean;
import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateHasAttachment;
import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.CandidateStatus;
import com.bosch.rts.entity.InterviewHistory;
import com.bosch.rts.entity.InterviewSchedule;
import com.bosch.rts.entity.OrgUnit;
import com.bosch.rts.entity.RecruitRequest;
import com.bosch.rts.entity.Skill;
import com.bosch.rts.entity.User;
import com.bosch.rts.utilities.RTSConstants;
import com.bosch.rts.utilities.RTSUtils;
import com.bosch.rts.utilities.UploadFileInfo;

/**
 * Candidate Home for Candidate module.
 *
 * @author khb1hc
 */
@Name("candidateHome")
@Scope(value = ScopeType.CONVERSATION)
public class CandidateHome extends EntityHome<Candidate> {

	/** serialVersionUID. */
	private static final long serialVersionUID = 3806341032055723735L;

	/** The recruit request home. */
	@In(create = true)
	private RecruitRequestHome recruitRequestHome;

	/** The user home. */
	@In(create = true)
	private UserHome userHome;

	/** The credentials. */
	@In
	private Credentials credentials;

	/** The log. */
	@Logger
	private transient Log log;

	/** The status messages. */
	@In
	private transient StatusMessages statusMessages;

	/** The candidate has skill home. */
	@In(create = true)
	private CandidateHasSkillHome candidateHasSkillHome;

	/** The skill pick bean. */
	@In(create = true)
	private SkillPickBean skillPickBean;

	/** The tmp chosen skill map. */
	private Map<Integer, ChosenSkill> tmpChosenSkillMap = new HashMap<Integer, ChosenSkill>();

	/** The name email validation. */
	private String nameEmailValidation;

	/** The contact number1. */
	private String contactNumber1;
	
	/** The contact number2. */
	private String contactNumber2;

	/** The con1 selected candidate. */
	private String con1SelectedCandidate;
	
	/** The con2 selected candidate. */
	private String con2SelectedCandidate;
	
	/** The file upload bean. */
	private FileUploadBean fileUploadBean = new FileUploadBean();

	@In(create = true)
	private OrgUnitList orgUnitList;

	/** The recruit request list. */
	@In(create = true)
	private RecruitRequestList recruitRequestList;

	/** The candidate list. */
	@In(create = true, required = false)
	private CandidateList candidateList;

	/** The selected university. */
	private String selectedUniversity;

	/** The other faculty. */
	private String otherFaculty;

	/** The recruit request document saver. */
	@In(create = true)
	private RecruitRequestDocumentSaver recruitRequestDocumentSaver;
	
	
	/** The messages. */
	@In
	private Map<String, String> messages;

	/** The tmp attachments. */
	private Set<CandidateHasAttachment> tmpAttachments = new HashSet<CandidateHasAttachment>();
	
	/** The tmp candidate has skills. */
	private Set<CandidateHasSkill> tmpCandidateHasSkills = new HashSet<CandidateHasSkill>();
	
	/** The exist candidates_ name. */
	private List<Candidate> existCandidates_Name = new ArrayList<Candidate>();
	
	/** The exist candidates_ email. */
	private List<Candidate> existCandidates_Email = new ArrayList<Candidate>();
	
	/** The existing email. */
	private Candidate existingEmail;
	
	/** The selected candidate. */
	private Candidate selectedCandidate;
	
	/** The cdd name. */
	private String cddName;
	
	/** The cdd email. */
	private String cddEmail;
	
	/** The curr recruit request name. */
	private String currRecruitRequestName;
	
	/** The curr candidate name. */
	private String currCandidateName;
	
	/** The curr email. */
	private String currEmail;
	
	/** The curr phone. */
	private String currPhone;

	/**
	 * Gets the curr phone.
	 *
	 * @return the curr phone
	 */
	public String getCurrPhone() {
		return currPhone;
	}

	/**
	 * Sets the curr phone.
	 *
	 * @param currPhone the new curr phone
	 */
	public void setCurrPhone(String currPhone) {
		this.currPhone = currPhone;
	}

	/**
	 * Gets the curr recruit request name.
	 *
	 * @return the curr recruit request name
	 */
	public String getCurrRecruitRequestName() {
		return currRecruitRequestName;
	}

	/**
	 * Gets the curr email.
	 *
	 * @return the curr email
	 */
	public String getCurrEmail() {
		return currEmail;
	}

	/**
	 * Sets the curr email.
	 *
	 * @param currEmail the new curr email
	 */
	public void setCurrEmail(String currEmail) {
		this.currEmail = currEmail;
	}

	/**
	 * Sets the curr recruit request name.
	 *
	 * @param currRecruitRequestName the new curr recruit request name
	 */
	public void setCurrRecruitRequestName(String currRecruitRequestName) {
		this.currRecruitRequestName = currRecruitRequestName;
	}

	/**
	 * Gets the curr candidate name.
	 *
	 * @return the curr candidate name
	 */
	public String getCurrCandidateName() {
		return currCandidateName;
	}

	/**
	 * Sets the curr candidate name.
	 *
	 * @param currCandidateName the new curr candidate name
	 */
	public void setCurrCandidateName(String currCandidateName) {
		this.currCandidateName = currCandidateName;
	}

	/**
	 * Gets the cdd name.
	 *
	 * @return the cdd name
	 */
	public String getCddName() {
		return cddName;
	}

	/**
	 * Sets the cdd name.
	 *
	 * @param cddName the new cdd name
	 */
	public void setCddName(String cddName) {
		this.cddName = cddName;
	}

	/**
	 * Gets the cdd email.
	 *
	 * @return the cdd email
	 */
	public String getCddEmail() {
		return cddEmail;
	}

	/**
	 * Sets the cdd email.
	 *
	 * @param cddEmail the new cdd email
	 */
	public void setCddEmail(String cddEmail) {
		this.cddEmail = cddEmail;
	}

	/**
	 * Gets the existing email.
	 *
	 * @return the existing email
	 */
	public Candidate getExistingEmail() {
		return existingEmail;
	}

	/**
	 * Sets the existing email.
	 *
	 * @param existingEmail the new existing email
	 */
	public void setExistingEmail(Candidate existingEmail) {
		this.existingEmail = existingEmail;
	}

	/**
	 * Gets the tmp chosen skill map.
	 *
	 * @return the tmp chosen skill map
	 */
	public Map<Integer, ChosenSkill> getTmpChosenSkillMap() {
		return tmpChosenSkillMap;
	}

	/**
	 * Sets the tmp chosen skill map.
	 *
	 * @param tmpChosenSkillMap the tmp chosen skill map
	 */
	public void setTmpChosenSkillMap(Map<Integer, ChosenSkill> tmpChosenSkillMap) {
		this.tmpChosenSkillMap = tmpChosenSkillMap;
	}

	/**
	 * Gets the name email validation.
	 *
	 * @return the name email validation
	 */
	public String getNameEmailValidation() {
		return nameEmailValidation;
	}

	/**
	 * Sets the name email validation.
	 *
	 * @param nameEmailValidation the new name email validation
	 */
	public void setNameEmailValidation(String nameEmailValidation) {
		this.nameEmailValidation = nameEmailValidation;
	}

	/**
	 * Gets the tmp candidate has skills.
	 *
	 * @return the tmp candidate has skills
	 */
	public Set<CandidateHasSkill> getTmpCandidateHasSkills() {
		return tmpCandidateHasSkills;
	}

	/**
	 * Sets the tmp candidate has skills.
	 *
	 * @param tmpCandidateHasSkills the new tmp candidate has skills
	 */
	public void setTmpCandidateHasSkills(Set<CandidateHasSkill> tmpCandidateHasSkills) {
		this.tmpCandidateHasSkills = tmpCandidateHasSkills;
	}

	/**
	 * Gets the selected candidate.
	 *
	 * @return the selected candidate
	 */
	public Candidate getSelectedCandidate() {
		return selectedCandidate;
	}

	/**
	 * Sets the selected candidate.
	 *
	 * @param selectedCandidate the new selected candidate
	 */
	public void setSelectedCandidate(Candidate selectedCandidate) {
		this.selectedCandidate = selectedCandidate;
	}

	/**
	 * New selected candidate.
	 */
	public void newSelectedCandidate() {
		selectedCandidate = new Candidate();
	}

	/**
	 * Gets the exist candidates_ name.
	 *
	 * @return the exist candidates_ name
	 */
	public List<Candidate> getExistCandidates_Name() {
		return existCandidates_Name;
	}

	/**
	 * Sets the exist candidates_ name.
	 *
	 * @param existCandidates_Name the new exist candidates_ name
	 */
	public void setExistCandidates_Name(List<Candidate> existCandidates_Name) {
		this.existCandidates_Name = existCandidates_Name;
	}

	/**
	 * Gets the exist candidates_ email.
	 *
	 * @return the exist candidates_ email
	 */
	public List<Candidate> getExistCandidates_Email() {
		return existCandidates_Email;
	}
	
	/** The cdd status. */
	private CandidateStatus cddStatus = null;	

	/**
	 * Sets the exist candidates_ email.
	 *
	 * @param existCandidates_Email the new exist candidates_ email
	 */
	public void setExistCandidates_Email(List<Candidate> existCandidates_Email) {
		this.existCandidates_Email = existCandidates_Email;
	}

	/**
	 * Gets the con1 selected candidate.
	 *
	 * @return the con1 selected candidate
	 */
	public String getCon1SelectedCandidate() {
		String contactNumberRaw = null;
		if (this.selectedCandidate != null) {
			contactNumberRaw = this.getSelectedCandidate().getCddPhoneNo() != null ? this.getSelectedCandidate()
					.getCddPhoneNo() : null;
		}

		con1SelectedCandidate = this.parseContactNumber(0, contactNumberRaw);
		return con1SelectedCandidate;
	}

	/**
	 * Sets the con1 selected candidate.
	 *
	 * @param con1SelectedCandidate the new con1 selected candidate
	 */
	public void setCon1SelectedCandidate(String con1SelectedCandidate) {
		this.con1SelectedCandidate = con1SelectedCandidate;
	}

	/**
	 * Gets the con2 selected candidate.
	 *
	 * @return the con2 selected candidate
	 */
	public String getCon2SelectedCandidate() {
		String contactNumberRaw = null;
		if (this.selectedCandidate != null) {
			contactNumberRaw = this.getSelectedCandidate().getCddPhoneNo() != null ? this.getSelectedCandidate()
					.getCddPhoneNo() : null;
		}
		con2SelectedCandidate = this.parseContactNumber(1, contactNumberRaw);
		return con2SelectedCandidate;
	}

	/**
	 * Sets the con2 selected candidate.
	 *
	 * @param con2SelectedCandidate the new con2 selected candidate
	 */
	public void setCon2SelectedCandidate(String con2SelectedCandidate) {
		this.con2SelectedCandidate = con2SelectedCandidate;
	}

	/**
	 * Gets the tmp attachments.
	 *
	 * @return the tmp attachments
	 */
	public Set<CandidateHasAttachment> getTmpAttachments() {
		return tmpAttachments;
	}

	/**
	 * Sets the tmp attachments.
	 *
	 * @param tmpAttachments the new tmp attachments
	 */
	public void setTmpAttachments(Set<CandidateHasAttachment> tmpAttachments) {
		this.tmpAttachments = tmpAttachments;
	}

	/**
	 * Gets the other faculty.
	 *
	 * @return the other faculty
	 */
	public String getOtherFaculty() {
		return otherFaculty;
	}

	/**
	 * Sets the other faculty.
	 *
	 * @param otherFaculty the new other faculty
	 */
	public void setOtherFaculty(String otherFaculty) {
		this.otherFaculty = otherFaculty;
	}

	/**
	 * Gets the selected university.
	 *
	 * @return the selected university
	 */
	public String getSelectedUniversity() {
		return selectedUniversity;
	}

	/**
	 * Sets the selected university.
	 *
	 * @param selectedUniversity the new selected university
	 */
	public void setSelectedUniversity(String selectedUniversity) {
		this.selectedUniversity = selectedUniversity;
	}

	/**
	 * Gets the selected faculty.
	 *
	 * @return the selected faculty
	 */
	public String getSelectedFaculty() {
		return selectedFaculty;
	}

	/**
	 * Sets the selected faculty.
	 *
	 * @param selectedFaculty the new selected faculty
	 */
	public void setSelectedFaculty(String selectedFaculty) {
		this.selectedFaculty = selectedFaculty;
	}

	/** The selected faculty. */
	private String selectedFaculty;
	
	/** The other unversity. */
	private String otherUnversity;

	/**
	 * Gets the other unversity.
	 *
	 * @return the other unversity
	 */
	public String getOtherUnversity() {
		return otherUnversity;
	}

	/**
	 * Sets the other unversity.
	 *
	 * @param otherUnversity the new other unversity
	 */
	public void setOtherUnversity(String otherUnversity) {
		this.otherUnversity = otherUnversity;
	}

	/** The selected org unit. */
	private OrgUnit selectedOrgUnit;

	/**
	 * Gets the selected org unit.
	 *
	 * @return the selected org unit
	 */
	public OrgUnit getSelectedOrgUnit() {
		return selectedOrgUnit;
	}

	/**
	 * Sets the selected org unit.
	 *
	 * @param selectedOrgUnit the new selected org unit
	 */
	public void setSelectedOrgUnit(OrgUnit selectedOrgUnit) {
		this.selectedOrgUnit = selectedOrgUnit;
	}

	/**
	 * Gets the contact number1.
	 *
	 * @return the contact number1
	 */
	@Length(max = 15)
	@Pattern(regex = "[[0-9]-+/() ]*", message = "#{messages['invalid.candidate.phone.number']}")
	public String getContactNumber1() {
		String contactNumberRaw = null;
		if (this.getInstance() != null && this.getInstance().getCddPhoneNo() != null) {
			contactNumberRaw = this.getInstance().getCddPhoneNo();
		}
		contactNumber1 = this.parseContactNumber(0, contactNumberRaw);
		return contactNumber1;
	}

	/**
	 * Sets the contact number1.
	 *
	 * @param contactNumber1 the new contact number1
	 */
	public void setContactNumber1(String contactNumber1) {
		this.contactNumber1 = contactNumber1;
	}

	/**
	 * Gets the contact number2.
	 *
	 * @return the contact number2
	 */
	@Length(max = 15)
	@Pattern(regex = "[[0-9]-+/() ]*", message = "#{messages['invalid.candidate.phone.number']}")
	public String getContactNumber2() {
		String contactNumberRaw = null;
		if (this.getInstance() != null && this.getInstance().getCddPhoneNo() != null) {
			contactNumberRaw = this.getInstance().getCddPhoneNo();
		}
		contactNumber2 = this.parseContactNumber(1, contactNumberRaw);
		return contactNumber2;
	}

	/**
	 * Sets the contact number2.
	 *
	 * @param contactNumber2 the new contact number2
	 */
	public void setContactNumber2(String contactNumber2) {
		this.contactNumber2 = contactNumber2;
	}

	/**
	 * Gets the file upload bean.
	 *
	 * @return the file upload bean
	 */
	public FileUploadBean getFileUploadBean() {
		return fileUploadBean;
	}

	/**
	 * Sets the file upload bean.
	 *
	 * @param fileUploadBean the new file upload bean
	 */
	public void setFileUploadBean(FileUploadBean fileUploadBean) {
		this.fileUploadBean = fileUploadBean;
	}

	/**
	 * Sets the candidate cdd candidate id.
	 *
	 * @param id the new candidate cdd candidate id
	 */
	public void setCandidateCddCandidateId(Integer id) {
		setId(id);
	}

	/**
	 * Gets the candidate cdd candidate id.
	 *
	 * @return the candidate cdd candidate id
	 */
	public Integer getCandidateCddCandidateId() {
		return (Integer) getId();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#createInstance()
	 */
	@Override
	protected Candidate createInstance() {
		Candidate candidate = new Candidate();
		return candidate;
	}

	/**
	 * Load.
	 */
	public void load() {
		if (isIdDefined()) {
			wire();
		}
	}

	/**
	 * Wire.
	 */
	public void wire() {
		getInstance();
		RecruitRequest recruitRequest = recruitRequestHome.getDefinedInstance();
		if (recruitRequest != null) {
			getInstance().setRecruitRequest(recruitRequest);
		}
		User user = userHome.getDefinedInstance();
		if (user != null) {
			getInstance().setUser(user);
		}
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#setInstance(java.lang.Object)
	 */
	@Override
	public void setInstance(Candidate instance) {
		super.setInstance(instance);
		if (instance != null && instance.getRecruitRequest() != null) {
			getInstance().setRecruitRequest(instance.getRecruitRequest());
		}
	}

	/**
	 * Update.
	 *
	 * @param candidate the candidate
	 * @return the string
	 */
	@Transactional
	public String update(Candidate candidate) {
		this.setInstance(candidate);
		this.setId(candidate.getCddCandidateId());
		this.getInstance().setRecruitRequest(candidate.getRecruitRequest());

		final String result = super.update();
		return result;
	}

	/**
	 * Checks if is wired.
	 *
	 * @return true, if is wired
	 */
	public boolean isWired() {
		return true;
	}

	/**
	 * Gets the defined instance.
	 *
	 * @return the defined instance
	 */
	public Candidate getDefinedInstance() {
		return isIdDefined() ? getInstance() : null;
	}

	/**
	 * Gets the candidate has skills.
	 *
	 * @return the candidate has skills
	 */
	public List<CandidateHasSkill> getCandidateHasSkills() {
		return getInstance() == null ? null : new ArrayList<CandidateHasSkill>(getInstance().getCandidateHasSkills());
	}

	/**
	 * Gets the interview histories.
	 *
	 * @return the interview histories
	 */
	public List<InterviewHistory> getInterviewHistories() {
		return getInstance() == null ? null : new ArrayList<InterviewHistory>(getInstance().getInterviewHistories());
	}

	/**
	 * Gets the interview schedules.
	 *
	 * @return the interview schedules
	 */
	public List<InterviewSchedule> getInterviewSchedules() {
		return getInstance() == null ? null : new ArrayList<InterviewSchedule>(getInstance().getInterviewSchedules());
	}

	/**
	 * Find candidate by list of org unit for interview schedule.
	 *
	 * @param orgUnitList the org unit list
	 * @return the list
	 */
	public List<Candidate> findCandidateByListOfOrgUnitForInterviewSchedule(List<OrgUnit> orgUnitList) {
		return candidateList.findCandidateByListOfOrgUnitForInterviewSchedule(orgUnitList);
	}

	/**
	 * Find all candidate for interview schedule.
	 *
	 * @return the list
	 */
	public List<Candidate> findAllCandidateForInterviewSchedule() {
		return candidateList.findAllCandidateForInterviewSchedule();
	}

	/**
	 * Find candidate by org unit for interview schedule list.
	 *
	 * @param orgUnitID the org unit id
	 * @return the list
	 */
	public List<Candidate> findCandidateByOrgUnitForInterviewScheduleList(final int orgUnitID) {
		return candidateList.findCandidateByOrgUnitForInterviewScheduleList(orgUnitID);
	}

	/**
	 * Find candidate by user.
	 *
	 * @param user the user
	 * @return the list
	 */
	public List<Candidate> findCandidateByUser(User user) {
		return candidateList.findCandidateByUser(user);
	}

	/**
	 * Find all candidate for interview schedule list.
	 *
	 * @return the list
	 */
	public List<Candidate> findAllCandidateForInterviewScheduleList() {
		return candidateList.findAllCandidateForInterviewScheduleList();
	}

	/**
	 * Check email existed.
	 *
	 * @param email the email
	 * @return the candidate
	 */
	public Candidate checkEmailExisted(final String email) {
		return candidateList.checkEmailExisted(email);
	}

	/**
	 * Parses the contact number.
	 *
	 * @param ordinal the ordinal
	 * @param contactNumberRaw the contact number raw
	 * @return the string
	 */
	public String parseContactNumber(int ordinal, String contactNumberRaw) {
		if (contactNumberRaw != null && !contactNumberRaw.isEmpty()) {
			String[] contactNumbers = contactNumberRaw.split(RTSConstants.SEMI_COLON);
			if (contactNumbers.length > ordinal) {
				return contactNumbers[ordinal];
			}
		}
		return null;
	}

	/**
	 * Fetch skill pick.
	 */
	public void fetchSkillPick() {
		if (this.getInstance() != null && this.nameEmailValidation != null && !this.nameEmailValidation.isEmpty()) {
			skillPickBean.setChosenSkillMap(tmpChosenSkillMap);
		}
	}

	/**
	 * Creates the candidate.
	 *
	 * @return the string
	 * @throws IllegalStateException the illegal state exception
	 * @throws SecurityException the security exception
	 * @throws SystemException the system exception
	 */
	public String createCandidate() throws IllegalStateException, SecurityException, SystemException {
		statusMessages.clear();
		String result = RTSConstants.FAILURE;

		tmpChosenSkillMap = skillPickBean.getChosenSkillMap();					

		try {

			String contactNumbers = contactNumber1;
			if (contactNumber2 != null && !contactNumber2.isEmpty()) {
				contactNumbers = contactNumbers + ";" + contactNumber2;
			}
			this.getInstance().setCddPhoneNo(contactNumbers);
			this.getInstance().setCddStatus(CandidateStatus.NEW);
			this.getInstance().setCddRequestNumber(0);

			final Date currentDate = new Date();
			final String username = credentials.getUsername();
			this.getInstance().setCddCreatedDate(currentDate);
			this.getInstance().setCddCreatedBy(username);
			this.getInstance().setCddUpdatedDate(currentDate);
			this.getInstance().setCddUpdatedBy(username);

			if (selectedUniversity != null && !selectedUniversity.isEmpty()) {
				if (!selectedUniversity.equalsIgnoreCase(RTSConstants.OTHER)) {
					this.getInstance().setCddGraduationUniversity(selectedUniversity);
				} else {
					this.getInstance().setCddGraduationUniversity(otherUnversity);
					saveNewUniversity(otherUnversity);
				}
			}

			if (selectedFaculty != null && !selectedFaculty.isEmpty()) {
				if (!selectedFaculty.equalsIgnoreCase(RTSConstants.OTHER)) {
					this.getInstance().setCddGraduationFaculty(selectedFaculty);
				} else {
					this.getInstance().setCddGraduationFaculty(otherFaculty);
					saveNewFaculty(otherFaculty);
				}
			}

			this.getInstance().setCddCreatedDate(new Date());

			final String folderName = messages.get(RTSConstants.CANDIDATE_DOCS_STORAGE);
			recruitRequestDocumentSaver.createFolder(folderName);

			this.initUpdateDocument(this.getFileUploadBean());
			this.getInstance().getAttachments().clear();
			this.getInstance().setAttachments(this.getTmpAttachments());
		
			super.persist();
			this.saveChosenSkill();

			result = RTSConstants.PERSISTED;
			statusMessages.addFromResourceBundle(Severity.INFO,	"candidate.create.success");

		} catch (Exception e) {
			log.error("Error in creating new candidate." + e);
			statusMessages.addFromResourceBundle(Severity.ERROR, "error.candidate.create");
		}
		
		return result;
	}
	
	/**
	 * Update candidate.
	 *
	 * @return the string
	 */
	public String updateCandidate() {
		statusMessages.clear();
		String result = RTSConstants.FAILURE;
		
		tmpChosenSkillMap = skillPickBean.getChosenSkillMap();

		try {					

			StringBuffer contactNumbers = new StringBuffer();
			contactNumbers.append(contactNumber1);

			if (contactNumber2 != null && !contactNumber2.isEmpty()) {
				contactNumbers.append(RTSConstants.SEMI_COLON);
				contactNumbers.append(contactNumber2);
			}

			this.getInstance().setCddPhoneNo(contactNumbers.toString());

			final Date currentDate = new Date();
			final String username = credentials.getUsername();
			this.getInstance().setCddUpdatedDate(currentDate);
			this.getInstance().setCddUpdatedBy(username);

			if (selectedUniversity != null && !selectedUniversity.isEmpty()) {
				if (!selectedUniversity.equalsIgnoreCase("other")) {
					this.getInstance().setCddGraduationUniversity(selectedUniversity);
				} else {
					this.getInstance().setCddGraduationUniversity(otherUnversity);
					saveNewUniversity(otherUnversity);
				}
			}

			if (selectedFaculty != null && !selectedFaculty.isEmpty()) {
				if (!RTSConstants.OTHER.equalsIgnoreCase(selectedFaculty)) {
					this.getInstance().setCddGraduationFaculty(selectedFaculty);
				} else {
					this.getInstance().setCddGraduationFaculty(otherFaculty);
					saveNewFaculty(otherFaculty);
				}
			}

			final String folderName = messages.get(RTSConstants.CANDIDATE_DOCS_STORAGE);
			recruitRequestDocumentSaver.createFolder(folderName);

			this.initUpdateDocument(this.getFileUploadBean());

			for (CandidateHasAttachment att : this.getInstance().getAttachments()) {
				getEntityManager().remove(att);
			}

			this.getInstance().getAttachments().clear();

			final String basePath = folderName + RTSConstants.BACK_SLASH;

			for (UploadFileInfo fileInfo : this.getFileUploadBean().getRemovedFiles()) {
				recruitRequestDocumentSaver.doDeleteDocument(fileInfo.getName(), basePath);
			}

			this.getInstance().setAttachments(this.getTmpAttachments());
			
			
			//No recruit request mapped with this candidate.
			if(this.rrid == null || this.rrid <= 0){
				final RecruitRequest currentRecruitRequest = this.getInstance().getRecruitRequest();
				
				if(currentRecruitRequest != null){
					CandidateStatus candidateStatus = this.getInstance().getCddStatus();
					boolean isUpdate = false;
					if(candidateStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
						final int numberOfferConfirmed = currentRecruitRequest.getNumberOfferConfirmed();
						currentRecruitRequest.setNumberOfferConfirmed(numberOfferConfirmed < 0 ? 1 : numberOfferConfirmed+1);
						
						final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
						currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
						isUpdate = true;
					} else if(candidateStatus.equals(CandidateStatus.SELECTED)){						
						final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
						currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
						isUpdate = true;
					} else if(candidateStatus.equals(CandidateStatus.JOINED)){						
						final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
						currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
						
						final int numberJoined = currentRecruitRequest.getNumberCandidateJoined();
						currentRecruitRequest.setNumberCandidateJoined(numberJoined < 0 ? 1 : numberJoined+1);				
						isUpdate = true;
					}
					if(isUpdate){
						getEntityManager().persist(currentRecruitRequest);
					}
				}
				
			} else {
				final RecruitRequest currentRecruitRequest = this.getInstance().getRecruitRequest();
				
				if(currentRecruitRequest != null){
					final int recruitRequestId = currentRecruitRequest.getRcrRecruitRequestId();
					if(this.rrid != null && this.rrid != recruitRequestId){
						
						final RecruitRequest recruitRequest = getEntityManager().find(RecruitRequest.class, this.rrid);
						if(recruitRequest != null){
							final CandidateStatus candidateStatus = this.getInstance().getCddStatus();						
							boolean isUpdate = false;
							
							if(candidateStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
								//Update for new recruit request
								final int numberOfferConfirmed = currentRecruitRequest.getNumberOfferConfirmed();								
								currentRecruitRequest.setNumberOfferConfirmed(numberOfferConfirmed < 0 ? 1 : numberOfferConfirmed+1);
								
								final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
								currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
								
								//Update for old recruit request
								final int rrNumberOfferConfirmed = recruitRequest.getNumberOfferConfirmed();
								recruitRequest.setNumberOfferConfirmed(rrNumberOfferConfirmed-1);
								
								final int rrNumberOfRecruited = recruitRequest.getNumberRecruited();
								recruitRequest.setNumberOfferConfirmed(rrNumberOfRecruited-1);
								
								isUpdate = true;
							} else if(candidateStatus.equals(CandidateStatus.SELECTED)){						
								final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
								currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
								
								final int rrNumberOfRecruited = recruitRequest.getNumberRecruited();
								recruitRequest.setNumberOfferConfirmed(rrNumberOfRecruited-1);
								
								isUpdate = true;
							} else if(candidateStatus.equals(CandidateStatus.JOINED)){						
								final int numberOfRecruited = currentRecruitRequest.getNumberRecruited();
								currentRecruitRequest.setNumberOfferConfirmed(numberOfRecruited < 0 ? 1 : numberOfRecruited+1);
								
								final int numberJoined = currentRecruitRequest.getNumberCandidateJoined();
								currentRecruitRequest.setNumberCandidateJoined(numberJoined < 0 ? 1 : numberJoined+1);			
								
								final int rrNumberOfRecruited = recruitRequest.getNumberRecruited();
								recruitRequest.setNumberOfferConfirmed(rrNumberOfRecruited-1);
								
								final int rrNumberJoined = recruitRequest.getNumberCandidateJoined();
								recruitRequest.setNumberCandidateJoined(rrNumberJoined-1);				
								
								isUpdate = true;
							}
							if(isUpdate){
								this.getInstance().setRecruitRequest(currentRecruitRequest);
								//getEntityManager().persist(currentRecruitRequest);
								//getEntityManager().persist(recruitRequest);
								recruitRequestHome.clearInstance();
								recruitRequestHome.setInstance(recruitRequest);
								final String update1 = recruitRequestHome.update();
								System.out.println("update1: " + update1);
								
								recruitRequestHome.clearInstance();
								recruitRequestHome.setInstance(currentRecruitRequest);
								final String update2 = recruitRequestHome.update();
								
								System.out.println("update2: " + update2);
							}
						}
						
					}
				}
				
			}			
			
			super.update();
			this.updateRequiredSkill();
			this.saveChosenSkill();

			result = RTSConstants.UPDATED;

			statusMessages.addFromResourceBundle(Severity.INFO,"candidate.update.success");
		} catch (Exception e) {
			log.error("Error in updating exsiting candidate." + e);
			statusMessages.addFromResourceBundle(Severity.ERROR,"error.candidate.update");
		}
		
		return result;
	}
	


	/**
	 * Update required skill.
	 */
	private void updateRequiredSkill() {
		Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
		List<CandidateHasSkill> removeList = new ArrayList<CandidateHasSkill>(0);
		for (CandidateHasSkill cdHasSkill : this.getInstance().getCandidateHasSkills()) {
			if (chosenSkillMap.containsKey(cdHasSkill.getSkill().getSklSkillId())) {
				ChosenSkill temp = chosenSkillMap.get(cdHasSkill.getSkill().getSklSkillId());
				candidateHasSkillHome.clearInstance();
				candidateHasSkillHome.setInstance(cdHasSkill);
				candidateHasSkillHome.getInstance().setChs_Exerinece_Years(temp.getYear());
				candidateHasSkillHome.getInstance().setSkillLevel(temp.getSkillLevel());
				candidateHasSkillHome.update();
				chosenSkillMap.remove(cdHasSkill.getSkill().getSklSkillId());
			} else {
				removeList.add(cdHasSkill);
			}
		}
		removeSkill(removeList);
	}

	/**
	 * Removes the skill.
	 *
	 * @param cdHasSkills the cd has skills
	 */
	private void removeSkill(List<CandidateHasSkill> cdHasSkills) {
		for (CandidateHasSkill cdHasSkill : cdHasSkills) {
			candidateHasSkillHome.setId(cdHasSkill.getChsCandidateSkillId());
			candidateHasSkillHome.remove();
		}
	}

	/**
	 * Save chosen skill.
	 */
	private void saveChosenSkill() {
		if (skillPickBean != null) {
			Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
			if (chosenSkillMap != null) {
				for (ChosenSkill chosenSkill : chosenSkillMap.values()) {
					candidateHasSkillHome.clearInstance();
					candidateHasSkillHome.getInstance().setCandidate(this.getInstance());
					candidateHasSkillHome.getInstance().setSkill(chosenSkill.getSkill());
					candidateHasSkillHome.getInstance().setChs_Exerinece_Years(chosenSkill.getYear());
					candidateHasSkillHome.getInstance().setSkillLevel(chosenSkill.getSkillLevel());
					candidateHasSkillHome.persist();
				}
			}
		}
	}

	/**
	 * Save new university.
	 *
	 * @param universityName the university name
	 */
	public void saveNewUniversity(String universityName) {
		UniversityHome universityHome = (UniversityHome) Component.getInstance("universityHome", true);
		universityHome.getInstance().setName(universityName);
		universityHome.persist();
		FacesMessages.instance().clear();
	}

	/**
	 * Save new faculty.
	 *
	 * @param facultyName the faculty name
	 */
	private void saveNewFaculty(String facultyName) {
		FacultyHome facultyHome = (FacultyHome) Component.getInstance("facultyHome", true);
		facultyHome.getInstance().setName(facultyName);
		facultyHome.persist();
		FacesMessages.instance().clear();
	}

	/**
	 * Gets the choose able org unit list.
	 *
	 * @return the choose able org unit list
	 */
	public List<OrgUnit> getChooseAbleOrgUnitList() {
		orgUnitList.setMaxResults(null);
		return orgUnitList.getResultList();
	}
	
	private List<OrgUnit> candidateReportOrgUnits;
	
	public void listCandidateReportOrgUnits(final boolean isPM){
		final User user = (User) Contexts.getSessionContext().get("loggedInUser");
		if(user != null){
			candidateReportOrgUnits =  isPM 
			? orgUnitList.getOrgUnitByRole(user.getOrgUnit().getUnitName(), Boolean.TRUE) 
			: orgUnitList.getResultList();	
		}
					
	}

	// OrgUnit
	/**
	 * Reset recruit request.
	 *
	 * @param mode the mode
	 */
	public void resetRecruitRequest() {
		RTSUtils.resetList(recruitRequests);
		if (this.getInstance().getOrgUnit() != null){
			final String LEVEL_PATH = this.instance.getOrgUnit().getLevelPath();
			recruitRequests = recruitRequestList.getRecruitRequestByOrgUnitLevelPath(LEVEL_PATH);			
		}			
		
	}

	/**
	 * Initialize the update document.
	 *
	 * @param fileUploadBean the file upload bean
	 */
	private void initUpdateDocument(FileUploadBean fileUploadBean) {

		for (UploadFileInfo uploadFileInfo : fileUploadBean.getFiles()) {

			CandidateHasAttachment attachment = new CandidateHasAttachment();
			final Date currentDate = new Date();
			final String currentUserName = credentials.getUsername();

			if (uploadFileInfo.getCandidateAtt() != null
					&& uploadFileInfo.getCandidateAtt().getChaAttachmentName() != null
					&& !uploadFileInfo.getCandidateAtt().getChaAttachmentName().isEmpty()) {

				attachment.setChaAttachmentName(uploadFileInfo.getCandidateAtt().getChaAttachmentName());

				if (uploadFileInfo.getCandidateAtt().getChaAttachmentCreatedOn() == null) {
					attachment.setChaAttachmentCreatedOn(currentDate);
					attachment.setChaAttachmentCreatedBy(currentUserName);
				} else {
					attachment.setChaAttachmentCreatedOn(uploadFileInfo.getCandidateAtt().getChaAttachmentCreatedOn());
					attachment.setChaAttachmentCreatedBy(uploadFileInfo.getCandidateAtt().getChaAttachmentCreatedBy());
				}

				attachment.setChaAttachmentUpdatedOn(uploadFileInfo.getCandidateAtt().getChaAttachmentUpdatedOn());
				attachment.setChaAttachmentUpdatedBy(uploadFileInfo.getCandidateAtt().getChaAttachmentUpdatedBy());
				attachment.setChaAttachmentLength(uploadFileInfo.getCandidateAtt().getChaAttachmentLength());
				attachment.setChaAttachmentStatus(uploadFileInfo.getStatus());
				attachment.setCandidate(this.getInstance());

				this.getTmpAttachments().add(attachment);

			} else {
				final String basePath = messages.get(RTSConstants.CANDIDATE_DOCS_STORAGE) + RTSConstants.BACK_SLASH;

				final String storageName = recruitRequestDocumentSaver.saveDocument(uploadFileInfo.getName(),
						uploadFileInfo, basePath);

				attachment.setChaAttachmentName(storageName);
				attachment.setChaAttachmentCreatedOn(currentDate);
				attachment.setChaAttachmentCreatedBy(currentUserName);
				attachment.setChaAttachmentUpdatedOn(currentDate);
				attachment.setChaAttachmentUpdatedBy(currentUserName);
				attachment.setChaAttachmentLength(uploadFileInfo.getLength());
				attachment.setChaAttachmentStatus(uploadFileInfo.getStatus());
				attachment.setCandidate(this.getInstance());

				this.getTmpAttachments().add(attachment);

			}
		}
	}

	
	/**
	 * Inits the update.
	 */
	public void initUpdate() {
		Map<Integer, ChosenSkill> chosenSkills = new HashMap<Integer, ChosenSkill>();
		for (CandidateHasSkill cdHasSkill : this.getInstance().getCandidateHasSkills()) {
			final Skill skill = cdHasSkill.getSkill() != null ? cdHasSkill.getSkill() : null;
			final int yoe = cdHasSkill.getChs_Exerinece_Years() != null ? cdHasSkill.getChs_Exerinece_Years() : 0;
			final String skillLevel = cdHasSkill.getSkillLevel() != null ? cdHasSkill.getSkillLevel() : null;

			chosenSkills.put(skill.getSklSkillId(), new ChosenSkill(skill, yoe, skillLevel));
		}
		skillPickBean.setChosenSkillMap(chosenSkills);

		this.selectedOrgUnit = this.getInstance().getOrgUnit();
		this.selectedFaculty = this.getInstance().getCddGraduationFaculty();
		this.selectedUniversity = this.getInstance().getCddGraduationUniversity();

		RTSUtils.resetList(existCandidates_Name);
		RTSUtils.resetList(existCandidates_Email);

		selectedCandidate = null;
	}
	

	/**
	 * Fetch exist name.
	 *
	 * @param candidateName the candidate name
	 */
	public void fetchExistName() {
		RTSUtils.resetList(existCandidates_Name);

		this.selectedCandidate = null;

		if (this.getInstance() != null && this.getInstance().getCddCandidateId() != null) {
			existCandidates_Name = candidateList.getCandidateByName2(this.getInstance().getCddCandidateId(),
					this.getInstance().getCddName());
		} else {
			existCandidates_Name = candidateList.getCandidateByName(this.getInstance().getCddName());
		}
	}

	/**
	 * Fetch exist email.
	 *
	 * @param candidateEmail the candidate email
	 */
	public void fetchExistEmail() {
		RTSUtils.resetList(existCandidates_Email);
		this.selectedCandidate = null;

		if (this.getInstance() != null && this.getInstance().getCddCandidateId() != null) {
			existCandidates_Email = candidateList.getCandidateByEmail2(this.getInstance().getCddCandidateId(),
					this.getInstance().getCddEmail());
		} else {
			existCandidates_Email = candidateList.getCandidateByEmail(this.getInstance().getCddEmail());
		}

		if (RTSUtils.isNotEmpty(existCandidates_Email)) {
			selectedCandidate = existCandidates_Email.get(0);
		}
	}

	/**
	 * Checks for selected candidate.
	 *
	 * @return true, if successful
	 */
	public boolean hasSelectedCandidate() {
		return selectedCandidate != null ? true : false;
	}

	/**
	 * Clear curr selected candidate.
	 */
	public void clearCurrSelectedCandidate() {
		this.setSelectedCandidate(null);
	}

	/**
	 * Dispatch edit page.
	 *
	 * @return the string
	 */
	public String dispatchEditPage() {
		statusMessages.clear();
		String returnMsg = RTSConstants.FAILURE;
		if (this.selectedCandidate != null) {
			this.setInstance(selectedCandidate);
			returnMsg = RTSConstants.SUCCESS;
		}
		return returnMsg;
	}

	/** The from view. */
	private String fromView = null;

	/**
	 * Gets the from view.
	 *
	 * @return the from view
	 */
	public String getFromView() {
		return fromView;
	}

	/**
	 * Sets the from view.
	 *
	 * @param fromView the new from view
	 */
	public void setFromView(String fromView) {
		this.fromView = fromView;
	}

	/**
	 * Inits the candidate skill.
	 */
	public void initCandidateSkill() {
		this.getTmpCandidateHasSkills().clear();

		if (skillPickBean != null) {
			Map<Integer, ChosenSkill> chosenSkillMap = skillPickBean.getChosenSkillMap();
			CandidateHasSkill candidateHasSkill = new CandidateHasSkill();

			if (chosenSkillMap != null) {
				for (ChosenSkill chosenSkill : chosenSkillMap.values()) {
					candidateHasSkill.setCandidate(this.getInstance());
					candidateHasSkill.setSkill(chosenSkill.getSkill());
					candidateHasSkill.setChs_Exerinece_Years(chosenSkill.getYear());
					candidateHasSkill.setSkillLevel(chosenSkill.getSkillLevel());

					this.getTmpCandidateHasSkills().add(candidateHasSkill);
				}
			}
		}
	}

	/**
	 * Fetch file upload bean.
	 */
	public void fetchFileUploadBean() {
		ArrayList<UploadFileInfo> fileInfos = new ArrayList<UploadFileInfo>();

		for (CandidateHasAttachment attachment : this.getInstance().getAttachments()) {
			UploadFileInfo fileInfo = new UploadFileInfo();
			fileInfo.setName(attachment.getChaAttachmentName());
			fileInfo.setLength(attachment.getChaAttachmentLength());
			fileInfo.setStatus(attachment.getChaAttachmentStatus());
			fileInfo.setCandidateAtt(attachment);
			fileInfos.add(fileInfo);
		}

		this.getFileUploadBean().setFiles(fileInfos);
		final int availableFile = this.getFileUploadBean().getMaxAvailableFile()
				- this.getInstance().getAttachments().size();
		this.getFileUploadBean().setUploadsAvailable(availableFile);
	}

	/**
	 * Initialise recruit requets.
	 */
	public void initialiseRecruitRequets() {
		RTSUtils.resetList(recruitRequests);
		recruitRequests = recruitRequestList.getRecruitRequestsOrderByName();
	}

	/** The recruit requests. */
	private List<RecruitRequest> recruitRequests;

	/**
	 * Gets the recruit requests.
	 *
	 * @return the recruit requests
	 */
	public List<RecruitRequest> getRecruitRequests() {
		return recruitRequests;
	}

	/**
	 * Sets the recruit requests.
	 *
	 * @param recruitRequests the new recruit requests
	 */
	public void setRecruitRequests(List<RecruitRequest> recruitRequests) {
		this.recruitRequests = recruitRequests;
	}

	/**
	 * Load apply for recruit requests.
	 */
	public void loadApplyForRecruitRequets() {
		final List<OrgUnit> orgUnits = getChooseAbleOrgUnitList();
		//if (RTSUtils.isNotEmpty(orgUnits) && this.getInstance().getOrgUnit() != null) {
		if (RTSUtils.isNotEmpty(orgUnits)) {
			final OrgUnit firstOrgUnitOnList = orgUnits.get(0);
			if (firstOrgUnitOnList != null) {
				final String LEVEL_PATH = firstOrgUnitOnList.getLevelPath();
				recruitRequests = recruitRequestList.getRecruitRequestByOrgUnitLevelPath(LEVEL_PATH);
			}
		}
	}

	/** The candidates. */
	private List<Candidate> candidates = null;

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
	 * Onload candidates.
	 */
	public void onloadCandidates() {
		doSearch();
	}

	/**
	 * Search.
	 */
	@Begin(join = true)
	public void search() {
		doSearch();
	}
	
	/**
	 * Do search candidates.
	 */
	private void doSearch(){
		RTSUtils.resetList(candidates);
		if (candidateListBean.getSearchFilterBy() == FilterBy.SKILLSPEC) {
			candidates = candidateList.searchCandidatesBySkillSpec();
		} else {
			final String candidateName = this.getCurrCandidateName() != null ? this.getCurrCandidateName().trim() : null;
			final String recruitRequestName = this.getCurrRecruitRequestName() != null ? 
					this.getCurrRecruitRequestName().trim() : null;
			final String email = this.getCurrEmail() != null ? this.getCurrEmail().trim() : null;
			final String phone = this.getCurrPhone() != null ? this.getCurrPhone().trim() : null;
			final CandidateStatus status = this.getCddStatus() != null ? this.getCddStatus() : null;

			candidates = candidateList.searchCandidatesByInfoBasedCriteria(candidateName, recruitRequestName, email, phone, status);
		}
	}

	/** The candidate list bean. */
	@In(create = true)
	private CandidateListBean candidateListBean;

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#persist()
	 */
	@Override
	public String persist() {
		return super.persist();
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.EntityHome#update()
	 */
	@Override
	public String update() {
		return super.update();
	}

	/**
	 * Reset candidate instance.
	 */
	public void resetCandidateInstance() {
		if (this.getInstance() != null) {
			this.clearInstance();
		}

		if (RTSUtils.isNotEmpty(fileUploadBean.getFiles())) {
			fileUploadBean.getFiles().clear();
		}

		onloadCreateCandidate();
	}

	/**
	 * Auto complete recruit request names.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteRecruitRequestNames() {
		List<String> autoRR = new ArrayList<String>();
		if (this.getCurrRecruitRequestName() != null) {
			final String recruitRequestName = this.getCurrRecruitRequestName();
			autoRR = recruitRequestList.getRecruitRequestNamesByName(recruitRequestName);
		}
		return autoRR;
	}

	/**
	 * Auto complete candidate names.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteCandidateNames() {
		List<String> autoCandidates = new ArrayList<String>();
		if (this.getCurrCandidateName() != null) {
			final String candidateName = this.getCurrCandidateName();
			autoCandidates = candidateList.getCandidateByName_LikeQuery(candidateName);
		}
		return autoCandidates;
	}

	/**
	 * Auto complete candidate emails.
	 *
	 * @return the list
	 */
	public List<String> autoCompleteCandidateEmails() {
		List<String> autoCandidates = new ArrayList<String>();
		if (this.getCurrEmail() != null) {
			final String email = this.getCurrEmail();
			autoCandidates = candidateList.getCandidateByEmail_LikeQuery(email);
		}
		return autoCandidates;
	}

	/** The interview schedule list. */
	@In(create = true)
	private InterviewScheduleList interviewScheduleList;

	/** The Candidate interview histories. */
	List<InterviewSchedule> CandidateInterviewHistories;
	
	/** The cand history. */
	private Candidate candHistory;

	/**
	 * Gets the cand history.
	 *
	 * @return the cand history
	 */
	public Candidate getCandHistory() {
		return candHistory;
	}

	/**
	 * Sets the cand history.
	 *
	 * @param candHistory the new cand history
	 */
	public void setCandHistory(Candidate candHistory) {
		this.candHistory = candHistory;
	}

	/**
	 * Gets the candidate interview histories.
	 *
	 * @return the candidate interview histories
	 */
	public List<InterviewSchedule> getCandidateInterviewHistories() {
		return CandidateInterviewHistories;
	}

	/**
	 * Sets the candidate interview histories.
	 *
	 * @param candidateInterviewHistories the new candidate interview histories
	 */
	public void setCandidateInterviewHistories(List<InterviewSchedule> candidateInterviewHistories) {
		CandidateInterviewHistories = candidateInterviewHistories;
	}

	/**
	 * Inits the interview histories.
	 *
	 * @param candidate the candidate
	 */
	public void initInterviewHistories(Candidate candidate) {
		this.candHistory = candidate;
		RTSUtils.isNotEmpty(CandidateInterviewHistories);
		if (this.getCandHistory() != null) {
			final int candidateId = this.getCandHistory().getCddCandidateId();
			CandidateInterviewHistories = interviewScheduleList.getInterviewScheduleByCandidateId(candidateId);
		}
	}

	/**
	 * Assigncandidate.
	 *
	 * @param candidate the candidate
	 */
	public void assigncandidate(Candidate candidate) {
		if (candidate != null) {
			selectedCandidate = candidate;
		}
	}

	/**
	 * Copying candidate info.
	 *
	 * @param candFrom the cand from
	 * @param candTo the cand to
	 */
	public void copyingCandidateInfo(Candidate candFrom, Candidate candTo) {
		if (candFrom != null) {
			/* Source */
			if (candFrom.getCddSource() != null) {
				candTo.setCddSource(candFrom.getCddSource());
			}

			/* Referral */
			if (candFrom.getCddEmployeeId() != null) {
				candTo.setCddEmployeeId(candFrom.getCddEmployeeId());
			}
			if (candFrom.getCddEmployeeName() != null) {
				candTo.setCddEmployeeName(candFrom.getCddEmployeeName());
			}

			/* Refix */
			if (candFrom.getCddPrefix() != null) {
				candTo.setCddPrefix(candFrom.getCddPrefix());
			}

			/* Candidate name */
			if (candFrom.getCddName() != null) {
				candTo.setCddName(candFrom.getCddName());
			}

			/* Address */
			if (candFrom.getCddAddress() != null) {
				candTo.setCddAddress(candFrom.getCddAddress());
			}

			/* Email */
			if (candFrom.getCddEmail() != null) {
				candTo.setCddEmail(candFrom.getCddEmail());
			}

			/* Contact number */
			if (candFrom.getCddPhoneNo() != null) {
				candTo.setCddPhoneNo(candFrom.getCddPhoneNo());
			}

			/* Graduated Degree 1 */
			if (candFrom.getCddDegree1() != null) {
				candTo.setCddDegree1(candFrom.getCddDegree1());
			}

			/* Graduated University 1 */
			if (candFrom.getCddGraduationUniversity() != null) {
				candTo.setCddGraduationUniversity(candFrom.getCddGraduationUniversity());
			}

			/* Graduated Year 1 */
			if (candFrom.getCddYearOfGraduation() != null) {
				candTo.setCddYearOfGraduation(candFrom.getCddYearOfGraduation());
			}

			/* Graduated Degree 2 */
			if (candFrom.getCddDegree2() != null) {
				candTo.setCddDegree2(candFrom.getCddDegree2());
			}

			/* Graduated University 2 */
			if (candFrom.getCddGraduationUniversity2() != null) {
				candTo.setCddGraduationUniversity2(candFrom.getCddGraduationUniversity2());
			}

			/* Graduated Year 2 */
			if (candFrom.getCddYearOfGraduation2() != null) {
				candTo.setCddYearOfGraduation2(candFrom.getCddYearOfGraduation2());
			}

			/* Graduated Degree 3 */
			if (candFrom.getCddDegree3() != null) {
				candTo.setCddDegree3(candFrom.getCddDegree3());
			}

			/* Graduated University 3 */
			if (candFrom.getCddGraduationUniversity3() != null) {
				candTo.setCddGraduationUniversity3(candFrom.getCddGraduationUniversity3());
			}

			/* Graduated Year 3 */
			if (candFrom.getCddYearOfGraduation3() != null) {
				candTo.setCddYearOfGraduation3(candFrom.getCddYearOfGraduation3());
			}

			/* Year of Experience (overall) */
			if (candFrom.getCddYearOfExperience() != null) {
				candTo.setCddYearOfExperience(candFrom.getCddYearOfExperience());
			}

			/* Relevant Experience */
			if (candFrom.getCddRelevantExperience() != null) {
				candTo.setCddRelevantExperience(candFrom.getCddRelevantExperience());
			}

			/* Skill set */
			if (candFrom.getCddSkillSet() != null) {
				candTo.setCddSkillSet(candFrom.getCddSkillSet());
			}

			/* Priority */
			if (candFrom.getCddPriority() != null) {
				candTo.setCddPriority(candFrom.getCddPriority());
			}

			/* Status */
			if (candFrom.getCddStatus() != null) {
				candTo.setCddStatus(candFrom.getCddStatus());
			}

			/* Skill Descriptions */
			if (candFrom.getCddSkillDescription() != null) {
				candTo.setCddSkillDescription(candFrom.getCddSkillDescription());
			}

			/* Org. Unit */
			if (candFrom.getOrgUnit() != null) {
				candTo.setOrgUnit(candFrom.getOrgUnit());
			}

			/* Recruitment Request */
			if (candFrom.getRecruitRequest() != null) {
				candTo.setRecruitRequest(candFrom.getRecruitRequest());
			}
		}// End if candFrom not null
	}

	/**
	 * Onload create candidate.
	 */
	public void onloadCreateCandidate() {
		if (currCandidateName != null && !currCandidateName.isEmpty()) {
			getInstance().setCddName(currCandidateName);
		}
		/*if (currRecruitRequestName != null && !currRecruitRequestName.isEmpty()) {
			List<RecruitRequest> recruitRequests = recruitRequestList.getRecruitRequestsByName(currRecruitRequestName);
			if (RTSUtils.isNotEmpty(recruitRequests)) {
				getInstance().setRecruitRequest(recruitRequests.get(0));
			}
		}*/
		if (currEmail != null && !currEmail.isEmpty()) {
			getInstance().setCddEmail(currEmail);
		}
		if (currPhone != null && !currPhone.isEmpty()) {
			getInstance().setCddPhoneNo(currPhone);
		}
		
		if (selectedUniversity != null && !selectedUniversity.isEmpty()) {			
			setSelectedUniversity(null);
		}		

		if (selectedFaculty != null && !selectedFaculty.isEmpty()) {			
			setSelectedFaculty(null);
		}
		if (otherFaculty != null && !otherFaculty.isEmpty()) {			
			setOtherFaculty(null);
		}
		
	}

	/**
	 * Reset search.
	 */
	public void resetSearch() {
		currCandidateName = null;
		currRecruitRequestName = null;
		currEmail = null;
		currPhone = null;
		cddStatus = null;
	}	

	/**
	 * Sets the cdd status.
	 *
	 * @param cddStatus the new cdd status
	 */
	public void setCddStatus(CandidateStatus cddStatus) {
		this.cddStatus = cddStatus;
	}

	/**
	 * Gets the cdd status.
	 *
	 * @return the cdd status
	 */
	public CandidateStatus getCddStatus() {
		return cddStatus;
	}
	
	/**
	 * Populate user to candidate form.
	 *
	 * @param usrUserId the user id
	 * @param userCase the user case
	 */
	public void populateUser(final String usrUserId, final String userCase){	
		if("handledBy".equals(userCase)){
			this.getInstance().setHandledBy(usrUserId);
		} else if("shortListBy".equals(userCase)){
			this.getInstance().setShortListBy(usrUserId);
		}		
	}	
	

	/**
	 * Gets the graduation labels.
	 *
	 * @return the graduation labels
	 */
	public List<String> getGraduationTimes(){
		final ResourceBundle resourceBundle = SeamResourceBundle.getBundle("messages", Locale.ENGLISH);
		
		final int minYear = Integer.valueOf(resourceBundle.getString("candidate.min.age")); //18
		final int maxYear = Integer.valueOf(resourceBundle.getString("candidate.max.age")); //65		
		
		List<String> years = new ArrayList<String>();
		
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.set(Calendar.YEAR, (calendar.get(Calendar.YEAR) - maxYear + minYear));
		
		Calendar maxCalendar = GregorianCalendar.getInstance();
		final int maxGraduationYear = maxCalendar.get(Calendar.YEAR);
		
		for (int i = maxGraduationYear;; i--) {
			years.add(String.valueOf(i));
			maxCalendar.set(Calendar.YEAR, i);
			if(maxCalendar.before(calendar)){
				break;
			}	
		}		
		
		return years;
	}
	
	public boolean isRenderedFooterSpace(){
		return true;
	}	

	public void setRrid(Integer rrid) {
		this.rrid = rrid;
	}

	public Integer getRrid() {
		return rrid;
	}

	private Integer rrid;
	
	 
    private void updateRecruitRequestReport(RecruitRequest recruitRequest, RecruitRequest oldRecruitRequest, final CandidateStatus oldCandidateStatus, 
    		final CandidateStatus candidateStatus, final boolean hasRecruitRequest, 
    		final boolean orgUnitChanged, final boolean recruitRequestChanged){
    	
    	//Candidate is not applied for any recruit request
    	if(!hasRecruitRequest){
    		
    		// now it is applied for a recruit request
    		if(recruitRequest != null){
    			if(candidateStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
    				final int NUMBER_OFFER_CONFIRMED = recruitRequest.getNumberOfferConfirmed();		
    				
    				if(!oldCandidateStatus.equals(CandidateStatus.SELECTED) || !oldCandidateStatus.equals(CandidateStatus.JOINED)){
    					recruitRequest.setNumberOfferConfirmed(NUMBER_OFFER_CONFIRMED + 1);
    					final int NUMBER_RECRUITED = recruitRequest.getNumberRecruited();
    					recruitRequest.setNumberRecruited(NUMBER_RECRUITED + 1);
    				} 
    				
    			}
        		//ignore, nothing to do
        		else if(candidateStatus.equals(CandidateStatus.OFFER_REFUSED)){
    				
    				
    			} else if(candidateStatus.equals(CandidateStatus.SELECTED)){
    				final int NUMBER_RECRUITED = recruitRequest.getNumberRecruited();
    				if(!oldCandidateStatus.equals(CandidateStatus.OFFER_ACCEPTED) || !oldCandidateStatus.equals(CandidateStatus.JOINED)){
    					recruitRequest.setNumberRecruited(NUMBER_RECRUITED + 1);
    				} 
    			}	else if(candidateStatus.equals(CandidateStatus.JOINED)){
    				final int NUMBER_JOINED = recruitRequest.getNumberCandidateJoined();
    				if(!oldCandidateStatus.equals(CandidateStatus.OFFER_ACCEPTED) && !oldCandidateStatus.equals(CandidateStatus.SELECTED)){
    					final int NUMBER_OFFER_CONFIRMED = recruitRequest.getNumberOfferConfirmed();
    					recruitRequest.setNumberRecruited(NUMBER_OFFER_CONFIRMED +1 );
    				} 
    				recruitRequest.setNumberCandidateJoined(NUMBER_JOINED + 1);
    			}	
    		}
    		
    		
    	} 
    	//Candidate was applied for a recruit request
    	else {
    		//now, it is not applied for any request
    		if(recruitRequest == null){
    			if(oldCandidateStatus.getPosition() >= 13){
    				if(candidateStatus.equals(CandidateStatus.OFFER_ACCEPTED)){
        				final int NUMBER_OFFER_CONFIRMED = oldRecruitRequest.getNumberOfferConfirmed();		
        				
        				if(!oldCandidateStatus.equals(CandidateStatus.SELECTED) || !oldCandidateStatus.equals(CandidateStatus.JOINED)){
        					oldRecruitRequest.setNumberOfferConfirmed(NUMBER_OFFER_CONFIRMED + 1);
        					final int NUMBER_RECRUITED = oldRecruitRequest.getNumberRecruited();
        					recruitRequest.setNumberRecruited(NUMBER_RECRUITED + 1);
        				} 
        				
        			}
            		//ignore, nothing to do
            		else if(candidateStatus.equals(CandidateStatus.OFFER_REFUSED)){
            			final int NUMBER_RECRUITED = oldRecruitRequest.getNumberRecruited();
        				if(!oldCandidateStatus.equals(CandidateStatus.OFFER_ACCEPTED) || !oldCandidateStatus.equals(CandidateStatus.JOINED)){
        					oldRecruitRequest.setNumberRecruited(NUMBER_RECRUITED + 1);
        				} 	
        				
        			} else if(candidateStatus.equals(CandidateStatus.SELECTED)){
        				final int NUMBER_RECRUITED = oldRecruitRequest.getNumberRecruited();
        				if(!oldCandidateStatus.equals(CandidateStatus.OFFER_ACCEPTED) || !oldCandidateStatus.equals(CandidateStatus.JOINED)){
        					oldRecruitRequest.setNumberRecruited(NUMBER_RECRUITED + 1);
        				} 
        			}	else if(candidateStatus.equals(CandidateStatus.JOINED)){
        				final int NUMBER_JOINED = oldRecruitRequest.getNumberCandidateJoined();
        				if(!oldCandidateStatus.equals(CandidateStatus.OFFER_ACCEPTED) && !oldCandidateStatus.equals(CandidateStatus.SELECTED)){
        					final int NUMBER_OFFER_CONFIRMED = oldRecruitRequest.getNumberOfferConfirmed();
        					oldRecruitRequest.setNumberRecruited(NUMBER_OFFER_CONFIRMED +1 );
        				} 
        				oldRecruitRequest.setNumberCandidateJoined(NUMBER_JOINED + 1);
        			}
    				
    				
    			}
    			
    				
	    	} else {
	    		
	    	}
    	}
    	
    }

	public void setCandidateReportOrgUnits(List<OrgUnit> candidateReportOrgUnits) {
		this.candidateReportOrgUnits = candidateReportOrgUnits;
	}

	public List<OrgUnit> getCandidateReportOrgUnits() {
		return candidateReportOrgUnits;
	}
	
}	
