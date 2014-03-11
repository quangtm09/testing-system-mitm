package model;

import java.io.Serializable;
import java.util.Calendar;

public class EvaluationForm implements Serializable {

	private static final long serialVersionUID = -698958242269812748L;
	private Integer evaluationFormId;
	private Course course;
	private Calendar date;
	private int[] knowledgeAndSkillEvaluation;
	private double knowledgeAndSkillEvaluationResult;
	private int[] instructorEvaluation;
	private double instuctorEvaluationResult;
	private int[] courseTopicEvaluation;
	private double courseTopicEvalutionResult;
	private int[] othersEvalution;
	private double othersEvalutionResult;
	private int overallSatisfaction;
	private double overallSatisfactionResult;
	private double evaluationResult;
	
	public EvaluationForm() {
		super();
	}
	
	public EvaluationForm(Course course, Calendar date,
			int[] knowledgeAndSkillEvaluation,
			double knowledgeAndSkillEvaluationResult,
			int[] instructorEvaluation, double instuctorEvaluationResult,
			int[] courseTopicEvaluation, double courseTopicEvalutionResult,
			int[] othersEvalution, double othersEvalutionResult,
			int overallSatisfaction, double overallSatisfactionResult,
			double evaluationResult) {
		super();
		this.course = course;
		this.date = date;
		this.knowledgeAndSkillEvaluation = knowledgeAndSkillEvaluation;
		this.knowledgeAndSkillEvaluationResult = knowledgeAndSkillEvaluationResult;
		this.instructorEvaluation = instructorEvaluation;
		this.instuctorEvaluationResult = instuctorEvaluationResult;
		this.courseTopicEvaluation = courseTopicEvaluation;
		this.courseTopicEvalutionResult = courseTopicEvalutionResult;
		this.othersEvalution = othersEvalution;
		this.othersEvalutionResult = othersEvalutionResult;
		this.overallSatisfaction = overallSatisfaction;
		this.overallSatisfactionResult = overallSatisfactionResult;
		this.evaluationResult = evaluationResult;
	}
	
	public Integer getEvaluationFormId() {
		return evaluationFormId;
	}

	public void setEvaluationFormId(Integer evaluationFormId) {
		this.evaluationFormId = evaluationFormId;
	}

	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Calendar getDate() {
		return date;
	}
	public void setDate(Calendar date) {
		this.date = date;
	}
	public int[] getKnowledgeAndSkillEvaluation() {
		return knowledgeAndSkillEvaluation;
	}
	public void setKnowledgeAndSkillEvaluation(int[] knowledgeAndSkillEvaluation) {
		this.knowledgeAndSkillEvaluation = knowledgeAndSkillEvaluation;
	}
	public double getKnowledgeAndSkillEvaluationResult() {
		return knowledgeAndSkillEvaluationResult;
	}
	public void setKnowledgeAndSkillEvaluationResult(
			double knowledgeAndSkillEvaluationResult) {
		this.knowledgeAndSkillEvaluationResult = knowledgeAndSkillEvaluationResult;
	}
	public int[] getInstructorEvaluation() {
		return instructorEvaluation;
	}
	public void setInstructorEvaluation(int[] instructorEvaluation) {
		this.instructorEvaluation = instructorEvaluation;
	}
	public double getInstuctorEvaluationResult() {
		return instuctorEvaluationResult;
	}
	public void setInstuctorEvaluationResult(double instuctorEvaluationResult) {
		this.instuctorEvaluationResult = instuctorEvaluationResult;
	}
	public int[] getCourseTopicEvaluation() {
		return courseTopicEvaluation;
	}
	public void setCourseTopicEvaluation(int[] courseTopicEvaluation) {
		this.courseTopicEvaluation = courseTopicEvaluation;
	}
	public double getCourseTopicEvalutionResult() {
		return courseTopicEvalutionResult;
	}
	public void setCourseTopicEvalutionResult(double courseTopicEvalutionResult) {
		this.courseTopicEvalutionResult = courseTopicEvalutionResult;
	}
	public int[] getOthersEvalution() {
		return othersEvalution;
	}
	public void setOthersEvalution(int[] othersEvalution) {
		this.othersEvalution = othersEvalution;
	}
	public double getOthersEvalutionResult() {
		return othersEvalutionResult;
	}
	public void setOthersEvalutionResult(double othersEvalutionResult) {
		this.othersEvalutionResult = othersEvalutionResult;
	}
	public int getOverallSatisfaction() {
		return overallSatisfaction;
	}
	public void setOverallSatisfaction(int overallSatisfaction) {
		this.overallSatisfaction = overallSatisfaction;
	}
	public double getOverallSatisfactionResult() {
		return overallSatisfactionResult;
	}
	public void setOverallSatisfactionResult(double overallSatisfactionResult) {
		this.overallSatisfactionResult = overallSatisfactionResult;
	}
	public double getEvaluationResult() {
		return evaluationResult;
	}
	public void setEvaluationResult(double evaluationResult) {
		this.evaluationResult = evaluationResult;
	}
	
	
}
