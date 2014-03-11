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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import business.MaterialManager;

public class ViewMaterialController implements Controller {
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

			String fileName = material.getMaterialTitle();

			// get tail of the file, type of file
			String getType = fileName.substring(fileName.length() - 4,
					fileName.length()).toLowerCase();

			String[] compareList = { ".doc", "docx", ".ppt", "pptx", ".txt"};

			boolean check = false;

			// check type of file, if this type cannot view, send to view file
			// error
			for (int i = 0; i < compareList.length; i++) {
				if (getType.equals(compareList[i])) {
					check = true;
					break;
				}
			}

			if (check == false) {
				String errorView = "This format file is not supported to view! Please choose another file!";
				return new ModelAndView("SubjectManagement/FileViewError",
						"errorView", errorView);
			}

			// Get material properties
			String filePath = material.getMaterialAbsolutePath();
			String typeFile = material.getMaterialType();
			String fileUrl = material.getMaterialUrl();

			/*// Prefix of String typeFile for viewing IMAGE
			if (typeFile.startsWith("image")) {
				return new ModelAndView("SubjectManagement/ViewMaterial",
						"fileUrl", filePath);
			}*/

			/*// Suffix of filePath for viewing VIDEO
			if (getType.equals(".flv") || getType.equals(".mp4")
					|| getType.equals(".mov") || getType.equals(".m4v")
					|| getType.equals(".f4v")) {
				return new ModelAndView("ViewVideo", "fileUrl", fileUrl);
			}*/

			// dump data if it able to view by PDF
			Map<String, Material> fileData = new HashMap<String, Material>();
			fileData.put(filePath, material);

			// viewing DOC, DOCX, PPT, PPTX, TXT by PDF
			if (getType.equals(".ppt") || getType.equals("pptx")
					|| getType.equals(".doc") || getType.equals("docx")
					|| getType.equals(".txt")) {

				// String "PDFDocumentViewer" is a java class, which was defined
				// in
				// src/views.properties
				// Should place the views.properties in folder classes if you
				// build
				// it
				// This class will generate a PDF file for user to view file
				return new ModelAndView("PDFDocumentViewer", "fileData",
						fileData);
			}
		} catch (Exception ex) {
			return new ModelAndView("SubjectManagement/Home");
		}
		return new ModelAndView("SubjectManagement/Home");
	}
}
