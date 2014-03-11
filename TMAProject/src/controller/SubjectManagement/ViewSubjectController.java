package controller.SubjectManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Material;
import model.Subject;

import org.apache.commons.io.IOUtils;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.view.RedirectView;

import util.HibernateUtil;

import business.MaterialManager;
import business.SubjectManager;
import controller.SubjectManagement.bean.SubjectBean;

public class ViewSubjectController implements Controller {
	private SubjectManager subjectManager;
	private MaterialManager materialManager;

	public SubjectManager getSubjectManager() {
		return subjectManager;
	}

	public void setSubjectManager(SubjectManager subjectManager) {
		this.subjectManager = subjectManager;
	}

	public MaterialManager getMaterialManager() {
		return materialManager;
	}

	public void setMaterialManager(MaterialManager materialManager) {
		this.materialManager = materialManager;
	}

	@Override
	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// catch null exception if user try to put wrong subjectId or something
		// into URL (i.e. ...html?subjectId=83793523jhkjs)
		try {
			Integer subjectId = Integer.parseInt(request
					.getParameter("subjectId"));
			Subject subject = subjectManager.getSubjectById(subjectId);

			SubjectBean subjectBean = new SubjectBean();
			String subjectName = subject.getSubjectName();
			String subjectDescription = subject.getSubjectDescription();
			
			
			// Show data to user by using subject bean
			subjectBean.setSubjectName(subjectName);
			subjectBean.setSubjectId(subjectId);
			subjectBean.setSubjectDescription(subjectDescription);

			return new ModelAndView("SubjectManagement/ViewSubject",
					"subjectBean", subjectBean);
		} catch (Exception ex) {
			return new ModelAndView("SubjectManagement/Home");
		}

	}
}
