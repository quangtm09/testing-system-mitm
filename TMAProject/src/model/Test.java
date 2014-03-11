package model;

import java.io.Serializable;

public class Test implements Serializable {
	private static final long serialVersionUID = -2367495419875123687L;
	private long TestID;
	private float earnPoint;
	private float maxPoint;
	private int percentage;
	private Examination examination;
	private Examinee examinee;

	public Examinee getExaminee() {
		return examinee;
	}

	public void setExaminee(Examinee examinee) {
		this.examinee = examinee;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public long getTestID() {
		return TestID;
	}

	public void setTestID(long testID) {
		TestID = testID;
	}

	public Test(long testID, float earnPoint, float maxPoint, int percentage,
			Examination examination, Examinee examinee) {
		super();
		TestID = testID;
		this.earnPoint = earnPoint;
		this.maxPoint = maxPoint;
		this.percentage = percentage;
		this.examination = examination;
		this.examinee = examinee;
	}

	public float getEarnPoint() {
		return earnPoint;
	}

	public void setEarnPoint(float earnPoint) {
		this.earnPoint = earnPoint;
	}

	public float getMaxPoint() {
		return maxPoint;
	}

	public void setMaxPoint(float maxPoint) {
		this.maxPoint = maxPoint;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

}
