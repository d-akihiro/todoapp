package com.example.todoapp.application.service;

import com.example.todoapp.domain.model.task.Task;

import java.time.LocalDateTime;
import java.util.List;

/**
 * タスク管理サービス
 * Created by d_akihiro on 2017/02/26.
 */
public interface TaskService {
    /**
     * タスク取得
     * @param id タスクID
     * @return タスク
     */
    Task getTask(long id);

    /**
     * タスク一覧取得
     * @param projectId プロジェクトID
     * @return タスクリスト
     */
    List<Task> getTaskList(long projectId);

    /**
     * タスク作成
     * @param projectId プロジェクトID
     * @param name タスク名
     * @param description 注釈
     * @param deadline 期限
     * @return タスク
     */
    Task createTask(long projectId, String name, String description, LocalDateTime deadline);

    /**
     * タスク更新
     * @param id タスクID
     * @param name タスク名
     * @param description 注釈
     * @param deadline 期限
     * @return タスク
     */
    Task updateTask(long id, String name, String description, LocalDateTime deadline);

    /**
     * タスク削除
     * @param id タスクID
     */
    void deleteTask(long id);

    /**
     * タスク完了取り消し
     * @param id タスクID
     */
    void unfinishedTask(long id);

    /**
     * タスク完了
     * @param id タスクID
     */
    void finishedTask(long id);
}
