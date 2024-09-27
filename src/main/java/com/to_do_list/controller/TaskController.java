package com.to_do_list.controller;

import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.entity.Status;
import com.to_do_list.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;
    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){
        List<TaskDTO> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TaskDTO> getById(@PathVariable Long id){
        TaskDTO task = taskService.getById(id);
           return ResponseEntity.ok(task);
    }
    @GetMapping("/search")
    public ResponseEntity<List<TaskDTO>> findByStatus (@RequestParam Status status){
        List<TaskDTO> taskStatus = taskService.findByStatus(status);
        return ResponseEntity.ok(taskStatus);
    }
    @PostMapping
    public ResponseEntity<TaskDTO> createTask(@Valid @RequestBody TaskDTO taskDTO){
        TaskDTO newTask = taskService.createTask(taskDTO);
        URI location = URI.create(String.format("/task%d", newTask.getId()));
        return ResponseEntity.created(location).body(newTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDTO> updateTask (@Valid @RequestBody Long id, TaskDTO taskDTO){
        TaskDTO updatedTask = taskService.updateTask(id, taskDTO);
        return ResponseEntity.ok(updatedTask);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
