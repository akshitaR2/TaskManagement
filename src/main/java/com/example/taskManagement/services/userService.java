package com.example.taskManagement.services;

import com.example.taskManagement.models.userDto;

public interface userService {

	public boolean registerUser(userDto userDTO);
	public boolean userLogin(userDto userDTO);
}
