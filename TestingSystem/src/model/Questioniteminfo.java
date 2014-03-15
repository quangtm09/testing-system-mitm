package model;

// default package
// Generated Mar 15, 2014 5:58:16 PM by Hibernate Tools 3.4.0.CR1

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
 * Questioniteminfo generated by hbm2java
 */
@Entity
@Table(name = "questioniteminfo")
public class Questioniteminfo implements java.io.Serializable {

	private Integer itemInfoId;
	private Questionitem questionitem;
	private String itemInfoProp;
	private String itemInfoValue;

	public Questioniteminfo() {
	}

	public Questioniteminfo(String itemInfoProp, String itemInfoValue) {
		this.itemInfoProp = itemInfoProp;
		this.itemInfoValue = itemInfoValue;
	}

	public Questioniteminfo(Questionitem questionitem, String itemInfoProp,
			String itemInfoValue) {
		this.questionitem = questionitem;
		this.itemInfoProp = itemInfoProp;
		this.itemInfoValue = itemInfoValue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ITEM_INFO_ID", unique = true, nullable = false)
	public Integer getItemInfoId() {
		return this.itemInfoId;
	}

	public void setItemInfoId(Integer itemInfoId) {
		this.itemInfoId = itemInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "QUEST_ITEM_ID")
	public Questionitem getQuestionitem() {
		return this.questionitem;
	}

	public void setQuestionitem(Questionitem questionitem) {
		this.questionitem = questionitem;
	}

	@Column(name = "ITEM_INFO_PROP", nullable = false, length = 50)
	public String getItemInfoProp() {
		return this.itemInfoProp;
	}

	public void setItemInfoProp(String itemInfoProp) {
		this.itemInfoProp = itemInfoProp;
	}

	@Column(name = "ITEM_INFO_VALUE", nullable = false, length = 200)
	public String getItemInfoValue() {
		return this.itemInfoValue;
	}

	public void setItemInfoValue(String itemInfoValue) {
		this.itemInfoValue = itemInfoValue;
	}

}
