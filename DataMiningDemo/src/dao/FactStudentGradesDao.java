package dao;

import java.util.List;

import model.DimStudents;
import model.DimSubjects;
import model.DimTimes;
import model.FactStudentGrades;

public interface FactStudentGradesDao extends Dao<FactStudentGrades, Integer> {
	public static final String DIM_SUBJECT = "dimSubjects";
	public static final String DIM_TIME = "dimTimes";
	public static final String DIM_STUDENT = "dimStudents";

	public List<FactStudentGrades> getFactStudentGradeByStudent(DimStudents student);
	public List<FactStudentGrades> getFactStudentGradeBySubject(DimSubjects subject);
	public List<FactStudentGrades> getFactStudentGradeByTime(DimTimes time);

	public List<FactStudentGrades> getFactStudentGrade(DimStudents student, DimSubjects subject);
	public List<FactStudentGrades> getFactStudentGrade(DimStudents student, DimSubjects subject, DimTimes time);

	public int count();
}
