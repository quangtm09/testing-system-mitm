package model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tquestionsolution database table.
 * 
 */
@Entity
@Table(name="tquestionsolution")
@NamedQuery(name="Tquestionsolution.findAll", query="SELECT t FROM Tquestionsolution t")
public class Tquestionsolution implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tquestSolId;
	private byte tquestSolOrder;
	private Tquestion tquestion;
	private Tquestionitem tquestionitem1;
	private Tquestionitem tquestionitem2;
	private Tquestionitem tquestionitem3;

	public Tquestionsolution() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TQUEST_SOL_ID", unique=true, nullable=false)
	public int getTquestSolId() {
		return this.tquestSolId;
	}

	public void setTquestSolId(int tquestSolId) {
		this.tquestSolId = tquestSolId;
	}


	@Column(name="TQUEST_SOL_ORDER")
	public byte getTquestSolOrder() {
		return this.tquestSolOrder;
	}

	public void setTquestSolOrder(byte tquestSolOrder) {
		this.tquestSolOrder = tquestSolOrder;
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


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_NEXT_ITEM_ID")
	public Tquestionitem getTquestionitem1() {
		return this.tquestionitem1;
	}

	public void setTquestionitem1(Tquestionitem tquestionitem1) {
		this.tquestionitem1 = tquestionitem1;
	}


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_PREVIOUS_ITEM_ID")
	public Tquestionitem getTquestionitem2() {
		return this.tquestionitem2;
	}

	public void setTquestionitem2(Tquestionitem tquestionitem2) {
		this.tquestionitem2 = tquestionitem2;
	}


	//bi-directional many-to-one association to Tquestionitem
	@ManyToOne
	@JoinColumn(name="TQUEST_ITEM_ID")
	public Tquestionitem getTquestionitem3() {
		return this.tquestionitem3;
	}

	public void setTquestionitem3(Tquestionitem tquestionitem3) {
		this.tquestionitem3 = tquestionitem3;
	}

}