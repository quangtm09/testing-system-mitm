/**
 * com.bosch.rts.entity.UploadUtils
 */
package com.bosch.rts.entity;

import java.text.DecimalFormat;

/**
 * File upload utilization.
 *
 * @author khb1hc
 */
public class UploadUtils {
	
	/** The Constant KB. */
	private static final String KB = " KB";
	
	/** The Constant MB. */
	private static final String MB = " MB";
	
	/** The Constant FRACTION. */
	private static final String FRACTION = "#0.##";
	
	/** The Constant KYLOBYTE. */
	private static final int KYLOBYTE = 1024;
	
	/** The Constant MEGABYTE. */
	private static final int MEGABYTE = 1024*1024;
	
	/**
	 * Human readable file length.
	 *
	 * @param attachmentLength the attachment length
	 * @return the string
	 */
	public static String humanReadableFileLength(final int attachmentLength ){
		final DecimalFormat df = new DecimalFormat(FRACTION);
		if(KYLOBYTE <= attachmentLength && attachmentLength < MEGABYTE){			
			return df.format((double)attachmentLength/KYLOBYTE)  + KB; 
		} else if (attachmentLength >= MEGABYTE){
			return df.format((double)attachmentLength/MEGABYTE) + MB; 	
		} 
		return "0" + KB;		
	}
}
