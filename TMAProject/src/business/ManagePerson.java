package business;

import java.util.List;

import model.Person;

public interface ManagePerson {
	public List<Person> getPersons();
	public boolean insertPerson(Person person);
	public boolean deletePerson(Person person);
	public boolean updatePerson(Person person);
	public Person getPerson(long personId);
	public Person getPersonByEmail(String email);
}
