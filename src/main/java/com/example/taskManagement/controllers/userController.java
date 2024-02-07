package com.example.taskManagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.taskManagement.Exceptions.UserRegistrationException;
import com.example.taskManagement.models.userDto;
import com.example.taskManagement.services.userService;

@RestController
@RequestMapping("/api/user/")
public class userController {
	@Autowired
	userService user;

	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody userDto userDTO) {
		boolean status = user.userLogin(userDTO);
		if (status)
			return ResponseEntity.ok("login successful");
		else
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("user not found or incorrect password");
	}

	@PostMapping("/register")
	public ResponseEntity<String> userRegister(@RequestBody userDto userDTO) {

		boolean status = user.registerUser(userDTO);
		if (status)
			return ResponseEntity.ok("registered");
		else

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("username already exists");

	}

}
