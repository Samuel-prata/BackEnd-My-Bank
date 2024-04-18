package com.silver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.silver.dto.DataLogin;
import com.silver.model.User;
import com.silver.repository.UserRepository;

@Service
public class LoginService {

	@Autowired
	private UserRepository repository;
	
	public Optional<User> loginValidation(DataLogin data){
		
		var user = repository.getReferenceByEmail(data.email());
		
		if(user.getPassword().equals(data.password())) {
			return Optional.of(user);
		}
		return Optional.empty();
		
	}
	
}
