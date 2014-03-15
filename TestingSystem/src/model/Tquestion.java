package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tquestion database table.
 * 
 */
@Entity
@Table(name="tquestion")
@NamedQuery(name="Tquestion.findAll", query="SELECT t FROM Tquestion t")
public class Tquestion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestionId;
	private String tquestionDesc;
	private String tquestionLevel;
	private float tquestionScore;
	private String tquestionType;
	private List<Studentsolution> studentsolutions;
	private Testsection testsection;
	private List<Tquestionfigure> tquestionfigures;
	private List<Tquestioninfo> tquestioninfos;
	private List<Tquestionitem> tquestionitems;
	private List<Tquestionowner> tquestionowners;
	private List<Tquestionsolution> tquestionsolutions;
	private List<Tquestiontopic> tquestiontopics;

	public Tquestion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUESTION_ID", unique=true, nullable=false)
	public int getTquestionId() {
		return this.tquestionId;
	}

	public void setTquestionId(int tquestionId) {
		this.tquestionId = tquestionId;
	}


	@Lob
	@Column(name="TQUESTION_DESC")
	public String getTquestionDesc() {
		return this.tquestionDesc;
	}

	public void setTquestionDesc(String tquestionDesc) {
		this.tquestionDesc = tquestionDesc;
	}


	@Column(name="TQUESTION_LEVEL", length=1)
	public String getTquestionLevel() {
		return this.tquestionLevel;
	}

	public void setTquestionLevel(String tquestionLevel) {
		this.tquestionLevel = tquestionLevel;
	}


	@Column(name="TQUESTION_SCORE")
	public float getTquestionScore() {
		return this.tquestionScore;
	}

	public void setTquestionScore(float tquestionScore) {
		this.tquestionScore = tquestionScore;
	}


	@Column(name="TQUESTION_TYPE", length=15)
	public String getTquestionType() {
		return this.tquestionType;
	}

	public void setTquestionType(String tquestionType) {
		this.tquestionType = tquestionType;
	}


	//bi-directional many-to-one association to Studentsolution
	@OneToMany(mappedBy="tquestion")
	public List<Studentsolution> getStudentsolutions() {
		return this.studentsolutions;
	}

	public void setStudentsolutions(List<Studentsolution> studentsolutions) {
		this.studentsolutions = studentsolutions;
	}

	public Studentsolution addStudentsolution(Studentsolution studentsolution) {
		getStudentsolutions().add(studentsolution);
		studentsolution.setTquestion(this);

		return studentsolution;
	}

	public Studentsolution removeStudentsolution(Studentsolution studentsolution) {
		getStudentsolutions().remove(studentsolution);
		studentsolution.setTquestion(null);

		return studentsolution;
	}


	//bi-directional many-to-one association to Testsection
	@ManyToOne
	@JoinColumn(name="TEST_SEC_ID")
	public Testsection getTestsection() {
		return this.testsection;
	}

	public void setTestsection(Testsection testsection) {
		this.testsection = testsection;
	}


	//bi-directional many-to-one association to Tquestionfigure
	@OneToMany(mappedBy="tquestion")
	public List<Tquestionfigure> getTquestionfigures() {
		return this.tquestionfigures;
	}

	public void setTquestionfigures(List<Tquestionfigure> tquestionfigures) {
		this.tquestionfigures = tquestionfigures;
	}

	public Tquestionfigure addTquestionfigure(Tquestionfigure tquestionfigure) {
		getTquestionfigures().add(tquestionfigure);
		tquestionfigure.setTquestion(this);

		return tquestionfigure;
	}

	public Tquestionfigure removeTquestionfigure(Tquestionfigure tquestionfigure) {
		getTquestionfigures().remove(tquestionfigure);
		tquestionfigure.setTquestion(null);

		return tquestionfigure;
	}


	//bi-directional many-to-one association to Tquestioninfo
	@OneToMany(mappedBy="tquestion")
	public List<Tquestioninfo> getTquestioninfos() {
		return this.tquestioninfos;
	}

	public void setTquestioninfos(List<Tquestioninfo> tquestioninfos) {
		this.tquestioninfos = tquestioninfos;
	}

	public Tquestioninfo addTquestioninfo(Tquestioninfo tquestioninfo) {
		getTquestioninfos().add(tquestioninfo);
		tquestioninfo.setTquestion(this);

		return tquestioninfo;
	}

	public Tquestioninfo removeTquestioninfo(Tquestioninfo tquestioninfo) {
		getTquestioninfos().remove(tquestioninfo);
		tquestioninfo.setTquestion(null);

		return tquestioninfo;
	}


	//bi-directional many-to-one association to Tquestionitem
	@OneToMany(mappedBy="tquestion")
	public List<Tquestionitem> getTquestionitems() {
		return this.tquestionitems;
	}

	public void setTquestionitems(List<Tquestionitem> tquestionitems) {
		this.tquestionitems = tquestionitems;
	}

	public Tquestionitem addTquestionitem(Tquestionitem tquestionitem) {
		getTquestionitems().add(tquestionitem);
		tquestionitem.setTquestion(this);

		return tquestionitem;
	}

	public Tquestionitem removeTquestionitem(Tquestionitem tquestionitem) {
		getTquestionitems().remove(tquestionitem);
		tquestionitem.setTquestion(null);

		return tquestionitem;
	}


	//bi-directional many-to-one association to Tquestionowner
	@OneToMany(mappedBy="tquestion")
	public List<Tquestionowner> getTquestionowners() {
		return this.tquestionowners;
	}

	public void setTquestionowners(List<Tquestionowner> tquestionowners) {
		this.tquestionowners = tquestionowners;
	}

	public Tquestionowner addTquestionowner(Tquestionowner tquestionowner) {
		getTquestionowners().add(tquestionowner);
		tquestionowner.setTquestion(this);

		return tquestionowner;
	}

	public Tquestionowner removeTquestionowner(Tquestionowner tquestionowner) {
		getTquestionowners().remove(tquestionowner);
		tquestionowner.setTquestion(null);

		return tquestionowner;
	}


	//bi-directional many-to-one association to Tquestionsolution
	@OneToMany(mappedBy="tquestion")
	public List<Tquestionsolution> getTquestionsolutions() {
		return this.tquestionsolutions;
	}

	public void setTquestionsolutions(List<Tquestionsolution> tquestionsolutions) {
		this.tquestionsolutions = tquestionsolutions;
	}

	public Tquestionsolution addTquestionsolution(Tquestionsolution tquestionsolution) {
		getTquestionsolutions().add(tquestionsolution);
		tquestionsolution.setTquestion(this);

		return tquestionsolution;
	}

	public Tquestionsolution removeTquestionsolution(Tquestionsolution tquestionsolution) {
		getTquestionsolutions().remove(tquestionsolution);
		tquestionsolution.setTquestion(null);

		return tquestionsolution;
	}


	//bi-directional many-to-one association to Tquestiontopic
	@OneToMany(mappedBy="tquestion")
	public List<Tquestiontopic> getTquestiontopics() {
		return this.tquestiontopics;
	}

	public void setTquestiontopics(List<Tquestiontopic> tquestiontopics) {
		this.tquestiontopics = tquestiontopics;
	}

	public Tquestiontopic addTquestiontopic(Tquestiontopic tquestiontopic) {
		getTquestiontopics().add(tquestiontopic);
		tquestiontopic.setTquestion(this);

		return tquestiontopic;
	}

	public Tquestiontopic removeTquestiontopic(Tquestiontopic tquestiontopic) {
		getTquestiontopics().remove(tquestiontopic);
		tquestiontopic.setTquestion(null);

		return tquestiontopic;
	}

}