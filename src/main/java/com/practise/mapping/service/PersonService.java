package com.practise.mapping.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.practise.mapping.model.Person;

public interface PersonService {

	public List<Person> showPersons(); 
	public Person showPerson(long personId);
	public String savePerson(Person person);
	public String updatePerson(Person person);
	public String deletePerson(long personId);
	public String uploadPerson(MultipartFile file);
	public String addDetails(String file , Long personId);	
	public ResponseEntity<Object> downloadFile(String file);
}
