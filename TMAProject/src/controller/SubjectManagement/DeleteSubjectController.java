package controller.SubjectManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import business.SubjectManager;

public class DeleteSubjectController implements Controller {
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

		// catch null exception if user try to put wrong subjectId or something
		// into URL (i.e. ...html?subjectId=83793523jhkjs)
		try {
			Integer subjectId = Integer.parseInt(request
					.getParameter("subjectId"));
			
			// Delete subject cause delete all materials of that subject
			subjectManager.deleteSubjectById(subjectId);
			return new ModelAndView("SubjectManagement/Home");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("SubjectManagement/Home");
		}
	}

}
