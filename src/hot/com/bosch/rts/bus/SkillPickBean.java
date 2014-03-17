package com.bosch.rts.bus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Transient;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.richfaces.model.selection.Selection;

import com.bosch.rts.entity.Skill;
import com.bosch.rts.session.SkillList;
import com.bosch.rts.utilities.RTSConstants;

/**
 * The Class SkillPickBean.
 */
@Name("skillPickBean")
@Scope(ScopeType.PAGE)
public class SkillPickBean implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = -5804908089570665538L;	

	/** The skill list. */
	@In(create = true)
	private SkillList skillList;

	/**
	 * Instantiates a new skill pick bean.
	 */
	public SkillPickBean() {
	}

	/** The chosen skill map. */
	private Map<Integer, ChosenSkill> chosenSkillMap = new HashMap<Integer, ChosenSkill>();
	
	/** The selected left skills. */
	private Skill[] selectedLeftSkills;
	
	/** The selected right skill. */
	private Selection selectedRightSkill;
	
	/** The table state. */
	private String tableState;
	
	/** The skill cache. */
	private List<Skill> skillCache = new ArrayList<Skill>();
	
	/** The is creation mode. */
	@Transient
	private Boolean isCreationMode = false;	

	/**
	 * Gets the checks if is creation mode.
	 *
	 * @return the checks if is creation mode
	 */
	public Boolean getIsCreationMode() {
		return isCreationMode;
	}

	/**
	 * Sets the checks if is creation mode.
	 *
	 * @param isCreationMode the new checks if is creation mode
	 */
	public void setIsCreationMode(Boolean isCreationMode) {
		this.isCreationMode = isCreationMode;
	}

	/**
	 * Gets the skill cache.
	 *
	 * @return the skill cache
	 */
	public List<Skill> getSkillCache() {
		return skillCache;
	}

	/**
	 * Sets the skill cache.
	 *
	 * @param skillCache the new skill cache
	 */
	public void setSkillCache(List<Skill> skillCache) {
		this.skillCache = skillCache;
	}

	/** The filter. */
	private String filter;

	/**
	 * Gets the filter.
	 *
	 * @return the filter
	 */
	public String getFilter() {
		return filter;
	}

	/**
	 * Sets the filter.
	 *
	 * @param filter the new filter
	 */
	public void setFilter(String filter) {
		this.filter = filter.toLowerCase();
	}

	/** The use two year. */
	boolean useTwoYear = true;
	
	/** The use year column. */
	boolean useYearColumn = true;

	/** The dirty. */
	boolean dirty = false;

	/**
	 * Checks if is use two year.
	 *
	 * @return true, if is use two year
	 */
	public boolean isUseTwoYear() {
		return useTwoYear;
	}

	/**
	 * Sets the use two year.
	 *
	 * @param useTwoYear the new use two year
	 */
	public void setUseTwoYear(boolean useTwoYear) {
		this.useTwoYear = useTwoYear;
	}

	/**
	 * Checks if is use year column.
	 *
	 * @return true, if is use year column
	 */
	public boolean isUseYearColumn() {
		return useYearColumn;
	}

	/**
	 * Sets the use year column.
	 *
	 * @param useYearColumn the new use year column
	 */
	public void setUseYearColumn(boolean useYearColumn) {
		this.useYearColumn = useYearColumn;
	}

	/**
	 * Gets the table state.
	 *
	 * @return the table state
	 */
	public String getTableState() {
		return tableState;
	}

	/**
	 * Sets the table state.
	 *
	 * @param tableState the new table state
	 */
	public void setTableState(String tableState) {
		this.tableState = tableState;
	}

	/**
	 * Gets the selected left skills.
	 *
	 * @return the selected left skills
	 */
	public Skill[] getSelectedLeftSkills() {
		return selectedLeftSkills;
	}

	/**
	 * Sets the selected left skills.
	 *
	 * @param selectedLeftSkills the new selected left skills
	 */
	public void setSelectedLeftSkills(Skill[] selectedLeftSkills) {
		this.selectedLeftSkills = selectedLeftSkills;
	}

	/**
	 * Gets the selected right skills.
	 *
	 * @return the selected right skills
	 */
	public Selection getSelectedRightSkills() {
		return selectedRightSkill;
	}

	/**
	 * Sets the selected right skills.
	 *
	 * @param selectedRightSkill the new selected right skills
	 */
	public void setSelectedRightSkills(Selection selectedRightSkill) {
		this.selectedRightSkill = selectedRightSkill;
	}

	/**
	 * Gets the skill list.
	 *
	 * @return the skill list
	 */
	public List<Skill> getSkillList() {
		skillList.setMaxResults(null);
		if(this.isCreationMode){
			skillCache.clear();
			skillCache =  skillList.getResultList(); 
		} else {
			if (skillCache == null || skillCache.isEmpty() || dirty) {
				dirty = false;
				skillCache = skillList.getResultList();
				if(chosenSkillMap != null && !chosenSkillMap.isEmpty()){
					for (ChosenSkill cSkill : chosenSkillMap.values()) {
						skillCache.remove(cSkill.getSkill());
					}
				}
				
				if (!(filter == null || filter.isEmpty())) {
					List<Skill> temp = new ArrayList<Skill>();
					for (Skill sk : skillCache) {
						if (sk.getSklName().toLowerCase().startsWith(filter)) {
							temp.add(sk);
						}
					}
					skillCache = temp;
				}

			}
		}
		
		return skillCache;
	}
	
	/**
	 * Do filter.
	 */
	public void doFilter() {
		dirty = true;
	}

	/**
	 * Gets the chosen skill map.
	 *
	 * @return the chosen skill map
	 */
	public Map<Integer, ChosenSkill> getChosenSkillMap() {
		return this.chosenSkillMap;
	}

	/**
	 * Sets the chosen skill map.
	 *
	 * @param chosenSkillMap the chosen skill map
	 */
	public void setChosenSkillMap(Map<Integer, ChosenSkill> chosenSkillMap) {
		this.chosenSkillMap = chosenSkillMap;
		dirty = true;
	}

	/**
	 * Move skill left to right.
	 */
	public void moveSkillLeftToRight() {
		if (selectedLeftSkills != null) {
			for (Skill skill : selectedLeftSkills) {
				this.chosenSkillMap.put(skill.getSklSkillId(), new ChosenSkill(skill));
				dirty = true;
			}
		}
	}

	/**
	 * Move skill right to lelf.
	 */
	public void moveSkillRightToLelf() {	
		if(selectedRightSkill != null && selectedRightSkill.size() > 0){			
			Iterator<Object> itor = selectedRightSkill.getKeys();
			ChosenSkill[] chosenSkillSetArray = this.chosenSkillMap.values().toArray(
					new ChosenSkill[] {});
			while (itor.hasNext()) {
				Integer i = (Integer) itor.next();
				if (this.chosenSkillMap.size() > 0 && i < this.chosenSkillMap.size()) {
					this.chosenSkillMap.remove(chosenSkillSetArray[i].skill
							.getSklSkillId());
					dirty = true;
				}
			}
		}
		
	}
	
	/**
	 * Reset skill bean.
	 */
	public void resetSkillBean(){
		this.getChosenSkillMap().clear();
		this.getSkillCache().clear();
		this.setFilter(RTSConstants.BLANK);
	}
	
}
