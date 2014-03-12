package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Tquestion generated by hbm2java
 */
public class Tquestion implements java.io.Serializable {

	private Integer tquestionId;
	private Testsection testsection;
	private String tquestionDesc;
	private String tquestionType;
	private Character tquestionLevel;
	private Float tquestionScore;
	private Set<Tquestionowner> tquestionowners = new HashSet<Tquestionowner>(0);
	private Set<Tquestionitem> tquestionitems = new HashSet<Tquestionitem>(0);
	private Set<Studentsolution> studentsolutions = new HashSet<Studentsolution>(
			0);
	private Set<Tquestionsolution> tquestionsolutions = new HashSet<Tquestionsolution>(
			0);
	private Set<Tquestioninfo> tquestioninfos = new HashSet<Tquestioninfo>(0);
	private Set<Tquestiontopic> tquestiontopics = new HashSet<Tquestiontopic>(0);
	private Set<Tquestionfigure> tquestionfigures = new HashSet<Tquestionfigure>(
			0);

	public Tquestion() {
	}

	public Tquestion(Testsection testsection, String tquestionDesc,
			String tquestionType, Character tquestionLevel,
			Float tquestionScore, Set<Tquestionowner> tquestionowners,
			Set<Tquestionitem> tquestionitems,
			Set<Studentsolution> studentsolutions,
			Set<Tquestionsolution> tquestionsolutions,
			Set<Tquestioninfo> tquestioninfos,
			Set<Tquestiontopic> tquestiontopics,
			Set<Tquestionfigure> tquestionfigures) {
		this.testsection = testsection;
		this.tquestionDesc = tquestionDesc;
		this.tquestionType = tquestionType;
		this.tquestionLevel = tquestionLevel;
		this.tquestionScore = tquestionScore;
		this.tquestionowners = tquestionowners;
		this.tquestionitems = tquestionitems;
		this.studentsolutions = studentsolutions;
		this.tquestionsolutions = tquestionsolutions;
		this.tquestioninfos = tquestioninfos;
		this.tquestiontopics = tquestiontopics;
		this.tquestionfigures = tquestionfigures;
	}

	public Integer getTquestionId() {
		return this.tquestionId;
	}

	public void setTquestionId(Integer tquestionId) {
		this.tquestionId = tquestionId;
	}

	public Testsection getTestsection() {
		return this.testsection;
	}

	public void setTestsection(Testsection testsection) {
		this.testsection = testsection;
	}

	public String getTquestionDesc() {
		return this.tquestionDesc;
	}

	public void setTquestionDesc(String tquestionDesc) {
		this.tquestionDesc = tquestionDesc;
	}

	public String getTquestionType() {
		return this.tquestionType;
	}

	public void setTquestionType(String tquestionType) {
		this.tquestionType = tquestionType;
	}

	public Character getTquestionLevel() {
		return this.tquestionLevel;
	}

	public void setTquestionLevel(Character tquestionLevel) {
		this.tquestionLevel = tquestionLevel;
	}

	public Float getTquestionScore() {
		return this.tquestionScore;
	}

	public void setTquestionScore(Float tquestionScore) {
		this.tquestionScore = tquestionScore;
	}

	public Set<Tquestionowner> getTquestionowners() {
		return this.tquestionowners;
	}

	public void setTquestionowners(Set<Tquestionowner> tquestionowners) {
		this.tquestionowners = tquestionowners;
	}

	public Set<Tquestionitem> getTquestionitems() {
		return this.tquestionitems;
	}

	public void setTquestionitems(Set<Tquestionitem> tquestionitems) {
		this.tquestionitems = tquestionitems;
	}

	public Set<Studentsolution> getStudentsolutions() {
		return this.studentsolutions;
	}

	public void setStudentsolutions(Set<Studentsolution> studentsolutions) {
		this.studentsolutions = studentsolutions;
	}

	public Set<Tquestionsolution> getTquestionsolutions() {
		return this.tquestionsolutions;
	}

	public void setTquestionsolutions(Set<Tquestionsolution> tquestionsolutions) {
		this.tquestionsolutions = tquestionsolutions;
	}

	public Set<Tquestioninfo> getTquestioninfos() {
		return this.tquestioninfos;
	}

	public void setTquestioninfos(Set<Tquestioninfo> tquestioninfos) {
		this.tquestioninfos = tquestioninfos;
	}

	public Set<Tquestiontopic> getTquestiontopics() {
		return this.tquestiontopics;
	}

	public void setTquestiontopics(Set<Tquestiontopic> tquestiontopics) {
		this.tquestiontopics = tquestiontopics;
	}

	public Set<Tquestionfigure> getTquestionfigures() {
		return this.tquestionfigures;
	}

	public void setTquestionfigures(Set<Tquestionfigure> tquestionfigures) {
		this.tquestionfigures = tquestionfigures;
	}

}