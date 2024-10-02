package com.to_do_list;

import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.entity.Task;
import com.to_do_list.repository.CategoryRepository;
import com.to_do_list.repository.TaskRepository;
import com.to_do_list.service.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetById() {
        Task task = new Task();
        task.setId(1L);
        task.setTaskName("Test Task");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        TaskDTO result = taskService.getById(1L);

        assertEquals("Test Task", result.getTaskName());

        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateTask() {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskName("New Task");
        taskDTO.setDescription("Task Description");

        Task task = new Task();
        task.setTaskName(taskDTO.getTaskName());
        task.setDescription(taskDTO.getDescription());

        when(taskRepository.save(task)).thenReturn(task);

        TaskDTO result = taskService.createTask(taskDTO);

        assertEquals("New Task", result.getTaskName());
        assertEquals("Task Description", result.getDescription());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void testUpdateTask() {
        Long taskId = 1L;
        Task oldTask = new Task();
        oldTask.setId(1L);
        oldTask.setTaskName("Existing Task");

        TaskDTO updatedTaskDTO = new TaskDTO();
        updatedTaskDTO.setTaskName("updated Task");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(oldTask));

        when(taskRepository.save(oldTask)).thenReturn(oldTask);

        TaskDTO result = taskService.updateTask(taskId, updatedTaskDTO);

        assertEquals("updated Task", result.getTaskName());
        verify(taskRepository, times(1)).findById(taskId);
        verify(taskRepository, times(1)).save(oldTask);

    }
}