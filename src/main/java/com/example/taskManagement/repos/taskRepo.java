package com.example.taskManagement.repos;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.taskManagement.models.taskEntity;
import com.example.taskManagement.models.userEntity;

public interface taskRepo extends JpaRepository<taskEntity,Long> {

	 List<taskEntity> getAllTasks();
}
