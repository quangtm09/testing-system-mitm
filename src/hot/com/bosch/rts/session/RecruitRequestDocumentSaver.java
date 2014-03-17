package com.bosch.rts.session;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import com.bosch.rts.utilities.UploadFileInfo;


/**
 * The Class RecruitRequestDocumentSaver.
 */
@Name("recruitRequestDocumentSaver")
public class RecruitRequestDocumentSaver implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8866592535550637482L;
	
	/** The log. */
	@Logger
	private transient Log log;	

	/** The file path. */
	private String filePath;	
	
	/**
	 * Do delete document.
	 *
	 * @param fileName the file name
	 * @param basePath the base path
	 * @return true, if successful
	 */
	public boolean doDeleteDocument(final String fileName, final String basePath) {
		
		boolean bResult = false;	
		
		if (fileName == null|| fileName.isEmpty()) {
			return false;
		}
		
		File currentFile = new File(basePath + fileName);
		
		if(currentFile.exists())
		{
			bResult = currentFile.delete();
		}
		return bResult;
		
	}

	/**
	 * Save document.
	 *
	 * @param recruitRequestName the recruit request name
	 * @param file the file
	 * @param basePath the base path
	 * @return the string
	 */
	public String saveDocument(String recruitRequestName,
			UploadFileInfo file, String basePath) {
		
		FileOutputStream fos = null;
		try {
			String ext = getFileExtension(file.getName());
			String fileName = recruitRequestName;	
			String strName = fileName;
			File f = new File(basePath.toString());
			if (!f.exists()) {
				return null;
			}
			
			String fileFullPath = basePath.toString() + fileName;
			f = new File(fileFullPath);
			int counter = 2;			
			while (f.exists()) {
				
				final String strRealName = this.getRealName(fileName);
				
				strName = strRealName + "_" + counter + "." + ext;	
				
				fileFullPath = basePath.toString() + strName ; 	
				
				f = new File(fileFullPath);		
				
				counter++;
			}						
			
			boolean created = f.createNewFile();
			if (created) {
				fos = new FileOutputStream(f);
				fos.write(file.getData());
				filePath = fileFullPath;
				return strName;
			} else {
				return null;
			}
		} catch (IOException ex) {
			ex.printStackTrace();
			return null;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					log.error("Execute saveDocument method getting error on RecruitRequestDocumentSaver class------------------");
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
	 * Gets the supported file extensions.
	 *
	 * @return the supported file extensions
	 */
	public List<String> getSupportedFileExtensions() {
		String[] supportedList = { "xls", "xlsx", "xlsm", "xmlsb", "xps",
				"pdf", "docx", "docm", "doc", "odt", "rtf" };
		return Arrays.asList(supportedList);
	}

	/**
	 * Checks if is extension supported.
	 *
	 * @param ext the ext
	 * @return true, if is extension supported
	 */
	public boolean isExtensionSupported(String ext) {
		for (String sExt : getSupportedFileExtensions()) {
			if (ext.equals(sExt)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Creates the folder.
	 *
	 * @param folderName the folder name
	 */
	public void createFolder(String folderName){	
		if(folderName != null && !folderName.isEmpty()){
			File file = new File(folderName);
			
			boolean exists = file.exists();
			if(!exists){			 
			 file.mkdirs();
			}
		}		
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
	
	/**
	 * Gets the real name.
	 *
	 * @param fileName the file name
	 * @return the real name
	 */
	private String getRealName(String fileName){		
		String strResult = fileName;
		
		int index = fileName.lastIndexOf(".");
		if (index > 0) {
			if (index + 1 < fileName.length()) {
				strResult = fileName.substring(0, index);
			}
		}
		return strResult;
	}

}
