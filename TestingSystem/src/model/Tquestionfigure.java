package model;

// default package
// Generated Mar 15, 2014 7:27:54 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Tquestionfigure generated by hbm2java
 */
@Entity
@Table(name = "tquestionfigure")
public class Tquestionfigure implements java.io.Serializable {

	private Integer tquestFigId;
	private Tquestion tquestion;
	private Tfigurelink tfigurelink;
	private String tquestFigDesc;

	public Tquestionfigure() {
	}

	public Tquestionfigure(Tquestion tquestion, Tfigurelink tfigurelink,
			String tquestFigDesc) {
		this.tquestion = tquestion;
		this.tfigurelink = tfigurelink;
		this.tquestFigDesc = tquestFigDesc;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TQUEST_FIG_ID", unique = true, nullable = false)
	public Integer getTquestFigId() {
		return this.tquestFigId;
	}

	public void setTquestFigId(Integer tquestFigId) {
		this.tquestFigId = tquestFigId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TQUESTION_ID")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TFIGURE_ID")
	public Tfigurelink getTfigurelink() {
		return this.tfigurelink;
	}

	public void setTfigurelink(Tfigurelink tfigurelink) {
		this.tfigurelink = tfigurelink;
	}

	@Column(name = "TQUEST_FIG_DESC", length = 65535)
	public String getTquestFigDesc() {
		return this.tquestFigDesc;
	}

	public void setTquestFigDesc(String tquestFigDesc) {
		this.tquestFigDesc = tquestFigDesc;
	}

}
