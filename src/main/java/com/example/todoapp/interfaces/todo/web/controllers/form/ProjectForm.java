package com.example.todoapp.interfaces.todo.web.controllers.form;

import com.example.todoapp.interfaces.todo.facade.dto.ProjectDto;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;
import com.example.todoapp.interfaces.todo.facade.impl.assembler.ProjectDtoAssembler;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * プロジェクト編集form
 * Created by d_akihiro on 2017/03/20.
 */
@Data
@NoArgsConstructor
public class ProjectForm implements Serializable{

    private static final long serialVersionUID = 3858288084938914560L;

    /**
     * プロジェクトID
     */
    private long id;

    /**
     * プロジェクト名
     */
    @NotBlank
    private String name;

    /**
     * 注釈
     */
    private String description;

    /**
     * プロジェクトのタスク一覧
     */
    private List<TaskForm> taskFormList;

    /**
     * プロジェクトの未完了タスク数
     */
    private int unfinishedTaskCount;

    /**
     * プロジェクトDtoによる更新
     * @param projectDto プロジェクトDto
     */
    public void update(ProjectDto projectDto){
        id = projectDto.getId();
        name = projectDto.getName();
        description = projectDto.getDescription();

        taskFormList = new ArrayList<TaskForm>();
        int count = 0;
        if(projectDto.getTaskDtoList() != null){
            for(TaskDto taskDto : projectDto.getTaskDtoList()){
                TaskForm taskForm = new TaskForm();
                taskForm.update(taskDto);
                taskFormList.add(taskForm);
                if(!taskDto.isFinished()){
                    count++;
                }
            }
        }
        unfinishedTaskCount = count;
    }

    /**
     * プロジェクトDto作成
     * @return プロジェクトDto
     */
    public ProjectDto createDto(){
        List<TaskDto> taskDtoList = new ArrayList<TaskDto>();
        if(taskFormList != null){
            for(TaskForm taskForm : taskFormList){
                taskDtoList.add(taskForm.createDto());
            }
        }
        ProjectDtoAssembler projectDtoAssembler = new ProjectDtoAssembler();
        return projectDtoAssembler.createDto(
                id,
                name,
                description,
                taskDtoList,
                0
        );
    }
}
