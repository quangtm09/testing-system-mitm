package dao.impl;

import java.util.List;

import model.Person;
import dao.AbstractHibernateDaoSupport;
import dao.PersonDAO;

public class PersonDAOImpl extends AbstractHibernateDaoSupport<Person, Long> implements PersonDAO{

	protected PersonDAOImpl() {
		super(Person.class);
	}

	@Override
	public Person getPersonbyId(long personId) {
		return findById(personId);
	}

	@Override
	public Person getPersonByName(String fullName) {
		List<Person> resultList=findByProperty(FULL_NAME, fullName);
		if(resultList.size()==0){
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public Person getPersonByEmail(String email) {
		List<Person> resultList=findByProperty(EMAIL, email);
		if(resultList.size()==0){
			return null;
		}
		return resultList.get(0);
	}

}
