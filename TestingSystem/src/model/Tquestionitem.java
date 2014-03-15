package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tquestionitem database table.
 * 
 */
@Entity
@Table(name="tquestionitem")
@NamedQuery(name="Tquestionitem.findAll", query="SELECT t FROM Tquestionitem t")
public class Tquestionitem implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestItemId;
	private String tquestItemFace;
	private String tquestItemValue;
	private List<Squestionsolution> squestionsolutions1;
	private List<Squestionsolution> squestionsolutions2;
	private List<Squestionsolution> squestionsolutions3;
	private Tquestion tquestion;
	private List<Tquestionitemfigure> tquestionitemfigures;
	private List<Tquestioniteminfo> tquestioniteminfos;
	private List<Tquestionsolution> tquestionsolutions1;
	private List<Tquestionsolution> tquestionsolutions2;
	private List<Tquestionsolution> tquestionsolutions3;

	public Tquestionitem() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_ITEM_ID", unique=true, nullable=false)
	public int getTquestItemId() {
		return this.tquestItemId;
	}

	public void setTquestItemId(int tquestItemId) {
		this.tquestItemId = tquestItemId;
	}


	@Lob
	@Column(name="TQUEST_ITEM_FACE")
	public String getTquestItemFace() {
		return this.tquestItemFace;
	}

	public void setTquestItemFace(String tquestItemFace) {
		this.tquestItemFace = tquestItemFace;
	}


	@Lob
	@Column(name="TQUEST_ITEM_VALUE")
	public String getTquestItemValue() {
		return this.tquestItemValue;
	}

	public void setTquestItemValue(String tquestItemValue) {
		this.tquestItemValue = tquestItemValue;
	}


	//bi-directional many-to-one association to Squestionsolution
	@OneToMany(mappedBy="tquestionitem1")
	public List<Squestionsolution> getSquestionsolutions1() {
		return this.squestionsolutions1;
	}

	public void setSquestionsolutions1(List<Squestionsolution> squestionsolutions1) {
		this.squestionsolutions1 = squestionsolutions1;
	}

	public Squestionsolution addSquestionsolutions1(Squestionsolution squestionsolutions1) {
		getSquestionsolutions1().add(squestionsolutions1);
		squestionsolutions1.setTquestionitem1(this);

		return squestionsolutions1;
	}

	public Squestionsolution removeSquestionsolutions1(Squestionsolution squestionsolutions1) {
		getSquestionsolutions1().remove(squestionsolutions1);
		squestionsolutions1.setTquestionitem1(null);

		return squestionsolutions1;
	}


	//bi-directional many-to-one association to Squestionsolution
	@OneToMany(mappedBy="tquestionitem2")
	public List<Squestionsolution> getSquestionsolutions2() {
		return this.squestionsolutions2;
	}

	public void setSquestionsolutions2(List<Squestionsolution> squestionsolutions2) {
		this.squestionsolutions2 = squestionsolutions2;
	}

	public Squestionsolution addSquestionsolutions2(Squestionsolution squestionsolutions2) {
		getSquestionsolutions2().add(squestionsolutions2);
		squestionsolutions2.setTquestionitem2(this);

		return squestionsolutions2;
	}

	public Squestionsolution removeSquestionsolutions2(Squestionsolution squestionsolutions2) {
		getSquestionsolutions2().remove(squestionsolutions2);
		squestionsolutions2.setTquestionitem2(null);

		return squestionsolutions2;
	}


	//bi-directional many-to-one association to Squestionsolution
	@OneToMany(mappedBy="tquestionitem3")
	public List<Squestionsolution> getSquestionsolutions3() {
		return this.squestionsolutions3;
	}

	public void setSquestionsolutions3(List<Squestionsolution> squestionsolutions3) {
		this.squestionsolutions3 = squestionsolutions3;
	}

	public Squestionsolution addSquestionsolutions3(Squestionsolution squestionsolutions3) {
		getSquestionsolutions3().add(squestionsolutions3);
		squestionsolutions3.setTquestionitem3(this);

		return squestionsolutions3;
	}

	public Squestionsolution removeSquestionsolutions3(Squestionsolution squestionsolutions3) {
		getSquestionsolutions3().remove(squestionsolutions3);
		squestionsolutions3.setTquestionitem3(null);

		return squestionsolutions3;
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


	//bi-directional many-to-one association to Tquestionitemfigure
	@OneToMany(mappedBy="tquestionitem")
	public List<Tquestionitemfigure> getTquestionitemfigures() {
		return this.tquestionitemfigures;
	}

	public void setTquestionitemfigures(List<Tquestionitemfigure> tquestionitemfigures) {
		this.tquestionitemfigures = tquestionitemfigures;
	}

	public Tquestionitemfigure addTquestionitemfigure(Tquestionitemfigure tquestionitemfigure) {
		getTquestionitemfigures().add(tquestionitemfigure);
		tquestionitemfigure.setTquestionitem(this);

		return tquestionitemfigure;
	}

	public Tquestionitemfigure removeTquestionitemfigure(Tquestionitemfigure tquestionitemfigure) {
		getTquestionitemfigures().remove(tquestionitemfigure);
		tquestionitemfigure.setTquestionitem(null);

		return tquestionitemfigure;
	}


	//bi-directional many-to-one association to Tquestioniteminfo
	@OneToMany(mappedBy="tquestionitem")
	public List<Tquestioniteminfo> getTquestioniteminfos() {
		return this.tquestioniteminfos;
	}

	public void setTquestioniteminfos(List<Tquestioniteminfo> tquestioniteminfos) {
		this.tquestioniteminfos = tquestioniteminfos;
	}

	public Tquestioniteminfo addTquestioniteminfo(Tquestioniteminfo tquestioniteminfo) {
		getTquestioniteminfos().add(tquestioniteminfo);
		tquestioniteminfo.setTquestionitem(this);

		return tquestioniteminfo;
	}

	public Tquestioniteminfo removeTquestioniteminfo(Tquestioniteminfo tquestioniteminfo) {
		getTquestioniteminfos().remove(tquestioniteminfo);
		tquestioniteminfo.setTquestionitem(null);

		return tquestioniteminfo;
	}


	//bi-directional many-to-one association to Tquestionsolution
	@OneToMany(mappedBy="tquestionitem1")
	public List<Tquestionsolution> getTquestionsolutions1() {
		return this.tquestionsolutions1;
	}

	public void setTquestionsolutions1(List<Tquestionsolution> tquestionsolutions1) {
		this.tquestionsolutions1 = tquestionsolutions1;
	}

	public Tquestionsolution addTquestionsolutions1(Tquestionsolution tquestionsolutions1) {
		getTquestionsolutions1().add(tquestionsolutions1);
		tquestionsolutions1.setTquestionitem1(this);

		return tquestionsolutions1;
	}

	public Tquestionsolution removeTquestionsolutions1(Tquestionsolution tquestionsolutions1) {
		getTquestionsolutions1().remove(tquestionsolutions1);
		tquestionsolutions1.setTquestionitem1(null);

		return tquestionsolutions1;
	}


	//bi-directional many-to-one association to Tquestionsolution
	@OneToMany(mappedBy="tquestionitem2")
	public List<Tquestionsolution> getTquestionsolutions2() {
		return this.tquestionsolutions2;
	}

	public void setTquestionsolutions2(List<Tquestionsolution> tquestionsolutions2) {
		this.tquestionsolutions2 = tquestionsolutions2;
	}

	public Tquestionsolution addTquestionsolutions2(Tquestionsolution tquestionsolutions2) {
		getTquestionsolutions2().add(tquestionsolutions2);
		tquestionsolutions2.setTquestionitem2(this);

		return tquestionsolutions2;
	}

	public Tquestionsolution removeTquestionsolutions2(Tquestionsolution tquestionsolutions2) {
		getTquestionsolutions2().remove(tquestionsolutions2);
		tquestionsolutions2.setTquestionitem2(null);

		return tquestionsolutions2;
	}


	//bi-directional many-to-one association to Tquestionsolution
	@OneToMany(mappedBy="tquestionitem3")
	public List<Tquestionsolution> getTquestionsolutions3() {
		return this.tquestionsolutions3;
	}

	public void setTquestionsolutions3(List<Tquestionsolution> tquestionsolutions3) {
		this.tquestionsolutions3 = tquestionsolutions3;
	}

	public Tquestionsolution addTquestionsolutions3(Tquestionsolution tquestionsolutions3) {
		getTquestionsolutions3().add(tquestionsolutions3);
		tquestionsolutions3.setTquestionitem3(this);

		return tquestionsolutions3;
	}

	public Tquestionsolution removeTquestionsolutions3(Tquestionsolution tquestionsolutions3) {
		getTquestionsolutions3().remove(tquestionsolutions3);
		tquestionsolutions3.setTquestionitem3(null);

		return tquestionsolutions3;
	}

}