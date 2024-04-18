package com.silver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.silver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	Boolean existsByEmail(String email);

	User getReferenceByEmail(String email);

}
