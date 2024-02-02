package com.practise.mapping.service;

import java.util.List;
import com.practise.mapping.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practise.mapping.model.Account;
import com.practise.mapping.model.Person;
import com.practise.mapping.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImp implements PersonService {

	@Autowired
	private PersonRepository personRepository;
	@Autowired 
	private AccountRepository accountRepository;
	
	@Override
	public List<Person> showPersons() {
		return personRepository.findAll();
	}

	@Override
	public Person showPerson(long personId) {
		Person person = new Person();
		person = personRepository.findById(personId).orElse(null);
		return person;
	}

	@Override
	public String savePerson(Person person) {
		personRepository.save(person);
		return "Person Saved";
	}

	@Override
	public String updatePerson(Person person) {
		Person existingPerson = new Person();
		Account existingAccount = new Account();
		Account account = person.getAccount();
		existingPerson = personRepository.findById(person.getPersonId()).orElseThrow(() -> new EntityNotFoundException("Person Does not Exist in Database"));
		existingAccount = existingPerson.getAccount();
		
		if(person.getName() != null) {
			existingPerson.setName(person.getName());
		}
		if(person.getGender() != null) {
			existingPerson.setGender(person.getGender());
		}
		if(person.getEmail() != null) {
			existingPerson.setEmail(person.getEmail());
		}
		if((person.getAge() + "").length() != 0 ) {
			if(person.getAge() >= 18 )
				existingPerson.setAge(person.getAge());
			else
				return "Age must be greater then 18";
		}
		
		if((account.getAccountId() + "").length() != 10) {
			return "Account Id must be of Length 10";
		}
		else {
			existingPerson.setAccount(person.getAccount());
		}
		return "Person Saved";
	}

	@Override
	public String deletePerson(long personId) {
		Person toDeletePerson = new Person();
		personRepository.delete(toDeletePerson);
		return "Person Deleted";
	}

}
