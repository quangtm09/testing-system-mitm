package controller.SubjectManagement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Material;
import model.Subject;

import org.springframework.validation.BindException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;

import business.MaterialManager;
import business.SubjectManager;

import controller.SubjectManagement.bean.MaterialBean;

public class UploadMaterialController extends SimpleFormController {
	private String materialTitle;
	private String materialSize;
	private String materialType;
	private String subjectName;
	private MultipartFile multipartFile;
	private MaterialManager materialManager;
	private SubjectManager subjectManager;

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
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		MaterialBean materialBean = (MaterialBean) command;
		String checkSubjectName = request.getParameter("subjectName");

		// URL: file:///C:/Upload/SubjectName/file_upload
		// Absolute path: C:\Upload\SubjectName\file_upload
		// getCanonicalPath: convert URL to absolute path (without "file:///")

		// Create Upload folder
		boolean existsUploadFolder = (new File("../Upload")).exists();
		if (!existsUploadFolder) {
			(new File("../Upload")).mkdir();
		}

		// Check if the subject exists or not
		Subject checkSubject = subjectManager
				.getSubjectByName(checkSubjectName);

		if (checkSubject == null) {
			return new ModelAndView("SubjectManagement/FileUploadError");
		} else {
			// If not, then
			// Check the uploaded file exist or not
			// Name of file cannot contain \/ ? | <>*

			// Get canonical path of file, then check the path if the file exist
			// or not (Upload folder path + subject name folder path + file
			// name)
			boolean checkExistsFile = (new File(new File("../Upload/"
					+ replaceSymbols(checkSubjectName)
					+ "/"
					+ replaceSymbols(materialBean.getMaterialFile()
							.getOriginalFilename())).getCanonicalPath()))
					.exists();

			if (checkExistsFile) {
				// If this file already existed, send to error page
				return new ModelAndView("SubjectManagement/FileUploadError");
			} else {

				// replace all symbols of subject name to check/create subject
				// folder
				subjectName = replaceSymbols(request
						.getParameter("subjectName"));

				// get Uploaded file
				multipartFile = materialBean.getMaterialFile();

				// get absolute path by converting "URL" to canonical path
				String absolutePath = new java.io.File("../Upload/"
						+ subjectName).getCanonicalPath();

				// Check if the subject folder exists or not
				boolean existsSubjectFolder = (new File(absolutePath)).exists();

				// If yes, upload file to that folder
				if (existsSubjectFolder) {

					// If the uploaded file is not null, upload file
					// Actually, we already check the file at
					// UploadValidator.java
					if (multipartFile != null) {
						uploadFile();
					}
				} else {
					// If no, create new Upload/SubjectName folder, then upload
					File newDirectory = new File(absolutePath);

					// Create folder
					newDirectory.mkdir();

					// If the uploaded file is not null, upload file
					// Actually, we already check the file at
					// UploadValidator.java
					if (multipartFile != null) {
						uploadFile();
					}
				}

				// Send message and the path of folder of uploaded file to
				// success page
				String uploadMessage = materialTitle
						+ " has been uploaded successfully to the server (folder name: "
						+ absolutePath + ")";
				return new ModelAndView("SubjectManagement/FileUploadSuccess",
						"uploadMessage", uploadMessage);
			}
		}
	}

	private ModelAndView uploadFile() throws IllegalStateException,
			IOException, URISyntaxException {

		// URL: file:///C:/Upload/SubjectName/file_upload
		// Absolute path: C:\Upload\SubjectName\file_upload
		// getCanonicalPath: convert URL to absolute path (without "file:///")

		// Name of file cannot contain \/ ? | <>*
		materialTitle = replaceSymbols(multipartFile.getOriginalFilename());
		materialSize = calculateSize(multipartFile.getSize());
		materialType = multipartFile.getContentType();

		// get canonical path of folder
		String path = new File("../Upload/" + subjectName).getCanonicalPath();

		// Convert C:\Upload\SubjectName\file_upload to
		// C:/Upload/SubjectName/file_upload (paste the file path to
		// File(filePath)
		// to transfer file upload to this destination)
		URL url = new URL("file:///" + path);
		URI uri = url.toURI();

		// Cutting "file:///"
		String path2 = (uri.toString()).substring(6, uri.toString().length())
				+ "/";

		// Convert for UTF characters, but we don't need (for references)
		byte[] utf8 = materialTitle.getBytes("UTF-8");
		materialTitle = new String(utf8, "UTF-8");

		// Get the URL for file to upload
		// Example: C:/Upload/SubjectName/file_upload.exe
		String filePath = path2 + materialTitle;

		// Transfer uploaded file to filePath
		// (C:/Upload/SubjectName/file_upload.exe)
		File dest = new File(filePath);
		multipartFile.transferTo(dest);

		Material material = new Material();

		// Encode for http://localhost, but we don't need (for references)
		String URLFilePath = URLEncoder.encode("file:///" + filePath, "UTF-8");

		Subject subject = subjectManager.getSubjectByName(subjectName);

		// Save file properties to DB
		material.setMaterialTitle(materialTitle);
		material.setMaterialSize(materialSize);
		material.setMaterialType(materialType);
		material.setMaterialUrl("file:///" + filePath);
		material.setMaterialAbsolutePath(filePath);
		material.setDateModification(new GregorianCalendar().getTime()
				.toString());
		material.setSubject(subject);
		this.materialManager.addMaterial(material);

		return null;
	}

	// Add subject name to input field
	@Override
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		MaterialBean materialBean = new MaterialBean();
		try {
			Integer subjectId = Integer.parseInt(request
					.getParameter("subjectId"));
			Subject subject = subjectManager.getSubjectById(subjectId);
			materialBean.setSubjectName(subject.getSubjectName());
		} catch (Exception e) {
			materialBean.setSubjectName("Please choose from below!");
		}
		return materialBean;
	}

	// Show list subjects for easy input subject
	@Override
	protected Map referenceData(HttpServletRequest request) throws Exception {
		Map referenceData = new HashMap();

		Map<String, String> subjectList = new LinkedHashMap<String, String>();
		Iterator<Subject> iter = this.subjectManager.listAllSubject()
				.iterator();
		while (iter.hasNext()) {
			Subject subject = iter.next();
			subjectList.put(subject.getSubjectId() + "",
					subject.getSubjectName());
		}

		referenceData.put("subjectList", subjectList);
		return referenceData;
	}

	// Replace all symbols: \/ ? | <>*
	private String replaceSymbols(String target) {
		target = target.trim().replaceAll(" ", "_").replaceAll("\\/", "")
				.replaceAll("\\\\", "").replaceAll("\\<", "")
				.replaceAll("\\>", "").replaceAll("\\?", "")
				.replaceAll("\\|", "").replaceAll(":", "")
				.replaceAll("\\*", "");
		return target;
	}

	private String calculateSize(long fileSize) {
		String getSize = null;
		long compareSize = fileSize;

		if (compareSize < 1024) {
			getSize = Long.toString(fileSize) + " Bytes";
		} else if (compareSize < 1024 * 1024) {
			fileSize = fileSize / 1024;
			getSize = Long.toString(fileSize) + " KB";
		} else if (compareSize < 1024 * 1024 * 1024) {
			fileSize = fileSize / (1024 * 1024);
			getSize = Long.toString(fileSize) + " MB";
		} else if (compareSize < 1024 * 1024 * 1024 * 1024) {
			fileSize = fileSize / (1024 * 1024 * 1024);
			getSize = Long.toString(fileSize) + " GB";
		}
		return getSize;
	}

}
