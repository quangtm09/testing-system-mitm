package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestioninfo database table.
 * 
 */
@Entity
@Table(name="tquestioninfo")
@NamedQuery(name="Tquestioninfo.findAll", query="SELECT t FROM Tquestioninfo t")
public class Tquestioninfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestInfoId;
	private String tquestInfoProp;
	private String tquestInfoValue;
	private Tquestion tquestion;

	public Tquestioninfo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_INFO_ID", unique=true, nullable=false)
	public int getTquestInfoId() {
		return this.tquestInfoId;
	}

	public void setTquestInfoId(int tquestInfoId) {
		this.tquestInfoId = tquestInfoId;
	}


	@Column(name="TQUEST_INFO_PROP", nullable=false, length=50)
	public String getTquestInfoProp() {
		return this.tquestInfoProp;
	}

	public void setTquestInfoProp(String tquestInfoProp) {
		this.tquestInfoProp = tquestInfoProp;
	}


	@Column(name="TQUEST_INFO_VALUE", length=200)
	public String getTquestInfoValue() {
		return this.tquestInfoValue;
	}

	public void setTquestInfoValue(String tquestInfoValue) {
		this.tquestInfoValue = tquestInfoValue;
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