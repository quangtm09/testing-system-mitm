package dao;

import java.util.List;

import model.EvaluationForm;

public interface FillEvaluationFormDAO extends Dao<EvaluationForm, Integer>{
	
	public EvaluationForm getEvaluationFormById(Integer id);
	public List<EvaluationForm> getAllEvaluationForms();
	public List<EvaluationForm> getEvaluationFormByTraineeName(String name);
}
