package com.example.todoapp.interfaces.todo.facade.impl.assembler;

import com.example.todoapp.domain.model.task.Task;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;

import java.time.LocalDateTime;

/**
 * タスクDto組み立てクラス
 * Created by d_akihiro on 2017/03/20.
 */
public class TaskDtoAssembler {
    /**
     * デフォルトタスク
     * @return タスクDto
     */
    public TaskDto defaultTask(){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(0);
        taskDto.setName("");
        taskDto.setDescription("");
        taskDto.setDeadline(null);
        taskDto.setExpired(false);
        taskDto.setFinished(false);
        taskDto.setProjectId(0);
        return taskDto;
    }

    /**
     * タスクからタスクDto組み立て
     * @param task タスク
     * @return タスクDto
     */
    public TaskDto toDto(Task task){
        if(task == null) return null;
        return createDto(
                task.getId(),
                task.getName(),
                task.getDescription(),
                task.getDeadline(),
                task.isFinished(),
                task.isExpired(),
                task.getProjectId(),
                task.getProject().getOwner().getId()
        );
    }

    /**
     * タスクDto作成
     * @param id タスクID
     * @param name タスク名
     * @param description 注釈
     * @param deadline 期限
     * @param isFinished 完了フラグ
     * @param isExpired 期限切れフラグ
     * @param projectId プロジェクトID
     * @param userId ユーザID
     * @return タスクDto
     */
    public TaskDto createDto(long id,
                             String name,
                             String description,
                             LocalDateTime deadline,
                             boolean isFinished,
                             boolean isExpired,
                             long projectId,
                             long userId){
        TaskDto taskDto = defaultTask();
        taskDto.setId(id);
        taskDto.setName(name);
        taskDto.setDescription(description);
        taskDto.setDeadline(deadline);
        taskDto.setFinished(isFinished);
        taskDto.setExpired(isExpired);
        taskDto.setProjectId(projectId);
        taskDto.setUserId(userId);

        return taskDto;
    }
}
