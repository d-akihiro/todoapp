package com.example.todoapp.infrastructure.persistence.jpa;

import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.domain.model.project.ProjectRepository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by d_akihiro on 2017/02/23.
 */
public interface JpaProjectRepository extends ProjectRepository, JpaRepository<Project, Long> {
}
