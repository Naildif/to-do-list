package com.to_do_list.DTO;

import com.to_do_list.entity.Category;
import com.to_do_list.entity.Priority;
import com.to_do_list.entity.Status;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskDTO {
    private Long id;
    @NotNull(message = "(˶˃ᆺ˂˶) \nTask title cannot be empty.")
    @Size(min = 1, max = 100, message = "(｡•́︿•̀｡) \nTask title must have between 1 and 100 characters.")
    private String taskName;
    @Size(max = 500, message = "(˶˃ᆺ˂˶) \nDescription must not exceed 500 characters.")
    private String description;
    private LocalDateTime createdDate;
    private LocalDateTime finishDate;
    private LocalDate dueDate;
    private Status status;
    private Priority priority;
    private Long categoryId;

    public TaskDTO(){}
    public TaskDTO(String taskName, String description, LocalDateTime createdDate, LocalDateTime finishDate, LocalDate dueDate, Status status, Priority priority, Long categoryId){
        this.taskName = taskName;
        this.dueDate=dueDate;
        this.createdDate=createdDate;
        this.finishDate=finishDate;
        this.status=status;
        this.priority=priority;
        this.categoryId =categoryId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
