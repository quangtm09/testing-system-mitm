package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the squestionsolution database table.
 * 
 */
@Entity
@Table(name="squestionsolution")
@NamedQuery(name="Squestionsolution.findAll", query="SELECT s FROM Squestionsolution s")
public class Squestionsolution implements Serializable {
	private static final long serialVersionUID = 1L;
	private int squestSolId;
	private String squestSolComment;
	private byte squestSolOrder;
	private Studentsolution studentsolution;
	private Tquestionitem tquestionitem1;
	private Tquestionitem tquestionitem2;
	private Tquestionitem tquestionitem3;

	public Squestionsolution() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SQUEST_SOL_ID", unique=true, nullable=false)
	public int getSquestSolId() {
		return this.squestSolId;
	}

	public void setSquestSolId(int squestSolId) {
		this.squestSolId = squestSolId;
	}


	@Lob
	@Column(name="SQUEST_SOL_COMMENT")
	public String getSquestSolComment() {
		return this.squestSolComment;
	}

	public void setSquestSolComment(String squestSolComment) {
		this.squestSolComment = squestSolComment;
	}


	@Column(name="SQUEST_SOL_ORDER")
	public byte getSquestSolOrder() {
		return this.squestSolOrder;
	}

	public void setSquestSolOrder(byte squestSolOrder) {
		this.squestSolOrder = squestSolOrder;
	}


	//bi-directional many-to-one association to Studentsolution
	@ManyToOne
	@JoinColumn(name="STUDENT_SOL_ID")
	public Studentsolution getStudentsolution() {
		return this.studentsolution;
	}

	public void setStudentsolution(Studentsolution studentsolution) {
		this.studentsolution = studentsolution;
	}


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_ITEM_ID")
	public Tquestionitem getTquestionitem1() {
		return this.tquestionitem1;
	}

	public void setTquestionitem1(Tquestionitem tquestionitem1) {
		this.tquestionitem1 = tquestionitem1;
	}


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_NEXT_ITEM_ID")
	public Tquestionitem getTquestionitem2() {
		return this.tquestionitem2;
	}

	public void setTquestionitem2(Tquestionitem tquestionitem2) {
		this.tquestionitem2 = tquestionitem2;
	}


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_PREVIOUS_ITEM_ID")
	public Tquestionitem getTquestionitem3() {
		return this.tquestionitem3;
	}

	public void setTquestionitem3(Tquestionitem tquestionitem3) {
		this.tquestionitem3 = tquestionitem3;
	}

}