package com.to_do_list.service;

import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.entity.Category;
import com.to_do_list.entity.Status;
import com.to_do_list.entity.Task;
import com.to_do_list.exception.CategoryNotFoundException;
import com.to_do_list.exception.StatusNotFoundException;
import com.to_do_list.exception.TaskNotFoundException;
import com.to_do_list.repository.CategoryRepository;
import com.to_do_list.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    private final TaskRepository taskRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           CategoryRepository categoryRepository){
        this.taskRepository=taskRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public List<TaskDTO> getAllTasks() {
        List<Task> tasks= taskRepository.findAll();
        return tasks.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO getById(Long id) {
        Optional<Task> task = taskRepository.findById(id);
        return task.map(this::convertEntityToDTO)
        .orElseThrow(()->new TaskNotFoundException("The task "+id+ " was not found\n૮꒰◞ ˕ ◟ ྀི꒱ა"));
    }

    @Override
    public List<TaskDTO> findByStatus(Status status) {
        List<Task> taskStatus = taskRepository.findByStatus(status);
        if (taskStatus.isEmpty()){
            throw new StatusNotFoundException("No tasks in this status\n(˶ᵔᵕᵔ˶) \uD83D\uDC95");
        }
        return taskStatus.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    @Override
    public TaskDTO createTask(TaskDTO taskDTO) {
        Task newTask = convertDTOToEntity(taskDTO);
        Task savedTask = taskRepository.save(newTask);
        return convertEntityToDTO(savedTask);
    }

    @Override
    public TaskDTO updateTask(Long id, TaskDTO taskDTO) {
        Task oldTask = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("The task "+id+
                        " was not found\n૮꒰◞ ˕ ◟ ྀི꒱ა"));
        oldTask.setTaskName(taskDTO.getTaskName());
        oldTask.setDescription(taskDTO.getDescription());
        oldTask.setDueDate(taskDTO.getDueDate());
        oldTask.setStatus(taskDTO.getStatus());
        oldTask.setStatus(taskDTO.getStatus());
        oldTask.setPriority(taskDTO.getPriority());
        if (taskDTO.getCategoryId() != null){
            Category category = categoryRepository.findById(taskDTO.getCategoryId())
                    .orElseThrow(()-> new CategoryNotFoundException("Category with ID " + taskDTO.getCategoryId()
                            + " not found (｡•́︿•̀｡)"));
            oldTask.setCategory(category);
        }
        Task updatedTask = taskRepository.save(oldTask);
        return convertEntityToDTO(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(()-> new TaskNotFoundException("The task" + id + "does not exist \n૮꒰◞ ˕ ◟ ྀི꒱ა"));
        taskRepository.delete(task);

    }

    public TaskDTO convertEntityToDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTaskName(task.getTaskName());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setDueDate(task.getDueDate());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setPriority(task.getPriority());
        if (task.getCategory() != null) {
            taskDTO.setCategoryId(task.getCategory().getId());
        }
        return taskDTO;
    }

    public Task convertDTOToEntity(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());
        task.setDueDate(taskDTO.getDueDate());
        task.setStatus(taskDTO.getStatus());
        task.setPriority(taskDTO.getPriority());
        if(taskDTO.getCategoryId() != null){
            Category category = categoryRepository.findById(taskDTO.getCategoryId())
                    .orElseThrow(() -> new CategoryNotFoundException("Category with ID " + taskDTO.getCategoryId() + " not found (｡•́︿•̀｡)" ));
            task.setCategory(category);
        }
        return task;
    }
}
