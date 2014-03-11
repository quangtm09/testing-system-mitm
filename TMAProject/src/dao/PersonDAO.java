package dao;

import model.Person;

public interface PersonDAO extends Dao<Person,Long>{
	public String FULL_NAME="fullName";
	public String EMAIL="email";
	public Person getPersonbyId(long personId);
	public Person getPersonByName(String fullName);
	public Person getPersonByEmail(String email);
}
