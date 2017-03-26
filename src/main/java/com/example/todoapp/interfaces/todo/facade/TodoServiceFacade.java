package com.example.todoapp.interfaces.todo.facade;

import com.example.todoapp.interfaces.todo.facade.dto.ProjectDto;
import com.example.todoapp.interfaces.todo.facade.dto.TaskDto;

import java.util.List;

/**
 * Todo管理サービスファサード
 * Created by d_akihiro on 2017/03/04.
 */
public interface TodoServiceFacade {
    /**
     * デフォルトプロジェクト
     * @return プロジェクトDto
     */
    ProjectDto defaultProject();

    /**
     * プロジェクト一覧取得
     * @param userId ユーザID
     * @return プロジェクトDtoリスト
     */
    List<ProjectDto> getProjectList(long userId);

    /**
     * プロジェクト取得
     * @param projectId プロジェクトID
     * @return プロジェクトDto
     */
    ProjectDto getProject(long projectId);

    /**
     * プロジェクト作成
     * @param projectDto プロジェクトDto
     * @return プロジェクトDto
     */
    ProjectDto createProject(ProjectDto projectDto);

    /**
     * プロジェクト更新
     * @param projectDto プロジェクトID
     * @return プロジェクトDto
     */
    ProjectDto updateProject(ProjectDto projectDto);

    /**
     * プロジェクト削除
     * @param projectId プロジェクトID
     */
    void deleteProject(long projectId);

    /**
     * デフォルトタスク
     * @return タスクDto
     */
    TaskDto defaultTask();

    /**
     * タスク一覧取得
     * @param projectId プロジェクトID
     * @return タスクDtoリスト
     */
    List<TaskDto> getTaskList(long projectId);

    /**
     * タスク取得
     * @param taskId タスクID
     * @return タスクDto
     */
    TaskDto getTask(long taskId);

    /**
     * タスク作成
     * @param taskDto タスクDto
     * @return タスクDto
     */
    TaskDto createTask(TaskDto taskDto);

    /**
     * タスク更新
     * @param taskDto タスクDto
     * @return タスクDto
     */
    TaskDto updateTask(TaskDto taskDto);

    /**
     * タスク完了取り消し
     * @param taskId タスクID
     */
    void updateTaskUnfinished(long taskId);

    /**
     * タスク完了
     * @param taskId タスクID
     */
    void updateTaskFinished(long taskId);

    /**
     * タスク削除
     * @param taskId タスクID
     */
    void deleteTask(long taskId);
}
