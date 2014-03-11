/**
 * 
 */
package model;

import java.io.Serializable;
import java.util.Calendar;


/**
 * @author Jack3000
 *
 */

public class Material implements Serializable{
	private static final long serialVersionUID = -5529834272720257419L;
	
	private Integer materialId;
	private String materialType;
	private String materialUrl;
	private String materialAbsolutePath;
	private String materialTitle;
	private String materialSize;
	private String dateModification;
	private Subject subject;
	private Person person;
	
	
	public Material() {
		super();
	}
	
	

	public Material(String materialTitle) {
		super();
		this.materialTitle = materialTitle;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	public String getMaterialUrl() {
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl;
	}

	public String getMaterialTitle() {
		return materialTitle;
	}

	public void setMaterialTitle(String materialTitle) {
		this.materialTitle = materialTitle;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}



	public String getMaterialAbsolutePath() {
		return materialAbsolutePath;
	}



	public void setMaterialAbsolutePath(String materialAbsolutePath) {
		this.materialAbsolutePath = materialAbsolutePath;
	}



	public Person getPerson() {
		return person;
	}



	public void setPerson(Person person) {
		this.person = person;
	}


}

