package model;// default package
// Generated Mar 12, 2014 10:45:56 PM by Hibernate Tools 3.6.0

/**
 * Tquestioninfo generated by hbm2java
 */
public class Tquestioninfo implements java.io.Serializable {

	private Integer tquestInfoId;
	private Tquestion tquestion;
	private String tquestInfoProp;
	private String tquestInfoValue;

	public Tquestioninfo() {
	}

	public Tquestioninfo(String tquestInfoProp) {
		this.tquestInfoProp = tquestInfoProp;
	}

	public Tquestioninfo(Tquestion tquestion, String tquestInfoProp,
			String tquestInfoValue) {
		this.tquestion = tquestion;
		this.tquestInfoProp = tquestInfoProp;
		this.tquestInfoValue = tquestInfoValue;
	}

	public Integer getTquestInfoId() {
		return this.tquestInfoId;
	}

	public void setTquestInfoId(Integer tquestInfoId) {
		this.tquestInfoId = tquestInfoId;
	}

	public Tquestion getTquestion() {
		return this.tquestion;
	}

	public void setTquestion(Tquestion tquestion) {
		this.tquestion = tquestion;
	}

	public String getTquestInfoProp() {
		return this.tquestInfoProp;
	}

	public void setTquestInfoProp(String tquestInfoProp) {
		this.tquestInfoProp = tquestInfoProp;
	}

	public String getTquestInfoValue() {
		return this.tquestInfoValue;
	}

	public void setTquestInfoValue(String tquestInfoValue) {
		this.tquestInfoValue = tquestInfoValue;
	}

}