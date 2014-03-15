package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questionitemfigure database table.
 * 
 */
@Entity
@Table(name="questionitemfigure")
@NamedQuery(name="Questionitemfigure.findAll", query="SELECT q FROM Questionitemfigure q")
public class Questionitemfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	private int itemFigId;
	private String itemFigDesc;
	private Figurelink figurelink;
	private Questionitem questionitem;

	public Questionitemfigure() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ITEM_FIG_ID", unique=true, nullable=false)
	public int getItemFigId() {
		return this.itemFigId;
	}

	public void setItemFigId(int itemFigId) {
		this.itemFigId = itemFigId;
	}


	@Lob
	@Column(name="ITEM_FIG_DESC")
	public String getItemFigDesc() {
		return this.itemFigDesc;
	}

	public void setItemFigDesc(String itemFigDesc) {
		this.itemFigDesc = itemFigDesc;
	}


	//bi-directional many-to-one association to Figurelink
	@ManyToOne
	@JoinColumn(name="FIGURE_ID")
	public Figurelink getFigurelink() {
		return this.figurelink;
	}

	public void setFigurelink(Figurelink figurelink) {
		this.figurelink = figurelink;
	}


	//bi-directional many-to-one association to Questionitem
	@ManyToOne
	@JoinColumn(name="QUEST_ITEM_ID")
	public Questionitem getQuestionitem() {
		return this.questionitem;
	}

	public void setQuestionitem(Questionitem questionitem) {
		this.questionitem = questionitem;
	}

}