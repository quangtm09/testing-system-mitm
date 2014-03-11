package business;

import java.util.List;

import model.Material;
import model.Person;
import model.Subject;

public interface SubjectManager {
	
	
	public List<Material> listAllMaterialBySubjectId(Integer subjectId);
	public List<Subject> listAllSubject();
	public Subject getSubjectById(Integer subjectId);
	public Subject getSubjectByName(String subjectName);
	public List<Subject> getSubjectByPerson(Person person);
	
	public boolean addSubject(Subject subject);
	public boolean deleteSubjectById(Integer subjectId);
	public void deleteAllSubject();
	
	public void modifySubjectId(Subject subject, Integer newID);
	public void modifySubjectName(Subject subject, String newSubjectName);	
	public void modifySubjectDescription(Subject subject, String newDescription);
	public boolean modifySubjectPerson(Subject subject, Person newPerson);

}
