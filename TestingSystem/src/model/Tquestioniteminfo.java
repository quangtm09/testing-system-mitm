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
 * Tquestioniteminfo generated by hbm2java
 */
@Entity
@Table(name = "tquestioniteminfo")
public class Tquestioniteminfo implements java.io.Serializable {

	private Integer tquestItemInfoId;
	private Tquestionitem tquestionitem;
	private String tquestItemInfoProp;
	private String tquestItemInfoValue;

	public Tquestioniteminfo() {
	}

	public Tquestioniteminfo(String tquestItemInfoProp,
			String tquestItemInfoValue) {
		this.tquestItemInfoProp = tquestItemInfoProp;
		this.tquestItemInfoValue = tquestItemInfoValue;
	}

	public Tquestioniteminfo(Tquestionitem tquestionitem,
			String tquestItemInfoProp, String tquestItemInfoValue) {
		this.tquestionitem = tquestionitem;
		this.tquestItemInfoProp = tquestItemInfoProp;
		this.tquestItemInfoValue = tquestItemInfoValue;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "TQUEST_ITEM_INFO_ID", unique = true, nullable = false)
	public Integer getTquestItemInfoId() {
		return this.tquestItemInfoId;
	}

	public void setTquestItemInfoId(Integer tquestItemInfoId) {
		this.tquestItemInfoId = tquestItemInfoId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "TQUEST_ITEM_ID")
	public Tquestionitem getTquestionitem() {
		return this.tquestionitem;
	}

	public void setTquestionitem(Tquestionitem tquestionitem) {
		this.tquestionitem = tquestionitem;
	}

	@Column(name = "TQUEST_ITEM_INFO_PROP", nullable = false, length = 50)
	public String getTquestItemInfoProp() {
		return this.tquestItemInfoProp;
	}

	public void setTquestItemInfoProp(String tquestItemInfoProp) {
		this.tquestItemInfoProp = tquestItemInfoProp;
	}

	@Column(name = "TQUEST_ITEM_INFO_VALUE", nullable = false, length = 200)
	public String getTquestItemInfoValue() {
		return this.tquestItemInfoValue;
	}

	public void setTquestItemInfoValue(String tquestItemInfoValue) {
		this.tquestItemInfoValue = tquestItemInfoValue;
	}

}
