package com.example.taskManagement.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskManagement.models.userEntity;


public interface userRepo extends JpaRepository<userEntity, Long> {
	
	 Optional<userEntity> findByUsernameIgnoreCase(String username);
	
	}


