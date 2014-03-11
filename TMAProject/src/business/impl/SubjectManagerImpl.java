package business.impl;

import java.util.List;

import util.HibernateUtil;

import dao.SubjectDAO;

import business.SubjectManager;

import model.Material;
import model.Person;
import model.Subject;

public class SubjectManagerImpl implements SubjectManager {
	private SubjectDAO subjectDAO;
	private Integer subjectId;

	public void initSubjects() {
//		HibernateUtil.beginTransaction();
//
//		Subject subject1 = new Subject("Hibernate",
//				"What is hibernate? How to use? Advantages and drawbacks?");
//
//		Subject subject2 = new Subject("Spring",
//				"What is Spring? How to use? Advantages and drawbacks?");
//
//		Subject subject3 = new Subject("Operating System", "What is OS?");
//
//		Subject subject4 = new Subject("Principles of Programming Language",
//				"What is PPL?");
//
//		Subject subject5 = new Subject("Computer Architecture", "What is CA?");
//
//		Subject subject6 = new Subject("Data Structure & Algorithms",
//				"What is DSA?");
//
//		Subject subject7 = new Subject("Network Programming", "What is NP?");
//
//		Subject subject8 = new Subject("Web Application Development",
//				"What is WAD?");
//
//		Subject subject9 = new Subject("Liferay Social Network", "What is LSN?");
//
//		Subject subject10 = new Subject("Artificial Intelligence",
//				"What is AI?");
//
//		Subject subject11 = new Subject("Calculus 1",
//				"What should we do in this course?");
//
//		Subject subject12 = new Subject("Calculus 2",
//				"What should we do in this course?");
//
//		Subject subject13 = new Subject("Calculus 3",
//				"What should we do in this course?");
//
//		Subject subject14 = new Subject("Physics 1",
//				"What should we do in this course?");
//
//		Subject subject15 = new Subject("Physics 2",
//				"What should we do in this course?");
//
//		Subject subject16 = new Subject("Physics 3",
//				"What should we do in this course?");
//
//		Subject subject17 = new Subject("Information System Management",
//				"What should we do in this course?");
//
//		Subject subject18 = new Subject("Academic Writing",
//				"What should we do in this course?");
//
//		Subject subject19 = new Subject("IELTS",
//				"What should we do in this course?");
//
//		Subject subject20 = new Subject("TOEFL",
//				"What should we do in this course?");
//
//		Person person1 = new Person("Trung Huy Thu", "0938641688",
//				"trunghuythu@gmail.com");
//		Person person2 = new Person("Voong Thu Nhan", "0909569156",
//				"thanhnhankmm@gmail.com");
//		Person person3 = new Person("Nguyen Duy Anh", "0935571991",
//				"nda1291@yahoo.com");
//		Person person4 = new Person("Nguyen Duc Dinh Nghia", "0905616016",
//				"nddnghia@gmail.com");
//
//		HibernateUtil.getSession().save(person1);
//		HibernateUtil.getSession().save(person2);
//		HibernateUtil.getSession().save(person3);
//		HibernateUtil.getSession().save(person4);
//
//		subjectDAO.modifySubjectPerson(subject1, person1);
//		subjectDAO.modifySubjectPerson(subject2, person2);
//		subjectDAO.modifySubjectPerson(subject3, person3);
//		subjectDAO.modifySubjectPerson(subject4, person4);
//		subjectDAO.modifySubjectPerson(subject5, person1);
//		subjectDAO.modifySubjectPerson(subject6, person2);
//		subjectDAO.modifySubjectPerson(subject7, person3);
//		subjectDAO.modifySubjectPerson(subject8, person4);
//		subjectDAO.modifySubjectPerson(subject9, person1);
//		subjectDAO.modifySubjectPerson(subject10, person2);
//		subjectDAO.modifySubjectPerson(subject11, person3);
//		subjectDAO.modifySubjectPerson(subject12, person4);
//		subjectDAO.modifySubjectPerson(subject13, person1);
//		subjectDAO.modifySubjectPerson(subject14, person2);
//		subjectDAO.modifySubjectPerson(subject15, person3);
//		subjectDAO.modifySubjectPerson(subject16, person4);
//		subjectDAO.modifySubjectPerson(subject17, person1);
//		subjectDAO.modifySubjectPerson(subject18, person2);
//		subjectDAO.modifySubjectPerson(subject19, person3);
//		subjectDAO.modifySubjectPerson(subject20, person4);
//
//		subjectDAO.addSubject(subject1);
//		subjectDAO.addSubject(subject2);
//		subjectDAO.addSubject(subject3);
//		subjectDAO.addSubject(subject4);
//		subjectDAO.addSubject(subject5);
//		subjectDAO.addSubject(subject6);
//		subjectDAO.addSubject(subject7);
//		subjectDAO.addSubject(subject8);
//		subjectDAO.addSubject(subject9);
//		subjectDAO.addSubject(subject10);
//		subjectDAO.addSubject(subject11);
//		subjectDAO.addSubject(subject12);
//		subjectDAO.addSubject(subject13);
//		subjectDAO.addSubject(subject14);
//		subjectDAO.addSubject(subject15);
//		subjectDAO.addSubject(subject16);
//		subjectDAO.addSubject(subject17);
//		subjectDAO.addSubject(subject18);
//		subjectDAO.addSubject(subject19);
//		subjectDAO.addSubject(subject20);
//		HibernateUtil.commitTransaction();
	}

	public SubjectDAO getSubjectDAO() {
		return subjectDAO;
	}

	public void setSubjectDAO(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	@Override
	public Subject getSubjectById(Integer subjectId) {
		// TODO Auto-generated method stub
		return subjectDAO.getSubjectById(subjectId);
	}

	@Override
	public Subject getSubjectByName(String subjectName) {
		// TODO Auto-generated method stub
		return subjectDAO.getSubjectByName(subjectName);
	}

	@Override
	public List<Subject> getSubjectByPerson(Person person) {
		// TODO Auto-generated method stub
		return subjectDAO.getSubjectByPerson(person);
	}

	@Override
	public boolean addSubject(Subject subject) {
		// TODO Auto-generated method stub
		return subjectDAO.addSubject(subject);
	}

	@Override
	public boolean deleteSubjectById(Integer subjectId) {
		subjectDAO.deleteSubjectById(subjectId);
		return true;
	}

	@Override
	public void deleteAllSubject() {
		subjectDAO.deleteAllSubject();

	}

	@Override
	public void modifySubjectId(Subject subject, Integer newID) {
		// TODO Auto-generated method stub
		subjectDAO.modifySubjectId(subject, newID);
	}

	@Override
	public void modifySubjectName(Subject subject, String newSubjectName) {
		// TODO Auto-generated method stub
		subjectDAO.modifySubjectName(subject, newSubjectName);
	}

	@Override
	public void modifySubjectDescription(Subject subject, String newContent) {
		// TODO Auto-generated method stub
		subjectDAO.modifySubjectDescription(subject, newContent);
	}

	// @Override
	// public boolean modifySubjectPerson(Subject subject, Person newPerson) {
	// // TODO Auto-generated method stub
	// return subjectDAO.modifySubjectPerson(subject, newPerson);
	// }

	public List<Subject> getAllSubjects() {
		return subjectDAO.findAll();
	}

	@Override
	public boolean modifySubjectPerson(Subject subject, Person newPerson) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Subject> listAllSubject() {

		return subjectDAO.listAllSubject();
	}

	@Override
	public List<Material> listAllMaterialBySubjectId(Integer subjectId) {

		return subjectDAO.listAllMaterialBySubjectId(subjectId);
	}
}
