package com.silver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.silver.dto.DataLogin;
import com.silver.dto.DataTransfer;
import com.silver.dto.DataUser;
import com.silver.model.User;
import com.silver.repository.UserRepository;
import com.silver.service.LoginService;
import com.silver.service.OperationService;

@RestController
@RequestMapping("/operations")
public class OperationController {

	@Autowired
	private UserRepository repository;
	
	@Autowired
	private OperationService service;
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping
	public List<User> showData() {
		return repository.findAll();
	}
	
	@PostMapping
	public User save(@RequestBody DataUser data) {
		return repository.save(new User(data.name(),data.cpf(), data.photo() ,data.email(), data.password(), data.amount()));
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody DataLogin data) {
		return loginService.loginValidation(data)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body("Login realizado com sucesso"))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Dados incorretos ou inexistentes"));
	}
	
	@PostMapping("/transfer")
	public String transfer(@RequestBody DataTransfer data) {
		return service.transfer(data);
	}
	
}
