package com.example.taskManagement.services;

import com.example.taskManagement.models.taskDto;
import com.example.taskManagement.models.taskEntity;

public interface taskService {
	
	public  taskEntity createTask(taskDto taskDTO);
	public taskEntity updateTask(Long taskId, taskDto taskDTO);
		
}
