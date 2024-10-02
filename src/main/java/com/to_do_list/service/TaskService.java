package com.to_do_list.service;

import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.entity.Status;

import java.util.List;

public interface TaskService {
    List<TaskDTO> getAllTasks();
    List<TaskDTO> findByStatus(Status status);
    TaskDTO getById(Long id);
    TaskDTO createTask(TaskDTO taskDTO);
    TaskDTO updateTask(Long id, TaskDTO taskDTO);
    void deleteTask(Long id);

}
