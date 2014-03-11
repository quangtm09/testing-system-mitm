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
//import model.Person;
import model.Subject;
import dao.AbstractHibernateDaoSupport;
import dao.SubjectDAO;

public class SubjectDAOImpl extends
		AbstractHibernateDaoSupport<Subject, Integer> implements SubjectDAO {

	private MaterialDAOImpl materialDAO = new MaterialDAOImpl();

	public SubjectDAOImpl() {
		super(Subject.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Subject getSubjectById(Integer subjectId) {
		// TODO Auto-generated method stub
		return findById(subjectId);
	}

	@Override
	public Subject getSubjectByName(String subjectName) {
		Criteria crit = getSession().createCriteria(Subject.class);
		crit.add(Restrictions.like("subjectName", "%" + subjectName + "%"));
		crit.addOrder(Order.asc("subjectName"));

		List<Subject> listSubject = new ArrayList<Subject>();
		Iterator<Subject> lct = crit.list().iterator();

		System.out.println("From subjectDAO: " + 1);

		while (lct.hasNext()) {
			Subject subject = lct.next();
			listSubject.add(subject);
		}
		if (listSubject.size() == 0)
			return null;
		else
			return listSubject.get(0);
	}

	@Override
	public boolean addSubject(Subject subject) {
		// TODO Auto-generated method stub
		return save(subject);
	}

	@Override
	public void modifySubjectId(Subject subject, Integer newID) {
		HibernateUtil.beginTransaction();
		subject.setSubjectId(newID);
		update(subject);
		HibernateUtil.commitTransaction();
	}

	@Override
	public void modifySubjectName(Subject subject, String newSubjectName) {
		String oldSubjectName = subject.getSubjectName().trim()
				.replaceAll(" ", "_").replaceAll("\\/", "")
				.replaceAll("\\\\", "").replaceAll("\\<", "")
				.replaceAll("\\>", "").replaceAll("\\?", "")
				.replaceAll("\\|", "").replaceAll(":", "")
				.replaceAll("\\*", "");
		HibernateUtil.beginTransaction();
		subject.setSubjectName(newSubjectName);
		update(subject);
		HibernateUtil.commitTransaction();
		newSubjectName = newSubjectName.trim().replaceAll(" ", "_")
				.replaceAll("\\/", "").replaceAll("\\\\", "")
				.replaceAll("\\<", "").replaceAll("\\>", "")
				.replaceAll("\\?", "").replaceAll("\\|", "")
				.replaceAll(":", "").replaceAll("\\*", "");
		File oldName = new File("C:/Upload/" + oldSubjectName);
		System.out.println(oldName.getAbsolutePath());
		if (oldName.exists()) {
			File newName = new File("C:/Upload/" + newSubjectName);
			oldName.renameTo(newName);
		} else {
			System.out.println("This subject does not have a folder yet !!!");
		}

	}

	@Override
	public void modifySubjectDescription(Subject subject, String newDescription) {
		HibernateUtil.beginTransaction();
		subject.setSubjectDescription(newDescription);
		update(subject);
		HibernateUtil.commitTransaction();
	}

	@Override
	public List<Subject> getSubjectByPerson(Person person) {
		return findByProperty(PERSON, person);
	}

	@Override
	public boolean modifySubjectPerson(Subject subject, Person newPerson) {
		subject.setPerson(newPerson);
		return update(subject);
	}

	@Override
	public boolean deleteSubjectById(Integer subjectId) {

		// If subject was deleted, all materials of that subject must be deleted
		// first (deleting record in DB)
		deleteAllMaterialOfOneSubject(subjectId);
		Subject subject = getSubjectById(subjectId);
		String subjectDirectory = subject.getSubjectName();

		try {
			// Delete folder
			File folderToDelete = new File("../Upload/"
					+ subjectDirectory.trim().replaceAll(" ", "_")
							.replaceAll("\\/", "").replaceAll("\\\\", "")
							.replaceAll("\\<", "").replaceAll("\\>", "")
							.replaceAll("\\?", "").replaceAll("\\|", "")
							.replaceAll(":", "").replaceAll("\\*", ""));

			String[] listFileInFolder;

			if (folderToDelete.exists() && folderToDelete.isDirectory()) {
				if (folderToDelete.list().length != 0) {
					listFileInFolder = folderToDelete.list();

					// for each file in folder, delete that file
					for (String nameOfFileInFolder : listFileInFolder) {
						// construct the file structure: File(dir of file, name
						// of file)
						File fileInFolder = new File(folderToDelete,
								nameOfFileInFolder);

						// recursive delete
						fileInFolder.delete();
					}

				}

				folderToDelete.delete();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("No folder to delete!");
		}

		HibernateUtil.beginTransaction();
		delete(subject);
		HibernateUtil.commitTransaction();
		return true;
	}

	@Override
	public void deleteAllSubject() {
		Criteria crit = getSession().createCriteria(Subject.class);
		List<Subject> listSubject = crit.list();
		Iterator iterator = listSubject.iterator();

		while (iterator.hasNext()) {
			Subject subject = (Subject) iterator.next();
			deleteSubjectById(subject.getSubjectId());
		}
	}

	@Override
	public void deleteAllMaterialOfOneSubject(Integer subjectId) {

		System.out.println("delete from delete all material of subject");
		// Get a list of material of the deleted subject
		List<Material> listMaterial = materialDAO
				.getMaterialBySubjectId(subjectId);

		System.out.println("list material size: " + listMaterial.size());
		// If list is not null
		if (listMaterial.size() != 0) {
			Iterator<Material> iterator = listMaterial.iterator();
			while (iterator.hasNext()) {
				Material material = iterator.next();
				materialDAO.deleteMaterialById(material.getMaterialId());
			}
		}

	}

	@Override
	public List<Subject> listAllSubject() {
		List<Subject> listSubject = new ArrayList<Subject>();
		Iterator<Subject> iteratorSubject = findAll().iterator();
		while (iteratorSubject.hasNext()) {
			Subject subject = iteratorSubject.next();
			listSubject.add(subject);
		}
		// TODO Auto-generated method stub
		return listSubject;
	}

	@Override
	public List<Material> listAllMaterialBySubjectId(Integer subjectId) {
		Criteria criteria = getSession().createCriteria(Material.class).add(
				Restrictions.eq("subject.subjectId", subjectId));
		List<Material> listMaterial = new ArrayList<Material>();
		Iterator<Material> lct = criteria.list().iterator();

		while (lct.hasNext()) {
			Material material = lct.next();
			listMaterial.add(material);
		}
		if (listMaterial.size() == 0) {
			return null;
		} else {
			return listMaterial;
		}

	}

}
