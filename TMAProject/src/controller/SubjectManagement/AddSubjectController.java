package controller.SubjectManagement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Subject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import controller.SubjectManagement.bean.SubjectBean;

import business.SubjectManager;


public class AddSubjectController extends SimpleFormController{

	private SubjectManager subjectManager;

	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}

	@Override
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		SubjectBean subjectBean = (SubjectBean) command;
		
		// Get subjectName, description through subject bean
		String subjectName = subjectBean.getSubjectName();
		String subjectDescription = subjectBean.getSubjectDescription();
		
		Subject subject = new Subject();
		
		subject.setSubjectName(subjectName);
		subject.setSubjectDescription(subjectDescription);
		this.subjectManager.addSubject(subject);
		
		
		return new ModelAndView(getSuccessView(), "subjectBean", subjectBean);
	
	}
}
