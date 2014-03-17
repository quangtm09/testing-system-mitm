package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.entity.CandidateHasSkill;
import com.bosch.rts.entity.Skill;

/**
 * The Class CandidateListBean.
 */
@Name("candidateListBean")
public class CandidateListBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 7408857515478725168L;

	/**
	 * The Enum FilterBy.
	 */
	public enum FilterBy {
		
		/** The caninfo. */
		CANINFO, 
 /** The skillspec. */
 SKILLSPEC;
	}

	/** The selected skills. */
	private List<Skill> selectedSkills = new ArrayList<Skill>();
	
	/**
	 * Gets the selected skills.
	 *
	 * @return the selected skills
	 */
	public List<Skill> getSelectedSkills() {
		return selectedSkills;
	}

	/**
	 * Sets the selected skills.
	 *
	 * @param selectedSkills the new selected skills
	 */
	public void setSelectedSkills(List<Skill> selectedSkills) {
		this.selectedSkills = selectedSkills;
	}

	/** The yoe from. */
	float yoeFrom = 0;	
	
	/** The yoe to. */
	float yoeTo = 0;
	
	/**
	 * Gets the yoe from.
	 *
	 * @return the yoe from
	 */
	public float getYoeFrom() {
		return yoeFrom;
	}

	/**
	 * Sets the yoe from.
	 *
	 * @param yoeFrom the new yoe from
	 */
	public void setYoeFrom(float yoeFrom) {
		this.yoeFrom = yoeFrom;
	}

	/**
	 * Gets the yoe to.
	 *
	 * @return the yoe to
	 */
	public float getYoeTo() {
		return yoeTo;
	}

	/**
	 * Sets the yoe to.
	 *
	 * @param yoeTo the new yoe to
	 */
	public void setYoeTo(float yoeTo) {
		this.yoeTo = yoeTo;
	}


	/**
	 * Instantiates a new candidate list bean.
	 */
	public CandidateListBean() {
	}

	/** The search filter by. */
	private FilterBy searchFilterBy = FilterBy.CANINFO;

	/**
	 * Gets the search filter by.
	 *
	 * @return the search filter by
	 */
	public FilterBy getSearchFilterBy() {
		return searchFilterBy;
	}

	/**
	 * Sets the search filter by.
	 *
	 * @param searchFilterBy the new search filter by
	 */
	public void setSearchFilterBy(FilterBy searchFilterBy) {
		this.searchFilterBy = searchFilterBy;
	}

	/**
	 * Gets the filter by values.
	 *
	 * @return the filter by values
	 */
	public FilterBy[] getFilterByValues() {
		return FilterBy.values();
	}

	/**
	 * Gets the matched skill.
	 *
	 * @param candidate the candidate
	 * @return the matched skill
	 */
	public List<Skill> getMatchedSkill(Candidate candidate) {
		List<Skill> matchedSkill = new ArrayList<Skill>();
		for (CandidateHasSkill candidateHasSkill : candidate.getCandidateHasSkills()) {
			for (Skill s : selectedSkills) {
				if (s.equals(candidateHasSkill.getSkill())) {
					matchedSkill.add(s);
				}
			}
		}
		return matchedSkill;
	}

	/**
	 * Gets the matched skill string.
	 *
	 * @param candidate the candidate
	 * @param length the length
	 * @return the matched skill string
	 */
	public String getMatchedSkillString(Candidate candidate, Integer length) {
		StringBuilder sbMatched = new StringBuilder();
		List<Skill> skills = getMatchedSkill(candidate);
		for (Skill skill : skills) {
			sbMatched.append(skill.getSklName() + ", ");
		}
		
		if(sbMatched.length() > 0){
			sbMatched.replace(sbMatched.length() - 2, sbMatched.length(), "");
		}
		
		return sbMatched.toString();
	}

	/*public void addCandidateSchedule(Candidate candidate) {
		InterviewScheduleBLO isb = (InterviewScheduleBLO) Component
				.getInstance("interviewScheduleBLO", ScopeType.CONVERSATION, true);
		isb.setSelectedCandidate(candidate);
		Contexts.getConversationContext().set("interviewScheduleBLO", isb);
	}*/
}
