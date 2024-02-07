package com.example.taskManagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.taskManagement.Exceptions.UserRegistrationException;
import com.example.taskManagement.Exceptions.userNotFoundException;
import com.example.taskManagement.models.userDto;
import com.example.taskManagement.models.userEntity;
import com.example.taskManagement.repos.userRepo;

@Service
public class userServiceImpl implements userService {
	
	@Autowired
	userRepo userRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	 public boolean registerUser(userDto userDTO){
		 boolean status=false;
		 if(userRepository.findByUsernameIgnoreCase(userDTO.getUsername()).isPresent()) {	 
			 throw new UserRegistrationException("Username is already taken");	
		 }
		 else {
	        userEntity user = new userEntity();
	        user.setUsername(userDTO.getUsername());
	        user.setEmail(userDTO.getEmail());
	        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
	        user.setRole("ROLE_USER"); // Assign a default role
	        userRepository.save(user);
	        status =true;
		 }
	        return status;
	    }
	 
	 public boolean userLogin(userDto userDTO){
		userEntity user = userRepository.findByUsernameIgnoreCase(userDTO.getUsername())
		.orElseThrow(()-> new userNotFoundException("user not found"));
		if(passwordEncoder.matches(user.getPassword(), userDTO.getPassword())) {
			 return true;
		 }
		 else 
			 return false;
	 }
	 
	
}
