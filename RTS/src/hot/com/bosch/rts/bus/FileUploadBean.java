package com.bosch.rts.bus;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Locale;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.Synchronized;
import org.jboss.seam.core.SeamResourceBundle;
import org.richfaces.event.UploadEvent;
import org.richfaces.model.UploadItem;

import com.bosch.rts.utilities.UploadFileInfo;

/**
 * The Class FileUploadBean.
 *
 * @author NMG1HC
 */
@Name("fileUploadBean")
@Synchronized
@Scope(ScopeType.CONVERSATION)
public class FileUploadBean implements Serializable{

	/** serialVersionUID. */
	private static final long serialVersionUID = -4165977635082645214L;
		
	/** The files. */
	private ArrayList<UploadFileInfo> files;
	
	/** The removed files. */
	private ArrayList<UploadFileInfo> removedFiles;	
	
	/** The uploads available. */
	private int uploadsAvailable;
	
	/** The auto upload. */
	private boolean autoUpload;
	
	/** The use flash. */
	private boolean useFlash;		
	
	/** The max file size. */
	private int maxFileSize;
	

	/**
	 * Gets the max file size.
	 *
	 * @return the max file size
	 */
	public int getMaxFileSize() {
		return maxFileSize;
	}

	/**
	 * Instantiates a new file upload bean.
	 */
	public FileUploadBean() {		
		
		files = new ArrayList<UploadFileInfo>();
		removedFiles = new ArrayList<UploadFileInfo>();
		
		this.uploadsAvailable = getMaxAvailableFile();
		this.autoUpload = getAutoUpload();
		this.useFlash = getUseFlash();		
		this.maxFileSize = getDefaultMaxFileSize();		
	}
	
	/**
	 * Gets the removed files.
	 *
	 * @return the removed files
	 */
	public ArrayList<UploadFileInfo> getRemovedFiles() {
		return removedFiles;
	}

	/**
	 * Sets the removed files.
	 *
	 * @param removedFiles the new removed files
	 */
	public void setRemovedFiles(ArrayList<UploadFileInfo> removedFiles) {
		this.removedFiles = removedFiles;
	}
	
	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public int getSize() {
		if (getFiles().size() > 0) {
			return getFiles().size();
		} else {
			return 0;
		}
	}

	/**
	 * Paint.
	 *
	 * @param stream the stream
	 * @param object the object
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void paint(OutputStream stream, Object object) throws IOException {
		stream.write(getFiles().get((Integer) object).getData());
	}
	
	/**
	 * Listener.
	 *
	 * @param event the event
	 * @throws Exception the exception
	 */
	public void listener(UploadEvent event) throws Exception {
		UploadItem item = event.getUploadItem();
		UploadFileInfo fileUloadFileInfo = new UploadFileInfo();
		
		final int currFileLength = item.getFileSize();
	
		if(currFileLength <= this.getMaxFileSize()){
			
			fileUloadFileInfo.setLength(item.getFileSize());
			byte[] data = item.getData();
			
			if(item.isTempFile()){	
				final String name = FilenameUtils.getName(item.getFileName());
				fileUloadFileInfo.setName(name);
				
				data = FileUtils.readFileToByteArray( item.getFile() );
				fileUloadFileInfo.setData(data);
			}
			
			files.add(fileUloadFileInfo);
			uploadsAvailable--;
		}
	}	
	
	/**
	 * Change status.
	 *
	 * @param rowIndex the row index
	 */
	public void changeStatus(int rowIndex){
		
	}

	/**
	 * Clear upload data.
	 *
	 * @param mode the mode
	 * @param attachmentRow the attachment row
	 */
	public void clearUploadData(String mode, final int attachmentRow) {			
		
		if(attachmentRow >= 0 && attachmentRow < files.size()){	
			
			if(mode.equals("candidate")){
				if(this.getFiles() != null 
						&& this.getFiles().get(attachmentRow).getCandidateAtt() != null){				
					removedFiles.add(this.getFiles().get(attachmentRow));
				}				
			}
			
			if(mode.equals("recruitrequest")){
				if(this.getFiles() != null 
						&& this.getFiles().get(attachmentRow).getRecruitRequestAtt() != null){				
					removedFiles.add(this.getFiles().get(attachmentRow));
				}
			}
					
			this.getFiles().remove(attachmentRow);
			final int _uploadsAvailable = this.getUploadsAvailable() + 1;
			this.setUploadsAvailable(_uploadsAvailable);
						
		}
	}
	
	/**
	 * Clear all upload data.
	 *
	 * @param mode the mode
	 */
	public void clearAllUploadData(String mode) {
		if(mode.equals("candidate")){			
			for(UploadFileInfo fileUpload : this.getFiles()){			
				if(fileUpload.getCandidateAtt() != null 
						&& fileUpload.getCandidateAtt().getChaAttachmentName() != null){
					removedFiles.add(fileUpload);
				}			
			}
		}		
		
		if(mode.equals("recruitrequest")){			
			for(UploadFileInfo fileUpload : this.getFiles()){			
				if(fileUpload.getRecruitRequestAtt() != null 
						&& fileUpload.getRecruitRequestAtt().getAttachmentName() != null){
					removedFiles.add(fileUpload);
				}			
			}		
		}
		
		this.getFiles().clear();
		
		int _uploadsAvailable = this.getMaxAvailableFile();
		this.setUploadsAvailable(_uploadsAvailable);
		
	}

	/**
	 * Gets the time stamp.
	 *
	 * @return the time stamp
	 */
	public long getTimeStamp() {
		return System.currentTimeMillis();
	}

	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public ArrayList<UploadFileInfo> getFiles() {
		return files;
	}

	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(ArrayList<UploadFileInfo> files) {
		this.files = files;
	}

	/**
	 * Gets the uploads available.
	 *
	 * @return the uploads available
	 */
	public int getUploadsAvailable() {
		return uploadsAvailable;
	}

	/**
	 * Sets the uploads available.
	 *
	 * @param uploadsAvailable the new uploads available
	 */
	public void setUploadsAvailable(int uploadsAvailable) {
		this.uploadsAvailable = uploadsAvailable;
	}

	/**
	 * Checks if is auto upload.
	 *
	 * @return true, if is auto upload
	 */
	public boolean isAutoUpload() {
		return autoUpload;
	}

	/**
	 * Sets the auto upload.
	 *
	 * @param autoUpload the new auto upload
	 */
	public void setAutoUpload(boolean autoUpload) {
		this.autoUpload = autoUpload;
	}

	/**
	 * Checks if is use flash.
	 *
	 * @return true, if is use flash
	 */
	public boolean isUseFlash() {
		return useFlash;
	}

	/**
	 * Sets the use flash.
	 *
	 * @param useFlash the new use flash
	 */
	public void setUseFlash(boolean useFlash) {
		this.useFlash = useFlash; 
	}
	
	/**
	 * Gets the max available file.
	 *
	 * @return the max available file
	 */
	public int getMaxAvailableFile()
	{
		
		return Integer.valueOf(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("max.upload.size"));
	}
	
	/**
	 * Gets the auto upload.
	 *
	 * @return the auto upload
	 */
	private boolean getAutoUpload()
	{
		return Boolean.valueOf(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("auto.upload"));
	}
	
	/**
	 * Gets the use flash.
	 *
	 * @return the use flash
	 */
	private boolean getUseFlash()
	{
		return Boolean.valueOf(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("use.flash"));
	}
	
	/**
	 * Gets the default max file size.
	 *
	 * @return the default max file size
	 */
	private int getDefaultMaxFileSize(){		
		return Integer.valueOf(SeamResourceBundle.getBundle("messages", Locale.ENGLISH).getString("recruit.request.maxfFileSize"));
	}
	
}