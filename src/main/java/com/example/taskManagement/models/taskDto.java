package com.example.taskManagement.models;

import java.time.LocalDate;

import org.apache.tomcat.util.http.parser.Priority;

public class taskDto {
	 private String title;
	    private String description;
	    private String status;
	    private LocalDate dueDate;
	    private Priority priority;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public LocalDate getDueDate() {
			return dueDate;
		}
		public void setDueDate(LocalDate dueDate) {
			this.dueDate = dueDate;
		}
		public Priority getPriority() {
			return priority;
		}
		public void setPriority(Priority priority) {
			this.priority = priority;
		}
		
	    
	    
}
