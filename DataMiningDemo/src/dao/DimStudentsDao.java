package dao;

import java.util.List;

import model.DimStudents;

public interface DimStudentsDao extends Dao<DimStudents, Integer>{
	public static final String STUDENT_ID = "studentId";
	public static final String ACADEMIC_YEAR = "academicYear";

	public DimStudents getDimStudentByStudentId(String studentId);
	public List<DimStudents> getStudentsByAcademicYear(int academicYear);

}
