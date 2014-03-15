package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestionowner database table.
 * 
 */
@Entity
@Table(name="tquestionowner")
@NamedQuery(name="Tquestionowner.findAll", query="SELECT t FROM Tquestionowner t")
public class Tquestionowner implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestOwnerId;
	private Lecturer lecturer;
	private Tquestion tquestion;

	public Tquestionowner() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_OWNER_ID", unique=true, nullable=false)
	public int getTquestOwnerId() {
		return this.tquestOwnerId;
	}

	public void setTquestOwnerId(int tquestOwnerId) {
		this.tquestOwnerId = tquestOwnerId;
	}


	//bi-directional many-to-one association to Lecturer
	@ManyToOne
	@JoinColumn(name="LECTR_ID")
	public Lecturer getLecturer() {
		return this.lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
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