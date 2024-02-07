package com.practise.mapping.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.practise.mapping.model.Account;
import com.practise.mapping.model.Person;
import com.practise.mapping.repository.PersonRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PersonServiceImp implements PersonService {

	@Autowired
	private PersonRepository personRepository;

	private static final String UPLOAD_URL = "D:\\localStorage";

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
		Account account = person.getAccount();
		existingPerson = personRepository.findById(person.getPersonId())
				.orElseThrow(() -> new EntityNotFoundException("Person Does not Exist in Database"));

		if (person.getName() != null) {
			existingPerson.setName(person.getName());
		}
		if (person.getGender() != null) {
			existingPerson.setGender(person.getGender());
		}
		if (person.getEmail() != null) {
			existingPerson.setEmail(person.getEmail());
		}
		if ((person.getAge() + "").length() != 0) {
			existingPerson.setAge(person.getAge());
		}

		if ((account.getAccountId() + "").length() != 10) {
			return "Account Id must be of Length 10";
		} else {
			existingPerson.setAccount(account);
		}

		personRepository.save(existingPerson);
		return "Person Updated";
	}

	@Override
	public String deletePerson(long personId) {
		personRepository.deleteById(personId);
		return "Person Deleted";
	}

	@Override
	public ResponseEntity<String> uploadPerson(MultipartFile file) {
		try {
			File directory = new File(UPLOAD_URL);
			if (!directory.exists()) {
				directory.mkdirs();
			}

			String fileName = file.getOriginalFilename();
			Path filePath = Paths.get(UPLOAD_URL).resolve(fileName);

			Files.write(filePath, file.getBytes(), StandardOpenOption.CREATE);

			return ResponseEntity.ok("File Uploaded.");
		} catch (IOException ex) {
			return ResponseEntity.status(500).body("File not Uploaded : " + ex.getMessage());
		}
	}

	@Override
	public ResponseEntity<String> addDetails(String file, Long personId) {
		try {
			String fileName = file + ".txt";
			Path filePath = Paths.get(UPLOAD_URL).resolve(fileName).normalize();

			Person person = new Person();
			person = personRepository.findById(personId)
					.orElseThrow(() -> new EntityNotFoundException("Person does not Exist in database."));

			String name = person.getName();

			Files.write(filePath, name.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException ex) {
			return ResponseEntity.status(500).body("File did not Updated: " + ex.getMessage());
		}

		return ResponseEntity.ok("File Updated");
	}

	@Override
	public ResponseEntity<Object> downloadFile(String file) {
		ResponseEntity<Object> response = null;
		String fileName = file + ".txt";
		try {
			Path filePath = Paths.get(UPLOAD_URL).resolve(fileName).normalize();
			File downloadFile = filePath.toFile();

			if (downloadFile.exists()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.TEXT_PLAIN);
				headers.setContentDispositionFormData("attachment", file);

				response = ResponseEntity.ok().headers(headers).contentLength(downloadFile.length())
						.body(new FileSystemResource(downloadFile));
			} else {
				response = ResponseEntity.notFound().build();
			}
		} catch (Exception ex) {
			response = ResponseEntity.status(500).body("Failed to download the file: " + ex.getMessage());
		}
		return response;
	}

}
