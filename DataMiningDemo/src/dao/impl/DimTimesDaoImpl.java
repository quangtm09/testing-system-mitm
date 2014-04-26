package dao.impl;

import java.util.List;

import model.DimTimes;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import dao.AbstractHibernateDaoSupport;
import dao.DimTimesDao;

public class DimTimesDaoImpl extends
AbstractHibernateDaoSupport<DimTimes, Integer> implements DimTimesDao{

	public DimTimesDaoImpl() {
		super(DimTimes.class);
	}

	@Override
	public DimTimes getDimTimeBySemesterAndYear(final int semester, final int year) {
		final Criterion semesterCrit = Restrictions.eq(SEMESTER, semester);
		final Criterion yearCrit = Restrictions.eq(YEAR, year);

		final Criterion searchTimeCriterion = Restrictions.and(semesterCrit, yearCrit);

		final List<DimTimes> result = this.findByCriteria(searchTimeCriterion);

		if(result.size() == 0){
			return null;
		}

		return result.get(0);
	}

	@Override
	public List<DimTimes> getDimTimeBySemester(final int semester) {
		return this.findByProperty(SEMESTER, semester);
	}

	@Override
	public List<DimTimes> getDimTimeByYear(final int year) {
		return this.findByProperty(YEAR, year);
	}

}
