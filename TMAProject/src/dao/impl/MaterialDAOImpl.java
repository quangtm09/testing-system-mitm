package dao.impl;

import java.io.File;
import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import util.HibernateUtil;
import model.Material;
import model.Person;
import model.Subject;
import dao.AbstractHibernateDaoSupport;
import dao.MaterialDAO;

public class MaterialDAOImpl extends
		AbstractHibernateDaoSupport<Material, Integer> implements MaterialDAO {

	public MaterialDAOImpl() {
		super(Material.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Material getMaterialById(Integer materialId) {
		// TODO Auto-generated method stub
		return findById(materialId);
	}

	@Override
	public boolean addMaterial(Material material) {
		HibernateUtil.beginTransaction();
		save(material);
		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public Material getMaterialByTitle(String materialTitle) {
		Criteria crit = getSession().createCriteria(Material.class);
		crit.add(Restrictions.like("materialTitle", "%" + materialTitle + "%"));
		crit.addOrder(Order.asc("materialTitle"));

		List<Material> listMaterial = new ArrayList<Material>();
		Iterator<Material> lct = crit.list().iterator();
		while (lct.hasNext()) {
			Material material = lct.next();
			listMaterial.add(material);
		}
		System.out.println("MaterialDAO: " + listMaterial.size());
		return listMaterial.get(0);
	}

	@Override
	public List<Material> getMaterialByUrl(String url) {
		return findByProperty(URL, url);
	}

	@Override
	public List<Material> getMaterialByDateModification(String dateModification) {
		// TODO Auto-generated method stub
		return findByProperty(DATE_MODIFICATION, dateModification);
	}

	@Override
	public List<Material> getMaterialBySubjectId(Integer subjectId) {
		List<Material> listMaterial = new ArrayList<Material>();
		Iterator<Material> iteratorMaterial = findAll().iterator();
		while (iteratorMaterial.hasNext()) {
			Material material = iteratorMaterial.next();
			if (material.getSubject().getSubjectId() == subjectId)
				listMaterial.add(material);
		}
		return listMaterial;
	}

	@Override
	public boolean modifyMaterialById(Material material, Integer newID) {
		material.setMaterialId(newID);
		return update(material);
	}

	@Override
	public boolean modifyMaterialByTitle(Material material, String newTitle) {
		material.setMaterialTitle(newTitle);
		return update(material);
	}

	@Override
	public boolean modifyMaterialByUrl(Material material, String newUrl) {
		material.setMaterialUrl(newUrl);
		return update(material);
	}

	@Override
	public boolean modifyMaterialByDateModification(Material material,
			String newDateModification) {
		material.setDateModification(newDateModification);
		return update(material);
	}

	@Override
	public boolean modifyMaterialBySubject(Material material, Subject newSubject) {
		HibernateUtil.getSession().beginTransaction();
		material.setSubject(newSubject);
		update(material);
		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public boolean deleteMaterialById(Integer materialId) {
		Material material = getMaterialById(materialId);
		try {
			String filePathToDelete = material.getMaterialAbsolutePath();
			File fileToDelete = new File(filePathToDelete);
			fileToDelete.delete();
		} catch (Exception ex) {
			System.out.println("No file to delete!");
		}

		HibernateUtil.beginTransaction();
		delete(material);
		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public void deleteAllMaterial() {
		Criteria crit = getSession().createCriteria(Material.class);
		List<Material> listMaterial = crit.list();
		Iterator iterator = listMaterial.iterator();

		while (iterator.hasNext()) {
			Material material = (Material) iterator.next();
			deleteMaterialById(material.getMaterialId());
		}
	}

	@Override
	public void deleteAllMaterialOfSpecifiedSubject(Integer subjectId) {
		Criteria crit = getSession().createCriteria(Material.class);
		List<Material> listMaterial = crit.list();
		Iterator iterator = listMaterial.iterator();

		while (iterator.hasNext()) {
			Material material = (Material) iterator.next();
			if (material.getSubject().getSubjectId() == subjectId) {
				deleteMaterialById(material.getMaterialId());
			}
		}
	}

	@Override
	public boolean modifyMaterialByType(Material material, String materialType) {
		material.setMaterialType(materialType);
		return update(material);
	}

	@Override
	public List<Material> listAllMaterial() {
		List<Material> listMaterial = new ArrayList<Material>();
		Iterator<Material> iteratorMaterial = findAll().iterator();
		while (iteratorMaterial.hasNext()) {
			Material material = iteratorMaterial.next();
			listMaterial.add(material);
		}
		// TODO Auto-generated method stub
		return listMaterial;
	}

	@Override
	public List<Material> getMaterialByPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

}
