package com.example.todoapp.application.service.impl;

import com.example.todoapp.application.service.ProjectService;
import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.domain.model.project.ProjectRepository;
import com.example.todoapp.domain.model.user.User;
import com.example.todoapp.domain.model.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * プロジェクト管理サービス(実装)
 * Created by d_akihiro on 2017/02/26.
 */
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Project getProject(long id) {
        Project project = projectRepository.findById(id);
        return project;
    }

    @Override
    public List<Project> getProjectList(long userId) {
        return projectRepository.findByUserId(userId);
    }

    @Override
    public Project createProject(long userId, String name, String description) {
        Project project = null;
        User user = userRepository.findById(userId);
        if(user != null) {
            project = new Project();
            project.setName(name);
            project.setDescription(description);
            project.setOwner(user);
            project = saveProject(project);
        }
        return project;
    }

    @Override
    public Project updateProject(long id, String name, String description) {
        Project project = projectRepository.findById(id);
        project.setName(name);
        project.setDescription(description);
        project = saveProject(project);

        return project;
    }

    @Override
    public void deleteProject(long id) {
        projectRepository.delete(id);
    }

    private Project saveProject(Project project){
        Project result = null;
        try{
            result = projectRepository.save(project);
        }
        catch (DataIntegrityViolationException ex){
            // 制約違反の場合は正常系とする
            result = null;
        }
        catch (Exception ex){
            throw ex;
        }
        return result;
    }
}
