package model;

import java.io.Serializable;
import java.util.Calendar;

public class Course implements Serializable{
	private static final long serialVersionUID = -1391840477544796763L;

	private int courseId;
	private String courseName;
	private Calendar createTime;
	private Calendar endedTime;
	private String position;
	private String description;

	public Course(String courseName, Calendar createTime, Calendar endedTime,
			String position, String description) {
		super();
		this.courseName = courseName;
		this.createTime = createTime;
		this.endedTime = endedTime;
		this.position = position;
		this.description = description;
	}

	public Course() {
		super();
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Calendar getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Calendar createTime) {
		this.createTime = createTime;
	}

	public Calendar getEndedTime() {
		return endedTime;
	}

	public void setEndedTime(Calendar endedTime) {
		this.endedTime = endedTime;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
