package controller.SubjectManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Subject;


import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;


import controller.SubjectManagement.bean.SubjectBean;

import business.SubjectManager;

public class SubjectManagementController implements Controller {
	private SubjectManager subjectManager;

	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// Get all subjects to show
		List<SubjectBean> subjectBeanList = new ArrayList<SubjectBean>();
		List<Subject> subjectList = new ArrayList<Subject>();
		subjectList = subjectManager.listAllSubject();
		
		for(int i = 0; i < subjectList.size(); i++){
			SubjectBean subjectBean = new SubjectBean();
			Subject subject = new Subject();
			subject = subjectList.get(i);
			
			String subjectName = subject.getSubjectName();
			Integer subjectId = subject.getSubjectId();
			
			subjectBean.setSubjectName(subjectName);
			subjectBean.setSubjectId(subjectId);
			
			subjectBeanList.add(subjectBean);
		}
		
		return new ModelAndView("SubjectManagement/SubjectManagement", "subjectBeanList", subjectBeanList);
	}
}
