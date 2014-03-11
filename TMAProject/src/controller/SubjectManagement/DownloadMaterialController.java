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

public class DownloadMaterialController implements Controller {
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
		// into URL (i.e. ...html?materialId=83793523jhkjs)
		try {
			Integer materialId = Integer.parseInt(request
					.getParameter("materialId"));
			Material material = materialManager.getMaterialById(materialId);

			// Download file
			String filePath = material.getMaterialAbsolutePath();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ material.getMaterialTitle() + "\"");
			OutputStream outputStream = response.getOutputStream();
			response.setContentType("text/plain; charset=utf-8");
			FileInputStream fIS = new FileInputStream(filePath);
			IOUtils.copy(fIS, outputStream);
			outputStream.flush();
			outputStream.close();
			return null;

		} catch (Exception ex) {
			return new ModelAndView("SubjectManagement/Home");
		}

	}
}
