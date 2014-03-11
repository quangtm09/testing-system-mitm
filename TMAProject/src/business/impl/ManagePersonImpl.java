package business.impl;

import java.util.List;

import dao.PersonDAO;

import model.Person;
import business.ManagePerson;

public class ManagePersonImpl implements ManagePerson{
	private PersonDAO personDAO;
	
	public PersonDAO getPersonDAO() {
		return personDAO;
	}

	public void setPersonDAO(PersonDAO personDAO) {
		this.personDAO = personDAO;
	}
	
	@Override
	public List<Person> getPersons() {
		return personDAO.findAll();
	}

	@Override
	public Person getPerson(long personId) {
		return personDAO.getPersonbyId(personId);
	}

	@Override
	public boolean insertPerson(Person person) {
		// TODO Auto-generated method stub
		return personDAO.save(person);
	}

	@Override
	public boolean deletePerson(Person person) {
		// TODO Auto-generated method stub
		return personDAO.delete(person);
	}

	@Override
	public boolean updatePerson(Person person) {
		// TODO Auto-generated method stub
		return personDAO.update(person);
	}

	@Override
	public Person getPersonByEmail(String email) {
		// TODO Auto-generated method stub
		return personDAO.getPersonByEmail(email);
	}


}
