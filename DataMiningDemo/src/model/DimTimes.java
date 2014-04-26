package model;

// Generated Apr 26, 2014 11:46:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * DimTimes generated by hbm2java
 */
@Entity
@Table(name = "DimTimes", schema = "dbo", catalog = "RDW")
public class DimTimes implements java.io.Serializable {

	private int timeId;
	private int semester;
	private int year;
	private Set<FactStudentGrades> factStudentGradeses = new HashSet<FactStudentGrades>(
			0);

	public DimTimes() {
	}

	public DimTimes(int timeId, int semester, int year) {
		this.timeId = timeId;
		this.semester = semester;
		this.year = year;
	}

	public DimTimes(int timeId, int semester, int year,
			Set<FactStudentGrades> factStudentGradeses) {
		this.timeId = timeId;
		this.semester = semester;
		this.year = year;
		this.factStudentGradeses = factStudentGradeses;
	}

	@Id
	@Column(name = "time_id", unique = true, nullable = false)
	public int getTimeId() {
		return this.timeId;
	}

	public void setTimeId(int timeId) {
		this.timeId = timeId;
	}

	@Column(name = "semester", nullable = false)
	public int getSemester() {
		return this.semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	@Column(name = "year", nullable = false)
	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dimTimes")
	public Set<FactStudentGrades> getFactStudentGradeses() {
		return this.factStudentGradeses;
	}

	public void setFactStudentGradeses(
			Set<FactStudentGrades> factStudentGradeses) {
		this.factStudentGradeses = factStudentGradeses;
	}

}
