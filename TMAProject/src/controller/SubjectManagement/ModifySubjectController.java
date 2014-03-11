package controller.SubjectManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Subject;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import business.SubjectManager;
import controller.SubjectManagement.bean.SubjectBean;

public class ModifySubjectController extends SimpleFormController {
	private SubjectManager subjectManager;
	private String oldSubjectName, subjectNameOfInput,
			subjectDescriptionOfInput;

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

		// subject name and description from input
		subjectNameOfInput = request.getParameter("subjectName");
		subjectDescriptionOfInput = request.getParameter("subjectDescription");
		oldSubjectName = request.getParameter("oldSubjectName");

		// Case 1: If the input name equals to the name before modifying, then
		// update.

		// Case 2: If the input name does not equal to the name before
		// modifying, and
		// does not equal any names in DB, then update. (subject1 will get null
		// pointer exception)

		// Case 3: If the input name does not equal to the name before
		// modifying, and
		// equals to one of the name in DB, then forward to error page.

		// Case 4: The user try to enter wrong subject id for URL:
		// htt://..../Modify.html?subjectId=xxxx

		try {
			// get subject from input
			Subject subject1 = subjectManager
					.getSubjectByName(subjectNameOfInput);
			String subject1Name = subject1.getSubjectName();

			// get old subject to compare
			Subject subject2 = subjectManager.getSubjectByName(oldSubjectName);
			String subject2Name = subject2.getSubjectName();

			if (subject1Name.equals(subject2Name)) {
				// Case 1
				updateSubject(subjectNameOfInput, subjectDescriptionOfInput);
			} else {
				// Case 3
				return new ModelAndView("SubjectManagement/SubjectModifyError",
						"errorMessage",
						"This subject name already existed. Please try another name!");
			}
		} catch (Exception ex) {
			// Case 2
			try {
				updateSubject(subjectNameOfInput, subjectDescriptionOfInput);
			} catch (Exception ex2) {
				// Case 4
				return new ModelAndView("SubjectManagement/SubjectModifyError",
						"errorMessage",
						"The subject you've tried to modify is not existed.");
			}

		}

		return new ModelAndView("SubjectManagement/Home");
	}

	// Show info in input fields
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		SubjectBean subjectBean = new SubjectBean();
		try {
			Integer subjectId = Integer.parseInt(request
					.getParameter("subjectId"));
			Subject subject = subjectManager.getSubjectById(subjectId);
			String oldSubjectName = subject.getSubjectName();

			subjectBean.setSubjectName(subject.getSubjectName());
			subjectBean.setSubjectDescription(subject.getSubjectDescription());
			subjectBean.setSubjectId(subject.getSubjectId());
			subjectBean.setOldSubjectName(oldSubjectName);

			return subjectBean;
		} catch (Exception ex) {
			subjectBean.setSubjectId(0);
			subjectBean.setSubjectName("None");
			subjectBean.setSubjectDescription("None");
			subjectBean.setOldSubjectName("None");
			return subjectBean;
		}

	}

	// Update subject
	private ModelAndView updateSubject(String subjectName,
			String subjectDescription) {
		// Get old subject to update
		Subject subject = subjectManager.getSubjectByName(oldSubjectName);

		// Pass new subject name and description to update
		subjectManager.modifySubjectName(subject, subjectName);
		subjectManager.modifySubjectDescription(subject, subjectDescription);

		return new ModelAndView("SubjectManagement/Home");
	}

}
