package com.practise.mapping.controller;

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
	public List<Person> showPersons(){
		return personService.showPersons();
	}
	
	@GetMapping("/person/{personId}")
	public Person showPerson(@PathVariable("personId") Long personId) {
		return personService.showPerson(personId);
	}
	
	@GetMapping("/account")
	public List<Account> showAccounts(){
		return accountService.showAccounts();
	}
	
	@GetMapping("/account/{accountId}")
	public Account showAccount(@PathVariable("accountId") Long accountId) {
		return accountService.showAccount(accountId);
	}
	
	@PostMapping("/personSave")
	public String savePerson(@RequestBody Person person) {
		return personService.savePerson(person);
	}
	
	@PostMapping("/accountSave")
	public String saveAccount(@RequestBody Account account) {
		return accountService.saveAccount(account);
	}
	
	@DeleteMapping("/personDelete/{personId}")
	public String deletePerson(@PathVariable("personId") Long personId) {
		return personService.deletePerson(personId);
	}
	
	@DeleteMapping("/accountDelete/{accountId}")
	public String deleteAccount(@PathVariable("accountId") Long accountId) {
		return accountService.deleteAccount(accountId);
	}
	
	@PutMapping("/personUpdate")
	public String updatePerson(@RequestBody Person person) {
		return personService.updatePerson(person);
	}
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file){
		return personService.uploadPerson(file);
	}
	
	@PutMapping("/addDetail/{fileName}/{personId}")
	public ResponseEntity<String> appedFile(@PathVariable("fileName") String fileName , @PathVariable("personId") Long personId){
		return personService.addDetails(fileName, personId);
	}
	
	@GetMapping("/download/{fileName}")
	public ResponseEntity<Object> downloadFile(@PathVariable("fileName") String fileName){
		return personService.downloadFile(fileName);
	}
} 
