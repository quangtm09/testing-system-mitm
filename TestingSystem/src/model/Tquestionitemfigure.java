package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

/**
 * Tquestionitemfigure generated by hbm2java
 */
public class Tquestionitemfigure implements java.io.Serializable {

	private Integer tquestItemFigId;
	private Tquestionitem tquestionitem;
	private Tfigurelink tfigurelink;
	private String tquestItemFigDesc;

	public Tquestionitemfigure() {
	}

	public Tquestionitemfigure(Tquestionitem tquestionitem,
			Tfigurelink tfigurelink, String tquestItemFigDesc) {
		this.tquestionitem = tquestionitem;
		this.tfigurelink = tfigurelink;
		this.tquestItemFigDesc = tquestItemFigDesc;
	}

	public Integer getTquestItemFigId() {
		return this.tquestItemFigId;
	}

	public void setTquestItemFigId(Integer tquestItemFigId) {
		this.tquestItemFigId = tquestItemFigId;
	}

	public Tquestionitem getTquestionitem() {
		return this.tquestionitem;
	}

	public void setTquestionitem(Tquestionitem tquestionitem) {
		this.tquestionitem = tquestionitem;
	}

	public Tfigurelink getTfigurelink() {
		return this.tfigurelink;
	}

	public void setTfigurelink(Tfigurelink tfigurelink) {
		this.tfigurelink = tfigurelink;
	}

	public String getTquestItemFigDesc() {
		return this.tquestItemFigDesc;
	}

	public void setTquestItemFigDesc(String tquestItemFigDesc) {
		this.tquestItemFigDesc = tquestItemFigDesc;
	}

}
