package dao;

import java.util.List;

import model.DimSubjectGroups;
import model.DimSubjects;

public interface DimSubjectsDao extends Dao<DimSubjects, Integer>{
	public static final String SUBJECT_NAME = "subjectName";
	public static final String SUBJECT_ID = "subjectId";

	public List<DimSubjects> getDimSubjectsBySubjectName(String subjectName);
	public DimSubjects getDimSubjectBySubjectId(String subjectId);

	public DimSubjectGroups getDimSubjectGroupByDimSubject(DimSubjects subject);
}
