package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestionfigure database table.
 * 
 */
@Entity
@Table(name="tquestionfigure")
@NamedQuery(name="Tquestionfigure.findAll", query="SELECT t FROM Tquestionfigure t")
public class Tquestionfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestFigId;
	private String tquestFigDesc;
	private Tfigurelink tfigurelink;
	private Tquestion tquestion;

	public Tquestionfigure() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_FIG_ID", unique=true, nullable=false)
	public int getTquestFigId() {
		return this.tquestFigId;
	}

	public void setTquestFigId(int tquestFigId) {
		this.tquestFigId = tquestFigId;
	}


	@Lob
	@Column(name="TQUEST_FIG_DESC")
	public String getTquestFigDesc() {
		return this.tquestFigDesc;
	}

	public void setTquestFigDesc(String tquestFigDesc) {
		this.tquestFigDesc = tquestFigDesc;
	}


	//bi-directional many-to-one association to Tfigurelink
	@ManyToOne
	@JoinColumn(name="TFIGURE_ID")
	public Tfigurelink getTfigurelink() {
		return this.tfigurelink;
	}

	public void setTfigurelink(Tfigurelink tfigurelink) {
		this.tfigurelink = tfigurelink;
	}


	//bi-directional many-to-one association to Tquestion
	@ManyToOne
	@JoinColumn(name="TQUESTION_ID")
	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

}