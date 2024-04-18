package com.silver.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.silver.dto.DataTransfer;
import com.silver.repository.UserRepository;

@Service
public class OperationService {
	
	@Autowired
	private UserRepository repository;
	
	public String transfer (DataTransfer data) {
	validateAccountExists(data.sender(), data.receiver());
	
	return transferOperations(data.sender(), data.receiver(), data.amount());
	}
	
	private String validateAccountExists(Long senderId, Long receiverId) {
		if(!repository.existsById(senderId) || !repository.existsById(receiverId))
		return "Dados Não encontrados";
		
		return "ok";
	}
	
	private String transferOperations (Long senderId, Long receiverId, Double amount) {
		var sender = repository.getReferenceById(senderId);
		var receiver = repository.getReferenceById(receiverId);
		
		if(sender.getAmount() <= 0 || sender.getAmount() < amount) 
			return "Saldo insuficiente";
		
		sender.setAmount(sender.getAmount() - amount);
		receiver.setAmount(receiver.getAmount() + amount);
		repository.save(sender);
		repository.save(receiver);
		
		return "Operação realizada com sucesso";
	}
	
	
}
