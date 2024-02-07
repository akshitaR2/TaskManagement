package com.example.taskManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.taskManagement.models.taskDto;
import com.example.taskManagement.models.taskEntity;
import com.example.taskManagement.repos.taskRepo;
import com.example.taskManagement.services.taskService;

@Controller
@RequestMapping("/api/task")
public class taskController {

	@Autowired
	private taskService taskService;
	@Autowired
	private taskRepo taskRepository;

	@GetMapping
	public List<taskEntity> getAllTasks() {
		return taskRepository.getAllTasks();
	}

	@PostMapping
	public ResponseEntity<taskEntity> createTask(@RequestBody taskDto taskDTO) {
		taskEntity createdTask = taskService.createTask(taskDTO);
		return new ResponseEntity<>(createdTask, HttpStatus.CREATED);
	}

	@PutMapping("/{taskId}")
	public ResponseEntity<taskEntity> updateTask(@PathVariable Long taskId, @RequestBody taskDto taskDTO) {
		taskEntity updatedTask = taskService.updateTask(taskId, taskDTO);
		return new ResponseEntity<>(updatedTask, HttpStatus.OK);
	}

}
