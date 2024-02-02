package com.practise.mapping.service;

import java.util.List;
import com.practise.mapping.model.Person;

public interface PersonService {

	public List<Person> showPersons(); 
	public Person showPerson(long personId);
	public String savePerson(Person person);
	public String updatePerson(Person person);
	public String deletePerson(long personId);
	
}
