package controller.SubjectManagement;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import controller.SubjectManagement.bean.MaterialBean;

import business.MaterialManager;
import business.SubjectManager;
import model.Material;
import model.Subject;

public class MaterialController implements Controller {
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
			Integer subjectId = Integer.parseInt(request
					.getParameter("subjectId"));
			List<Material> materialList = new ArrayList<Material>();
			materialList = materialManager.getMaterialBySubjectId(subjectId);

			MaterialBean materialBean = new MaterialBean();
			materialBean.setMaterialList(materialList);
			return new ModelAndView("SubjectManagement/Material",
					"materialList", materialList);
		} catch (Exception ex) {
			return null;
		}

	}

}
