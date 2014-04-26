package dao;

import model.DimSubjectGroups;

public interface DimSubjectGroupsDao extends Dao<DimSubjectGroups, Long> {
	public static final String SUBJECT_GROUP_NAME = "subjectGroupName";

	public DimSubjectGroups getDimSubjectGroupBySubjectName(String subjectName);
}
