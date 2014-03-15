package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestionitemfigure database table.
 * 
 */
@Entity
@Table(name="tquestionitemfigure")
@NamedQuery(name="Tquestionitemfigure.findAll", query="SELECT t FROM Tquestionitemfigure t")
public class Tquestionitemfigure implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestItemFigId;
	private String tquestItemFigDesc;
	private Tfigurelink tfigurelink;
	private Tquestionitem tquestionitem;

	public Tquestionitemfigure() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_ITEM_FIG_ID", unique=true, nullable=false)
	public int getTquestItemFigId() {
		return this.tquestItemFigId;
	}

	public void setTquestItemFigId(int tquestItemFigId) {
		this.tquestItemFigId = tquestItemFigId;
	}


	@Lob
	@Column(name="TQUEST_ITEM_FIG_DESC")
	public String getTquestItemFigDesc() {
		return this.tquestItemFigDesc;
	}

	public void setTquestItemFigDesc(String tquestItemFigDesc) {
		this.tquestItemFigDesc = tquestItemFigDesc;
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


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_ITEM_ID")
	public Tquestionitem getTquestionitem() {
		return this.tquestionitem;
	}

	public void setTquestionitem(Tquestionitem tquestionitem) {
		this.tquestionitem = tquestionitem;
	}

}