package dao.impl;

import java.util.List;

import model.DimStudents;
import dao.AbstractHibernateDaoSupport;
import dao.DimStudentsDao;

public class DimStudentsDaoImpl extends
AbstractHibernateDaoSupport<DimStudents, Integer> implements DimStudentsDao{

	public DimStudentsDaoImpl() {
		super(DimStudents.class);
	}

	@Override
	public DimStudents getDimStudentByStudentId(final String studentId) {
		return this.findByProperty(STUDENT_ID, studentId).get(0);
	}

	@Override
	public List<DimStudents> getStudentsByAcademicYear(final int academicYear) {
		return this.findByProperty(ACADEMIC_YEAR, academicYear);
	}

}
