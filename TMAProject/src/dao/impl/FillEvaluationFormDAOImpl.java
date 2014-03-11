package dao.impl;

import java.util.List;

import model.EvaluationForm;
import dao.AbstractHibernateDaoSupport;
import dao.FillEvaluationFormDAO;

public class FillEvaluationFormDAOImpl 
	extends AbstractHibernateDaoSupport<EvaluationForm, Integer>
	implements FillEvaluationFormDAO {

	protected FillEvaluationFormDAOImpl() {
		super(EvaluationForm.class);
	}

	@Override
	public EvaluationForm getEvaluationFormById(Integer id) {
		return findById(id);
	}
	
	@Override
	public List<EvaluationForm> getAllEvaluationForms() {
		return findAll();
	}
	
	@Override
	public List<EvaluationForm> getEvaluationFormByTraineeName(String name) {
		return findByProperty("fullName", name);
	}
}
