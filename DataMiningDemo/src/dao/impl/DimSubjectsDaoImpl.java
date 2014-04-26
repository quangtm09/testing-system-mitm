package dao.impl;

import java.util.List;

import model.DimSubjectGroups;
import model.DimSubjects;
import dao.AbstractHibernateDaoSupport;
import dao.DimSubjectsDao;

public class DimSubjectsDaoImpl extends
AbstractHibernateDaoSupport<DimSubjects, Integer> implements DimSubjectsDao{

	public DimSubjectsDaoImpl() {
		super(DimSubjects.class);
	}

	@Override
	public List<DimSubjects> getDimSubjectsBySubjectName(final String subjectName) {
		return this.findByProperty(SUBJECT_NAME, subjectName);
	}

	@Override
	public DimSubjectGroups getDimSubjectGroupByDimSubject(final DimSubjects subject) {
		return subject.getDimSubjectGroups();
	}

	@Override
	public DimSubjects getDimSubjectBySubjectId(final String subjectId) {
		return this.findByProperty(SUBJECT_ID, subjectId).get(0);
	}

}
