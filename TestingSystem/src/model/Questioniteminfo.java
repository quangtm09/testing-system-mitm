package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questioniteminfo database table.
 * 
 */
@Entity
@Table(name="questioniteminfo")
@NamedQuery(name="Questioniteminfo.findAll", query="SELECT q FROM Questioniteminfo q")
public class Questioniteminfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int itemInfoId;
	private String itemInfoProp;
	private String itemInfoValue;
	private Questionitem questionitem;

	public Questioniteminfo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ITEM_INFO_ID", unique=true, nullable=false)
	public int getItemInfoId() {
		return this.itemInfoId;
	}

	public void setItemInfoId(int itemInfoId) {
		this.itemInfoId = itemInfoId;
	}


	@Column(name="ITEM_INFO_PROP", nullable=false, length=50)
	public String getItemInfoProp() {
		return this.itemInfoProp;
	}

	public void setItemInfoProp(String itemInfoProp) {
		this.itemInfoProp = itemInfoProp;
	}


	@Column(name="ITEM_INFO_VALUE", nullable=false, length=200)
	public String getItemInfoValue() {
		return this.itemInfoValue;
	}

	public void setItemInfoValue(String itemInfoValue) {
		this.itemInfoValue = itemInfoValue;
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