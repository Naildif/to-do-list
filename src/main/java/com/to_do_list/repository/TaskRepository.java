package com.to_do_list.repository;

import com.to_do_list.DTO.TaskDTO;
import com.to_do_list.entity.Status;
import com.to_do_list.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByStatus(Status status);

}
