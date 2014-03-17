package com.bosch.rts.utilities;

/**
 * The Class File.
 */
public class File {

	/** The Name. */
	private String Name;
	
	/** The mime. */
	private String mime;
	
	/** The length. */
	private long length;
	
	/** The data. */
	private byte[] data;
	
	/** The file size. */
	private int fileSize;

	/**
	 * Gets the file size.
	 *
	 * @return the file size
	 */
	public int getFileSize() {
		return fileSize;
	}

	/**
	 * Sets the file size.
	 *
	 * @param fileSize the new file size
	 */
	public void setFileSize(int fileSize) {
		this.fileSize = fileSize;
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public byte[] getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the new data
	 */
	public void setData(byte[] data) {
		this.data = data;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return Name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		Name = name;
	}

	/**
	 * Sets the mime.
	 *
	 * @param mime the new mime
	 */
	public void setMime(String mime) {
		this.mime = mime;
	}

	/**
	 * Gets the length.
	 *
	 * @return the length
	 */
	public long getLength() {
		return length;
	}

	/**
	 * Sets the length.
	 *
	 * @param length the new length
	 */
	public void setLength(long length) {
		this.length = length;
	}

	/**
	 * Gets the mime.
	 *
	 * @return the mime
	 */
	public String getMime() {
		return mime;
	}
}