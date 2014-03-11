package test;

import java.util.List;

import model.Person;
import business.ManagePerson;

public class PersonTest{
	private ManagePerson managePerson;

	public ManagePerson getManagePerson() {
		return managePerson;
	}

	public void setManagePerson(ManagePerson managePerson) {
		this.managePerson = managePerson;
	}
	
	public void printAllPersons() {
        List<Person> personList=managePerson.getPersons();
        for(Person person:personList){
        	System.out.println(person.getFullName());
        }
    }
	
	public void getPersonbyId(long personId){
		System.out.println(managePerson.getPerson(personId).getFullName());
	}
}
