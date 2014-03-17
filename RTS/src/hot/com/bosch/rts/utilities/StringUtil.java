package com.bosch.rts.utilities;

import java.io.Serializable;

import org.jboss.seam.annotations.Name;

/**
 * The Class StringUtil.
 */
@Name("stringUtil")
public class StringUtil implements Serializable {

	/** serialVersionUID. */
	private static final long serialVersionUID = 8186979900230418720L;

	/**
	 * Sub string.
	 *
	 * @param s the s
	 * @param start the start
	 * @param length the length
	 * @return the string
	 */
	public String subString(String s, int start, int length) {
		if (s.length() < start + length) {
			return s;
		}

		return s.substring(start, length);
	}

}