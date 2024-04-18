package com.silver.model;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@CPF
	@Column(unique = true, nullable = false)
	private String cpf;

	@Email
	@Column(unique = true, nullable = false)
	private String email;
	
	@Size(min = 5)
	@Column(nullable = false)
	private String password; 

	private String photo;

	private Double amount;

	// |----------------------------------Constructor--------------------------------|
	public User() {

	}

	public User(String name, String cpf, String photo, String email,String password, Double amount) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.photo = photo;
		this.password = password;
		this.amount = amount;
	}

//	|-----------------------------------------Getters and Setters------------------------------------|
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public Double getAmount() {
		return amount;
	}

	public String getCpf() {
		return cpf;
	}

	public String getPhoto() {
		return photo;
	}
	
	public String getPassword() {
		return password;
	}

	public void setAmount(Double amount) {
		if (amount > 0)
			this.amount = amount;
	}
}
