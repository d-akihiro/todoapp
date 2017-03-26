package com.example.todoapp.interfaces.todo.facade.impl;

import com.example.todoapp.application.service.ProjectService;
import com.example.todoapp.application.service.TaskService;
import com.example.todoapp.application.service.UserService;
import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.domain.model.task.Task;
import com.example.todoapp.interfaces.todo.facade.TodoServiceFacade;
import com.example.todoapp.interfaces.todo.facade.dto.ProjectDto;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;
import com.example.todoapp.interfaces.todo.facade.impl.assembler.ProjectDtoAssembler;
import com.example.todoapp.interfaces.todo.facade.impl.assembler.TaskDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Todo管理サービスファサード(実装)
 * Created by d_akihiro on 2017/03/20.
 */
@Component
public class TodoServiceFacadeImpl implements TodoServiceFacade {
    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TaskService taskService;

    /**
     * プロジェクトからプロジェクトDto組み立て
     * @param project プロジェクト
     * @return プロジェクトDto
     */
    private ProjectDto assemblerProjectDto(Project project){
        ProjectDtoAssembler projectDtoAssembler = new ProjectDtoAssembler();
        return projectDtoAssembler.toDto(project);
    }

    /**
     * タスクからタスクDto組み立て
     * @param task タスク
     * @return タスクDto
     */
    private TaskDto assemblerTaskDto(Task task){
        TaskDtoAssembler taskDtoAssembler = new TaskDtoAssembler();
        return taskDtoAssembler.toDto(task);
    }

    @Override
    public ProjectDto defaultProject() {
        ProjectDtoAssembler projectDtoAssembler = new ProjectDtoAssembler();
        return projectDtoAssembler.defaultProject();
    }

    @Override
    public List<ProjectDto> getProjectList(long userId) {
        List<Project> projectList = projectService.getProjectList(userId);

        List<ProjectDto> projectDtoList = new ArrayList<ProjectDto>();
        if(projectList != null){
            for(Project project : projectList){
                projectDtoList.add(assemblerProjectDto(project));
            }
        }
        return projectDtoList;
    }

    @Override
    public ProjectDto getProject(long projectId) {
        return assemblerProjectDto(projectService.getProject(projectId));
    }


    @Override
    public ProjectDto createProject(ProjectDto projectDto) {
        Project project = projectService.createProject(
                projectDto.getUserId(),
                projectDto.getName(),
                projectDto.getDescription());
        return assemblerProjectDto(project);
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDto) {
        Project project = projectService.updateProject(
                projectDto.getId(),
                projectDto.getName(),
                projectDto.getDescription());
        return assemblerProjectDto(project);
    }

    @Override
    public void deleteProject(long projectId) {
        projectService.deleteProject(projectId);
    }

    @Override
    public TaskDto defaultTask() {
        TaskDtoAssembler taskDtoAssembler = new TaskDtoAssembler();
        return taskDtoAssembler.defaultTask();
    }

    @Override
    public List<TaskDto> getTaskList(long projectId) {
        List<Task> taskList = taskService.getTaskList(projectId);
        List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
        if(taskList != null && !taskList.isEmpty()){
            for(Task task : taskList){
                taskDtoList.add(assemblerTaskDto(task));
            }
        }
        return taskDtoList;
    }

    @Override
    public TaskDto getTask(long taskId) {
        return assemblerTaskDto(taskService.getTask(taskId));
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        Task task = taskService.createTask(
                taskDto.getProjectId(),
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getDeadline());
        return assemblerTaskDto(task);
    }

    @Override
    public TaskDto updateTask(TaskDto taskDto) {
        Task task = taskService.updateTask(
                taskDto.getId(),
                taskDto.getName(),
                taskDto.getDescription(),
                taskDto.getDeadline());
        return assemblerTaskDto(task);
    }

    @Override
    public void updateTaskUnfinished(long taskId) {
        taskService.unfinishedTask(taskId);
    }

    @Override
    public void updateTaskFinished(long taskId) {
        taskService.finishedTask(taskId);
    }

    @Override
    public void deleteTask(long taskId) {
        taskService.deleteTask(taskId);
    }
}
