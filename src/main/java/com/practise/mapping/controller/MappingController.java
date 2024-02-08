package com.practise.mapping.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.practise.mapping.dto.ResponseDto;
import com.practise.mapping.model.Account;
import com.practise.mapping.model.Person;
import com.practise.mapping.service.AccountService;
import com.practise.mapping.service.PersonService;

@RestController
@RequestMapping("/api")
public class MappingController {

	@Autowired
	private AccountService accountService;
	
	@Autowired
	private PersonService personService;
	
	@GetMapping("/person")
	public  ResponseDto showPersons(){
		List<Person> persons = new ArrayList<>();
		persons = personService.showPersons();
		return new ResponseDto(200,"All the Persons",persons);
	}
	
	@GetMapping("/person/{personId}")
	public ResponseDto showPerson(@PathVariable("personId") Long personId) {
		Person person = new Person();
		person = personService.showPerson(personId);
		return new ResponseDto(200,"Person Fetched",person);
	}
	
	@GetMapping("/account")
	public ResponseDto showAccounts(){
		List<Account> accounts = new ArrayList<>();
		accounts = accountService.showAccounts();
		return new ResponseDto(200,"All the Accounts",accounts);
	}
	
	@GetMapping("/account/{accountId}")
	public ResponseDto showAccount(@PathVariable("accountId") Long accountId) {
		Account account = new Account(); 
		account = accountService.showAccount(accountId);
		return new ResponseDto(200,"Account Fetched",account);
	}
	
	@PostMapping("/personSave")
	public ResponseDto savePerson(@RequestBody Person person) {
		String message = new String();
		message = personService.savePerson(person);
		return new ResponseDto(200,message);
	}
	
	@PostMapping("/accountSave")
	public ResponseDto saveAccount(@RequestBody Account account) {
		String message = new String();
		message = accountService.saveAccount(account);
		return new ResponseDto(200,message);
	}
	
	@DeleteMapping("/personDelete/{personId}")
	public ResponseDto deletePerson(@PathVariable("personId") Long personId) {
		String message = new String();
		message = personService.deletePerson(personId);
		return new ResponseDto(200,message);
	}
	
	@DeleteMapping("/accountDelete/{accountId}")
	public ResponseDto deleteAccount(@PathVariable("accountId") Long accountId) {
		String message = new String();
		message = accountService.deleteAccount(accountId);
		return new ResponseDto(200,message);
	}
	
	@PutMapping("/personUpdate")
	public ResponseDto updatePerson(@RequestBody Person person) {
		String message = new String();
		message = personService.updatePerson(person);
		return new ResponseDto(200,message);
	}
	
	@PostMapping("/upload")
	public ResponseDto uploadFile(@RequestParam("file") MultipartFile file){
		String toUploadFile = personService.uploadPerson(file);
		return new ResponseDto(200,toUploadFile);
	}
	
	@PutMapping("/addDetail/{fileName}/{personId}")
	public ResponseDto appedFile(@PathVariable("fileName") String fileName , @PathVariable("personId") Long personId){
		String uploadedFile = personService.addDetails(fileName, personId); 
		return new ResponseDto(200, uploadedFile);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Object> downloadFile(@PathVariable("fileName") String fileName){
		return personService.downloadFile(fileName);
	}
} 
