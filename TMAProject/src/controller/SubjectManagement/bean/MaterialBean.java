package controller.SubjectManagement.bean;

import java.util.Calendar;
import java.util.List;

import model.Material;

import org.springframework.web.multipart.MultipartFile;

public class MaterialBean {

	private String materialTitle;
	private double materialSize;
	private String materialType;
	private String materialUrl;
	private String dateModification;
	private MultipartFile materialFile;
	private String subjectName;
	private List<Material> materialList;
	private List<String> subjectList;


	public List<String> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<String> subjectList) {
		this.subjectList = subjectList;
	}

	public String getMaterialTitle() {
		return materialTitle;
	}

	public void setMaterialTitle(String materialTitle) {
		this.materialTitle = materialTitle;
	}

	public double getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(double materialSize) {
		this.materialSize = materialSize;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialUrl() {
		return materialUrl;
	}

	public void setMaterialUrl(String materialUrl) {
		this.materialUrl = materialUrl;
	}


	public MultipartFile getMaterialFile() {
		return materialFile;
	}

	public void setMaterialFile(MultipartFile materialFile) {
		this.materialFile = materialFile;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDateModification() {
		return dateModification;
	}

	public void setDateModification(String dateModification) {
		this.dateModification = dateModification;
	}
}
