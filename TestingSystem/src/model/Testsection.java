package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the testsection database table.
 * 
 */
@Entity
@Table(name="testsection")
@NamedQuery(name="Testsection.findAll", query="SELECT t FROM Testsection t")
public class Testsection implements Serializable {
	private static final long serialVersionUID = 1L;
	private int testSecId;
	private String testSecDesc;
	private Test test;
	private List<Testsectionitem> testsectionitems;
	private List<Tquestion> tquestions;

	public Testsection() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TEST_SEC_ID", unique=true, nullable=false)
	public int getTestSecId() {
		return this.testSecId;
	}

	public void setTestSecId(int testSecId) {
		this.testSecId = testSecId;
	}


	@Lob
	@Column(name="TEST_SEC_DESC")
	public String getTestSecDesc() {
		return this.testSecDesc;
	}

	public void setTestSecDesc(String testSecDesc) {
		this.testSecDesc = testSecDesc;
	}


	//bi-directional many-to-one association to Test
	@ManyToOne
	@JoinColumn(name="TEST_ID")
	public Test getTest() {
		return this.test;
	}

	public void setTest(Test test) {
		this.test = test;
	}


	//bi-directional many-to-one association to Testsectionitem
	@OneToMany(mappedBy="testsection")
	public List<Testsectionitem> getTestsectionitems() {
		return this.testsectionitems;
	}

	public void setTestsectionitems(List<Testsectionitem> testsectionitems) {
		this.testsectionitems = testsectionitems;
	}

	public Testsectionitem addTestsectionitem(Testsectionitem testsectionitem) {
		getTestsectionitems().add(testsectionitem);
		testsectionitem.setTestsection(this);

		return testsectionitem;
	}

	public Testsectionitem removeTestsectionitem(Testsectionitem testsectionitem) {
		getTestsectionitems().remove(testsectionitem);
		testsectionitem.setTestsection(null);

		return testsectionitem;
	}


	//bi-directional many-to-one association to Tquestion
	@OneToMany(mappedBy="testsection")
	public List<Tquestion> getTquestions() {
		return this.tquestions;
	}

	public void setTquestions(List<Tquestion> tquestions) {
		this.tquestions = tquestions;
	}

	public Tquestion addTquestion(Tquestion tquestion) {
		getTquestions().add(tquestion);
		tquestion.setTestsection(this);

		return tquestion;
	}

	public Tquestion removeTquestion(Tquestion tquestion) {
		getTquestions().remove(tquestion);
		tquestion.setTestsection(null);

		return tquestion;
	}

}