package com.example.todoapp.infrastructure.persistence.jpa;

import com.example.todoapp.domain.model.task.TaskRepository;
import com.example.todoapp.domain.model.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by d_akihiro on 2017/02/23.
 */
public interface JpaTaskRepository extends TaskRepository, JpaRepository<Task, Long> {
}
