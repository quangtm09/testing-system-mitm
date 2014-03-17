package com.bosch.rts.session;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.bosch.rts.entity.RecruitRequestHasSkill;

/**
 * The Class RecruitRequestHasSkillHome.
 */
@Name("recruitRequestHasSkillHome")
public class RecruitRequestHasSkillHome extends
		EntityHome<RecruitRequestHasSkill> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see org.jboss.seam.framework.Home#setInstance(java.lang.Object)
	 */
	@Override
	public void setInstance(RecruitRequestHasSkill instance) {
		super.setInstance(instance);
	}
}
