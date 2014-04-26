package dao.impl;

import java.util.List;

import model.DimStudents;
import model.DimSubjects;
import model.DimTimes;
import model.FactStudentGrades;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;

import dao.AbstractHibernateDaoSupport;
import dao.FactStudentGradesDao;

public class FactStudentGradesDaoImpl extends
AbstractHibernateDaoSupport<FactStudentGrades, Integer> implements FactStudentGradesDao{

	public FactStudentGradesDaoImpl() {
		super(FactStudentGrades.class);
	}

	@Override
	public List<FactStudentGrades> getFactStudentGradeByStudent(final DimStudents student) {
		return this.findByProperty(DIM_STUDENT, student);
	}

	@Override
	public List<FactStudentGrades> getFactStudentGradeBySubject(final DimSubjects subject) {
		return this.findByProperty(DIM_SUBJECT, subject);
	}

	@Override
	public List<FactStudentGrades> getFactStudentGradeByTime(final DimTimes time) {
		return this.findByProperty(DIM_TIME, time);
	}

	@Override
	public List<FactStudentGrades> getFactStudentGrade(final DimStudents student,
			final DimSubjects subject) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FactStudentGrades> getFactStudentGrade(final DimStudents student,
			final DimSubjects subject, final DimTimes time) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		final Criteria criteria = getSession().createCriteria(FactStudentGrades.class);
		criteria.setProjection(Projections.rowCount());
		return criteria.list().size();
	}

}
