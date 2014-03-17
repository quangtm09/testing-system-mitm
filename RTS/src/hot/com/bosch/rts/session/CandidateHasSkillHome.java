package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.CandidateHasSkill;

/**
 * The Class CandidateHasSkillHome.
 */
@Name("candidateHasSkillHome")
public class CandidateHasSkillHome extends EntityHome<CandidateHasSkill> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5334471997860532072L;

	/**
	 * Instantiates a new candidate has skill home.
	 */
	public CandidateHasSkillHome() {
	}

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#setInstance(java.lang.Object)
	 */
	@Override
	public void setInstance(CandidateHasSkill instance) {
		super.setInstance(instance);
	}
}
