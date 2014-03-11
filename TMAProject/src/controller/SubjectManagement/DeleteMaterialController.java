package controller.SubjectManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import business.MaterialManager;
import business.SubjectManager;

public class DeleteMaterialController implements Controller {
	private MaterialManager materialManager;

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
			Integer materialId = Integer.parseInt(request
					.getParameter("materialId"));
			materialManager.deleteMaterialById(materialId);
			return new ModelAndView("SubjectManagement/Home");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("SubjectManagement/Home");
		}
	}

}
