package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

import java.util.HashSet;
import java.util.Set;

/**
 * Tfigurelink generated by hbm2java
 */
public class Tfigurelink implements java.io.Serializable {

	private Integer tfigureId;
	private String tfigureDesc;
	private String tfigurePath;
	private Set<Tquestionitemfigure> tquestionitemfigures = new HashSet<Tquestionitemfigure>(
			0);
	private Set<Tquestionfigure> tquestionfigures = new HashSet<Tquestionfigure>(
			0);

	public Tfigurelink() {
	}

	public Tfigurelink(String tfigureDesc, String tfigurePath,
			Set<Tquestionitemfigure> tquestionitemfigures,
			Set<Tquestionfigure> tquestionfigures) {
		this.tfigureDesc = tfigureDesc;
		this.tfigurePath = tfigurePath;
		this.tquestionitemfigures = tquestionitemfigures;
		this.tquestionfigures = tquestionfigures;
	}

	public Integer getTfigureId() {
		return this.tfigureId;
	}

	public void setTfigureId(Integer tfigureId) {
		this.tfigureId = tfigureId;
	}

	public String getTfigureDesc() {
		return this.tfigureDesc;
	}

	public void setTfigureDesc(String tfigureDesc) {
		this.tfigureDesc = tfigureDesc;
	}

	public String getTfigurePath() {
		return this.tfigurePath;
	}

	public void setTfigurePath(String tfigurePath) {
		this.tfigurePath = tfigurePath;
	}

	public Set<Tquestionitemfigure> getTquestionitemfigures() {
		return this.tquestionitemfigures;
	}

	public void setTquestionitemfigures(
			Set<Tquestionitemfigure> tquestionitemfigures) {
		this.tquestionitemfigures = tquestionitemfigures;
	}

	public Set<Tquestionfigure> getTquestionfigures() {
		return this.tquestionfigures;
	}

	public void setTquestionfigures(Set<Tquestionfigure> tquestionfigures) {
		this.tquestionfigures = tquestionfigures;
	}

}
