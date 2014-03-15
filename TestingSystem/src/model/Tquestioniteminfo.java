package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestioniteminfo database table.
 * 
 */
@Entity
@Table(name="tquestioniteminfo")
@NamedQuery(name="Tquestioniteminfo.findAll", query="SELECT t FROM Tquestioniteminfo t")
public class Tquestioniteminfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestItemInfoId;
	private String tquestItemInfoProp;
	private String tquestItemInfoValue;
	private Tquestionitem tquestionitem;

	public Tquestioniteminfo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_ITEM_INFO_ID", unique=true, nullable=false)
	public int getTquestItemInfoId() {
		return this.tquestItemInfoId;
	}

	public void setTquestItemInfoId(int tquestItemInfoId) {
		this.tquestItemInfoId = tquestItemInfoId;
	}


	@Column(name="TQUEST_ITEM_INFO_PROP", nullable=false, length=50)
	public String getTquestItemInfoProp() {
		return this.tquestItemInfoProp;
	}

	public void setTquestItemInfoProp(String tquestItemInfoProp) {
		this.tquestItemInfoProp = tquestItemInfoProp;
	}


	@Column(name="TQUEST_ITEM_INFO_VALUE", nullable=false, length=200)
	public String getTquestItemInfoValue() {
		return this.tquestItemInfoValue;
	}

	public void setTquestItemInfoValue(String tquestItemInfoValue) {
		this.tquestItemInfoValue = tquestItemInfoValue;
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