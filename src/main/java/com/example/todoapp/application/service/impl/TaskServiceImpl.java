package com.example.todoapp.application.service.impl;

import com.example.todoapp.application.service.TaskService;
import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.domain.model.project.ProjectRepository;
import com.example.todoapp.domain.model.task.Task;
import com.example.todoapp.domain.model.task.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by d_akihiro on 2017/02/26.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Task getTask(long id) {
        Task task = taskRepository.findById(id);
        return task;
    }

    @Override
    public List<Task> getTaskList(long project_id) {
        List<Task> taskList = taskRepository.findByProjectId(project_id);
        return taskList;
    }

    @Override
    public Task createTask(long project_id, String name, String description, LocalDateTime deadline) {
        Project project = projectRepository.findById(project_id);

        Task task = null;
        if(project != null){
            task = new Task();
            task.setName(name);
            task.setDescription(description);
            task.setDeadline(deadline);
            task.setProject(project);
            task = saveTask(task);
        }

        return task;
    }

    @Override
    public Task updateTask(long id, String name, String description, LocalDateTime deadline) {
        Task task = taskRepository.findById(id);
        if(task != null){
            task.setName(name);
            task.setDescription(description);
            task.setDeadline(deadline);

            task = saveTask(task);
        }
        return task;
    }

    @Override
    public void deleteTask(long id) {
        Task task = taskRepository.findById(id);
        if(task != null){
            taskRepository.delete(id);
        }
    }

    @Override
    public void unfinishedTask(long id) {
        Task task = taskRepository.findById(id);
        if(task != null){
            task.updateUnfinished();
            saveTask(task);
        }
    }

    @Override
    public void finishedTask(long id) {
        Task task = taskRepository.findById(id);
        if(task != null){
            task.updateFinished();
            saveTask(task);
        }
    }

    /**
     * タスク保存処理
     * @param task タスク
     * @return 保存後タスク
     */
    private Task saveTask(Task task){
        Task result = null;
        try{
            result = taskRepository.save(task);
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
