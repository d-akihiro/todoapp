package com.example.todoapp.application.service;

import com.example.todoapp.domain.model.project.Project;

import java.util.List;

/**
 * プロジェクト管理サービス
 * Created by d_akihiro on 2017/02/26.
 */
public interface ProjectService {
    /**
     * プロジェクト取得
     * @param id プロジェクトID
     * @return プロジェクト
     */
    Project getProject(long id);

    /**
     * プロジェクト一覧
     * @param userId プロジェクトオーナーユーザID
     * @return プロジェクトリスト
     */
    List<Project> getProjectList(long userId);

    /**
     * プロジェクト作成
     * @param userId プロジェクトオーナーユーザID
     * @param name プロジェクト名
     * @param description 注釈
     * @return プロジェクト
     */
    Project createProject(long userId, String name, String description);

    /**
     * プロジェクト更新
     * @param id プロジェクトID
     * @param name プロジェクト名
     * @param description 注釈
     * @return プロジェクト
     */
    Project updateProject(long id, String name, String description);

    /**
     * プロジェクト削除
     * @param id プロジェクトID
     */
    void deleteProject(long id);
}
