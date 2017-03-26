package com.example.todoapp.interfaces.todo.web.controllers.form;

import com.example.todoapp.interfaces.todo.facade.impl.assembler.TaskDtoAssembler;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * タスク編集フォーム
 * Created by d_akihiro on 2017/03/20.
 */
@Data
@NoArgsConstructor
public class TaskForm implements Serializable {
    private static final long serialVersionUID = -1883128112660656719L;
    /**
     * タスクID
     */
    private long id;

    /**
     * タスク名
     */
    @NotBlank
    private String name;

    /**
     * 注釈
     */
    private String description;

    /**
     * タスク期限
     */
    @DateTimeFormat(pattern="yyyy-MM-dd'T'HH:mm")
    private LocalDateTime deadline;

    /**
     * タスク完了済みフラグ
     */
    private boolean isFinished;

    /**
     * タスク期限切れフラグ
     */
    private boolean isExpired;

    /**
     * プロジェクトID
     */
    private long projectId;

    /**
     * タスクDtoによる更新
     * @param taskDto タスクDto
     */
    public void update(TaskDto taskDto){
        id = taskDto.getId();
        name = taskDto.getName();
        description = taskDto.getDescription();
        deadline = taskDto.getDeadline();
        isFinished = taskDto.isFinished();
        isExpired = taskDto.isExpired();
        projectId = taskDto.getProjectId();
    }

    /**
     * タスクDto作成
     * @return タスクDto
     */
    public TaskDto createDto(){
        TaskDtoAssembler taskDtoAssembler = new TaskDtoAssembler();
        return taskDtoAssembler.createDto(
                id,
                name,
                description,
                deadline,
                isFinished,
                isExpired,
                projectId,
                0
        );
    }

    /**
     * タスク完了済みフラグの取得(thymeleaf命名規則に対応)
     * @return
     */
    public boolean getIsFinished(){
        return isFinished;
    }

    /**
     * タスク期限切れフラグの取得(thymeleaf命名規則に対応)
     * @return
     */
    public boolean getIsExpired(){
        return isExpired;
    }

}
