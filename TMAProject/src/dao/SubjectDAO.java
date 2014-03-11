package dao;

import java.util.List;

//import model.Person;
import model.Material;
import model.Person;
import model.Subject;

public interface SubjectDAO extends Dao<Subject, Integer>{
	
	public String SUBJECT_ID = "subjectId";
	public String SUBJECT_NAME = "subjectName";
	public String PERSON = "person";
	
	
	public List<Subject> listAllSubject();
	public List<Material> listAllMaterialBySubjectId(Integer subjectId);
	public Subject getSubjectById(Integer subjectId);
	public Subject getSubjectByName(String subjectName);
	public List<Subject> getSubjectByPerson(Person person);
	
	public boolean addSubject(Subject subject);
	public boolean deleteSubjectById(Integer subjectId);
	public void deleteAllSubject();
	public void deleteAllMaterialOfOneSubject(Integer subjectId);
	
	public void modifySubjectId(Subject subject, Integer newID);
	public void modifySubjectName(Subject subject, String newSubjectName);	
	public void modifySubjectDescription(Subject subject, String newDescription);
	public boolean modifySubjectPerson(Subject subject, Person newPerson);
	
	
}
