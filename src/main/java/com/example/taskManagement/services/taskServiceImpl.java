package com.example.taskManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.taskManagement.Exceptions.taskNotFoundException;
import com.example.taskManagement.models.taskDto;
import com.example.taskManagement.models.taskEntity;
import com.example.taskManagement.repos.taskRepo;

@Service
public class taskServiceImpl implements taskService{

    @Autowired
    private taskRepo taskRepository;

    public List<taskEntity> getAllTasks() {
        return taskRepository.findAll();
    }

    public taskEntity createTask(taskDto taskDTO) {
    	taskEntity task = new taskEntity();
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setPriority(taskDTO.getPriority());
        task.setStatus(taskDTO.getStatus()); 

        return taskRepository.save(task);
    }

    public taskEntity updateTask(Long taskId, taskDto taskDTO) {
    	taskEntity existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new taskNotFoundException("Task not found"));

        existingTask.setTitle(taskDTO.getTitle());
        existingTask.setDescription(taskDTO.getDescription());
        existingTask.setDueDate(taskDTO.getDueDate());
        existingTask.setPriority(taskDTO.getPriority());

        return taskRepository.save(existingTask);
    }
    
  
    
    
    
    
    
    
    
    
}
