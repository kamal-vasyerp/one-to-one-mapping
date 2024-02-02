package com.practise.mapping.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long personId;
	
	private String name;
	private String gender;
	private int age;
	private String email;
	
	@OneToOne
	private Account account;
	
}
