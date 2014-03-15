package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tfigurelink database table.
 * 
 */
@Entity
@Table(name="tfigurelink")
@NamedQuery(name="Tfigurelink.findAll", query="SELECT t FROM Tfigurelink t")
public class Tfigurelink implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tfigureId;
	private String tfigureDesc;
	private String tfigurePath;
	private List<Tquestionfigure> tquestionfigures;
	private List<Tquestionitemfigure> tquestionitemfigures;

	public Tfigurelink() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TFIGURE_ID", unique=true, nullable=false)
	public int getTfigureId() {
		return this.tfigureId;
	}

	public void setTfigureId(int tfigureId) {
		this.tfigureId = tfigureId;
	}


	@Lob
	@Column(name="TFIGURE_DESC")
	public String getTfigureDesc() {
		return this.tfigureDesc;
	}

	public void setTfigureDesc(String tfigureDesc) {
		this.tfigureDesc = tfigureDesc;
	}


	@Lob
	@Column(name="TFIGURE_PATH")
	public String getTfigurePath() {
		return this.tfigurePath;
	}

	public void setTfigurePath(String tfigurePath) {
		this.tfigurePath = tfigurePath;
	}


	//bi-directional many-to-one association to Tquestionfigure
	@OneToMany(mappedBy="tfigurelink")
	public List<Tquestionfigure> getTquestionfigures() {
		return this.tquestionfigures;
	}

	public void setTquestionfigures(List<Tquestionfigure> tquestionfigures) {
		this.tquestionfigures = tquestionfigures;
	}

	public Tquestionfigure addTquestionfigure(Tquestionfigure tquestionfigure) {
		getTquestionfigures().add(tquestionfigure);
		tquestionfigure.setTfigurelink(this);

		return tquestionfigure;
	}

	public Tquestionfigure removeTquestionfigure(Tquestionfigure tquestionfigure) {
		getTquestionfigures().remove(tquestionfigure);
		tquestionfigure.setTfigurelink(null);

		return tquestionfigure;
	}


	//bi-directional many-to-one association to Tquestionitemfigure
	@OneToMany(mappedBy="tfigurelink")
	public List<Tquestionitemfigure> getTquestionitemfigures() {
		return this.tquestionitemfigures;
	}

	public void setTquestionitemfigures(List<Tquestionitemfigure> tquestionitemfigures) {
		this.tquestionitemfigures = tquestionitemfigures;
	}

	public Tquestionitemfigure addTquestionitemfigure(Tquestionitemfigure tquestionitemfigure) {
		getTquestionitemfigures().add(tquestionitemfigure);
		tquestionitemfigure.setTfigurelink(this);

		return tquestionitemfigure;
	}

	public Tquestionitemfigure removeTquestionitemfigure(Tquestionitemfigure tquestionitemfigure) {
		getTquestionitemfigures().remove(tquestionitemfigure);
		tquestionitemfigure.setTfigurelink(null);

		return tquestionitemfigure;
	}

}