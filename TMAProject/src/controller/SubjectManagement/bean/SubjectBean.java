package controller.SubjectManagement.bean;

import java.util.List;

import model.Material;
import model.Subject;

import org.springframework.web.multipart.MultipartFile;

public class SubjectBean {
	private Integer subjectId;
	private String subjectName;
	private String subjectDescription;
	private Long personId;
	private List<Subject> subjectList;
	private List<Material> materialList;
	private String oldSubjectName;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectDescription() {
		return subjectDescription;
	}

	public void setSubjectDescription(String subjectDescription) {
		this.subjectDescription = subjectDescription;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public List<Subject> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(List<Subject> subjectList) {
		this.subjectList = subjectList;
	}

	public List<Material> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<Material> materialList) {
		this.materialList = materialList;
	}
	
	private String materialTitle;
	private double materialSize;
	private String materialType;
	private String materialUrl;
	private MultipartFile materialFile;

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

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public void setMaterialFile(MultipartFile materialFile) {
		this.materialFile = materialFile;
	}

	public String getOldSubjectName() {
		return oldSubjectName;
	}

	public void setOldSubjectName(String oldSubjectName) {
		this.oldSubjectName = oldSubjectName;
	}
}
