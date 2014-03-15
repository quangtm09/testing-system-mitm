package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the questioninfo database table.
 * 
 */
@Entity
@Table(name="questioninfo")
@NamedQuery(name="Questioninfo.findAll", query="SELECT q FROM Questioninfo q")
public class Questioninfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int questInfoId;
	private String questInfoProp;
	private String questInfoValue;
	private Question question;

	public Questioninfo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="QUEST_INFO_ID", unique=true, nullable=false)
	public int getQuestInfoId() {
		return this.questInfoId;
	}

	public void setQuestInfoId(int questInfoId) {
		this.questInfoId = questInfoId;
	}


	@Column(name="QUEST_INFO_PROP", nullable=false, length=50)
	public String getQuestInfoProp() {
		return this.questInfoProp;
	}

	public void setQuestInfoProp(String questInfoProp) {
		this.questInfoProp = questInfoProp;
	}


	@Column(name="QUEST_INFO_VALUE", nullable=false, length=200)
	public String getQuestInfoValue() {
		return this.questInfoValue;
	}

	public void setQuestInfoValue(String questInfoValue) {
		this.questInfoValue = questInfoValue;
	}


	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="QUESTION_ID")
	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}