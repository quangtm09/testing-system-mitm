package business.impl;

import java.util.Calendar;
import java.util.List;

import util.HibernateUtil;

import dao.MaterialDAO;

import model.Material;
import model.Subject;
import business.MaterialManager;

public class MaterialManagerImpl implements MaterialManager {
	private MaterialDAO materialDAO;
	private Integer materialId;

	public void initMaterials() {
	}

	public MaterialDAO getMaterialDAO() {
		return materialDAO;
	}

	public void setMaterialDAO(MaterialDAO materialDAO) {
		this.materialDAO = materialDAO;
	}

	public Integer getMaterialId() {
		return materialId;
	}

	public void setMaterialId(Integer materialId) {
		this.materialId = materialId;
	}

	@Override
	public Material getMaterialById(Integer materialId) {
		// TODO Auto-generated method stub
		return materialDAO.getMaterialById(materialId);
	}

	@Override
	public Material getMaterialByTitle(String title) {
		// TODO Auto-generated method stub
		return materialDAO.getMaterialByTitle(title);
	}

	@Override
	public List<Material> getMaterialByUrl(String url) {
		// TODO Auto-generated method stub
		return materialDAO.getMaterialByUrl(url);
	}

	@Override
	public List<Material> getMaterialByDateModification(String dateModification) {
		// TODO Auto-generated method stub
		return materialDAO.getMaterialByDateModification(dateModification);
	}

	@Override
	public List<Material> getMaterialBySubjectId(Integer subjectId) {
		// TODO Auto-generated method stub
		return materialDAO.getMaterialBySubjectId(subjectId);
	}

	@Override
	public boolean addMaterial(Material material) {
		// TODO Auto-generated method stub
		return this.materialDAO.save(material);
	}

	@Override
	public boolean deleteMaterialById(Integer materialId) {
		materialDAO.deleteMaterialById(materialId);
		return true;
	}

	@Override
	public void deleteAllMaterial() {
		materialDAO.deleteAllMaterial();

	}

	@Override
	public void deleteAllMaterialOfSpecifiedSubject(Integer subjectId) {
		materialDAO.deleteAllMaterialOfSpecifiedSubject(subjectId);

	}

	@Override
	public boolean modifyMaterialById(Material material, Integer newID) {
		// TODO Auto-generated method stub
		return materialDAO.modifyMaterialById(material, newID);
	}

	@Override
	public boolean modifyMaterialByTitle(Material material, String newTitle) {
		// TODO Auto-generated method stub
		return materialDAO.modifyMaterialByTitle(material, newTitle);
	}

	@Override
	public boolean modifyMaterialByUrl(Material material, String newUrl) {
		// TODO Auto-generated method stub
		return materialDAO.modifyMaterialByUrl(material, newUrl);
	}

	@Override
	public boolean modifyMaterialByDateModification(Material material,
			String newDateModification) {
		// TODO Auto-generated method stub
		return materialDAO.modifyMaterialByDateModification(material,
				newDateModification);
	}

	@Override
	public boolean modifyMaterialBySubject(Material material, Subject newSubject) {
		// TODO Auto-generated method stub
		return materialDAO.modifyMaterialBySubject(material, newSubject);
	}

	public List<Material> getAllMaterials() {
		return materialDAO.findAll();
	}

	@Override
	public boolean modifyMaterialByType(Material material, String materialType) {

		return materialDAO.update(material);
	}

	@Override
	public List<Material> listAllMaterial() {
		return materialDAO.listAllMaterial();
	}

}
