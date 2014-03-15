package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the subjectassignment database table.
 * 
 */
@Entity
@Table(name="subjectassignment")
@NamedQuery(name="Subjectassignment.findAll", query="SELECT s FROM Subjectassignment s")
public class Subjectassignment implements Serializable {
	private static final long serialVersionUID = 1L;
	private int assignId;
	private Date assignEnddate;
	private Date assignStartdate;
	private Lecturer lecturer;
	private Subject subject;

	public Subjectassignment() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ASSIGN_ID", unique=true, nullable=false)
	public int getAssignId() {
		return this.assignId;
	}

	public void setAssignId(int assignId) {
		this.assignId = assignId;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGN_ENDDATE")
	public Date getAssignEnddate() {
		return this.assignEnddate;
	}

	public void setAssignEnddate(Date assignEnddate) {
		this.assignEnddate = assignEnddate;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="ASSIGN_STARTDATE")
	public Date getAssignStartdate() {
		return this.assignStartdate;
	}

	public void setAssignStartdate(Date assignStartdate) {
		this.assignStartdate = assignStartdate;
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


	//bi-directional many-to-one association to Subject
	@ManyToOne
	@JoinColumn(name="SUBJECT_ID")
	public Subject getSubject() {
		return this.subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

}