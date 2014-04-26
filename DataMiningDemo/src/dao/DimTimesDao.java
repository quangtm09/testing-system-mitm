package dao;

import java.util.List;

import model.DimTimes;

public interface DimTimesDao extends Dao<DimTimes, Integer> {
	public static final String SEMESTER = "semester";
	public static final String YEAR = "year";

	public DimTimes getDimTimeBySemesterAndYear(int semester, int year);

	public List<DimTimes> getDimTimeBySemester(int semester);
	public List<DimTimes> getDimTimeByYear(int year);
}
