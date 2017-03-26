package com.example.todoapp.interfaces.todo.facade.impl.assembler;

import com.example.todoapp.domain.model.project.Project;
import com.example.todoapp.interfaces.todo.facade.dto.ProjectDto;
import com.example.todoapp.domain.model.task.Task;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;

import java.util.ArrayList;
import java.util.List;

/**
 * プロジェクトDto組み立てクラス
 * Created by d_akihiro on 2017/03/20.
 */
public class ProjectDtoAssembler {
    /**
     * デフォルトプロジェクト組み立て
     * @return プロジェクトDto
     */
    public ProjectDto defaultProject(){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setId(0);
        projectDto.setName("");
        projectDto.setDescription("");
        projectDto.setUserId(0);
        projectDto.setTaskDtoList(null);
        return projectDto;
    }

    /**
     * プロジェクトからプロジェクトDto組み立て
     * @param project プロジェクト
     * @return プロジェクトDto
     */
    public ProjectDto toDto(Project project){
        if(project == null) return null;
        TaskDtoAssembler taskDtoAssembler = new TaskDtoAssembler();
        List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
        if(project.getTaskList() != null) {
            for (Task task : project.getTaskList()) {
                TaskDto taskDto = taskDtoAssembler.toDto(task);
                taskDtoList.add(taskDto);
            }
        }

        return createDto(project.getId(),
                project.getName(),
                project.getDescription(),
                taskDtoList,
                project.getOwner().getId());
    }

    /**
     * プロジェクトDto作成
     * @param id プロジェクトID
     * @param name プロジェクト名
     * @param description 注釈
     * @param taskDtoList タスクDtoリスト
     * @param userId ユーザID
     * @return プロジェクトDto
     */
    public ProjectDto createDto(long id,
                                String name,
                                String description,
                                List<TaskDto> taskDtoList,
                                long userId){
        ProjectDto projectDto = defaultProject();
        projectDto.setId(id);
        projectDto.setName(name);
        projectDto.setDescription(description);
        projectDto.setTaskDtoList(taskDtoList);
        projectDto.setUserId(userId);
        return projectDto;
    }
}
