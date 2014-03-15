package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the figurelink database table.
 * 
 */
@Entity
@Table(name="figurelink")
@NamedQuery(name="Figurelink.findAll", query="SELECT f FROM Figurelink f")
public class Figurelink implements Serializable {
	private static final long serialVersionUID = 1L;
	private int figureId;
	private String figureDesc;
	private String figurePath;
	private List<Questionfigure> questionfigures;
	private List<Questionitemfigure> questionitemfigures;

	public Figurelink() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="FIGURE_ID", unique=true, nullable=false)
	public int getFigureId() {
		return this.figureId;
	}

	public void setFigureId(int figureId) {
		this.figureId = figureId;
	}


	@Lob
	@Column(name="FIGURE_DESC")
	public String getFigureDesc() {
		return this.figureDesc;
	}

	public void setFigureDesc(String figureDesc) {
		this.figureDesc = figureDesc;
	}


	@Lob
	@Column(name="FIGURE_PATH", nullable=false)
	public String getFigurePath() {
		return this.figurePath;
	}

	public void setFigurePath(String figurePath) {
		this.figurePath = figurePath;
	}


	//bi-directional many-to-one association to Questionfigure
	@OneToMany(mappedBy="figurelink")
	public List<Questionfigure> getQuestionfigures() {
		return this.questionfigures;
	}

	public void setQuestionfigures(List<Questionfigure> questionfigures) {
		this.questionfigures = questionfigures;
	}

	public Questionfigure addQuestionfigure(Questionfigure questionfigure) {
		getQuestionfigures().add(questionfigure);
		questionfigure.setFigurelink(this);

		return questionfigure;
	}

	public Questionfigure removeQuestionfigure(Questionfigure questionfigure) {
		getQuestionfigures().remove(questionfigure);
		questionfigure.setFigurelink(null);

		return questionfigure;
	}


	//bi-directional many-to-one association to Questionitemfigure
	@OneToMany(mappedBy="figurelink")
	public List<Questionitemfigure> getQuestionitemfigures() {
		return this.questionitemfigures;
	}

	public void setQuestionitemfigures(List<Questionitemfigure> questionitemfigures) {
		this.questionitemfigures = questionitemfigures;
	}

	public Questionitemfigure addQuestionitemfigure(Questionitemfigure questionitemfigure) {
		getQuestionitemfigures().add(questionitemfigure);
		questionitemfigure.setFigurelink(this);

		return questionitemfigure;
	}

	public Questionitemfigure removeQuestionitemfigure(Questionitemfigure questionitemfigure) {
		getQuestionitemfigures().remove(questionitemfigure);
		questionitemfigure.setFigurelink(null);

		return questionitemfigure;
	}

}