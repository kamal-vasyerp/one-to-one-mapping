package com.practise.mapping.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Account {

	@Id
	long accountId;
	
	@Column(name = "bank_name")
	String bankName;
	
	String branch;
}
