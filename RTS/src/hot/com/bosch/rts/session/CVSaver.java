package com.bosch.rts.session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Map;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.international.StatusMessage.Severity;
import org.jboss.seam.log.Log;

import com.bosch.rts.entity.Candidate;
import com.bosch.rts.utilities.UploadFileInfo;

/**
 * The Class CVSaver.
 *
 * @author vut2hc
 */
@Name("cvSaver")
public class CVSaver implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8866592535550637482L;
	
	/** The messages. */
	@In
	private Map<String, String> messages;
	
	/** The file path. */
	private String filePath;
	
	/** The log. */
	@Logger
	private transient Log log;

	/**
	 * Delete cv.
	 *
	 * @param candidate the candidate
	 * @return true, if successful
	 */
	public boolean deleteCV(Candidate candidate) {
		String basePath = messages.get("com.bosch.storage");
		if (candidate.getCcdCVName() == null
				|| candidate.getCcdCVName().isEmpty()) {
			return false;
		}
		File f = new File(basePath + candidate.getCcdCVName());
		return f.delete();
	}

	/**
	 * Save cv.
	 *
	 * @param candidate the candidate
	 * @param file the file
	 * @return the string
	 */
	public String saveCV(Candidate candidate, UploadFileInfo file) {
		FileOutputStream fos = null;
		try {
			String ext = getFileExtension(file.getName());
			Calendar cal = Calendar.getInstance();
			String fileName = candidate.getCddName() + cal.getTimeInMillis();
			String basePath = messages.get("com.bosch.storage");
			File f = new File(basePath.toString());
			if (!f.exists()) {
				return null;
			}
			while (true) {
				fileName = fileName + "." + ext;
				String fileFullPath = basePath.toString() + fileName;
				f = new File(fileFullPath);
				if (f.exists()) {
					fileName = candidate.getCddName() + cal.getTimeInMillis();
					continue;
				}
				boolean created = f.createNewFile();
				if (created) {
					fos = new FileOutputStream(f);
					fos.write(file.getData());
					filePath = fileFullPath;
					return fileName;
				} else {
					return null;
				}
			}
		} catch (IOException ex) {
			log.error("Execute saveCV method getting error------------------");
			return null;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error("Execute saveCV method getting error on closing file------------------");
				}
			}
		}
	}

	/**
	 * This function must be call if something wrong transaction.
	 */
	public void rollBack() {
		if (filePath != null) {
			File f = new File(filePath);
			boolean deleted = f.delete();
			assert deleted;
		}
	}

	/**
	 * Check cv format.
	 *
	 * @param file the file
	 * @return true, if successful
	 */
	public boolean checkCVFormat(UploadFileInfo file) {
		if (file.getName() == null || file.getName().isEmpty()
				|| file.getData().length == 0) {
			FacesMessages.instance().clear();
			FacesMessages.instance().addFromResourceBundle(Severity.ERROR,
					"com.bosch.ui.warning.cvrequired");
			return false;
		}
		// if (!(file.getMime().equals(DocumentMimeConstant.DOC))
		// || file.getMime().equals(DocumentMimeConstant.DOCX)
		// || file.getMime().equals(DocumentMimeConstant.PDF)) {
		// FacesMessages.instance().addFromResourceBundle(Severity.ERROR,
		// "CV must in doc,docx or pdf format");
		// return false;
		// }
		return true;
	}

	/**
	 * Gets the file extension.
	 *
	 * @param fileName the file name
	 * @return the file extension
	 */
	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf(".");
		if (index > 0) {
			if (index + 1 < fileName.length()) {
				return fileName.substring(index + 1);
			}
		}
		return "";
	}

}
