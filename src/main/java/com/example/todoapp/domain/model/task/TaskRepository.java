package com.example.todoapp.domain.model.task;

import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * タスクリポジトリ
 * Created by d_akihiro on 2017/02/21.
 */
public interface TaskRepository {

    /**
     * ID検索
     * @param id タスクID
     * @return タスク
     */
    Task findById(Long id);

    /**
     * プロジェクトID検索
     * @param id プロジェクトID
     * @return タスクリスト
     */
    List<Task> findByProjectId(Long id);

    /**
     * 永続化
     * @param task タスク
     * @return タスク
     */
    Task save(@Validated Task task);

    /**
     * 永続化削除
     * @param id タスクID
     */
    void delete(Long id);
}
