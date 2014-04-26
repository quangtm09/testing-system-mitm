package dao.impl;

import model.DimSubjectGroups;
import dao.AbstractHibernateDaoSupport;
import dao.DimSubjectGroupsDao;

public class DimSubjectGroupsDaoImpl extends
AbstractHibernateDaoSupport<DimSubjectGroups, Long> implements DimSubjectGroupsDao{

	public DimSubjectGroupsDaoImpl() {
		super(DimSubjectGroups.class);
	}

	@Override
	public DimSubjectGroups getDimSubjectGroupBySubjectName(final String subjectName) {
		return this.findByProperty(SUBJECT_GROUP_NAME, subjectName).get(0);
	}

}
