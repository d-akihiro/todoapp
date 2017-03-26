package com.example.todoapp.domain.model.project;

import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * プロジェクトリポジトリ
 * Created by d_akihiro on 2017/02/21.
 */
public interface ProjectRepository {

    /**
     * ID検索
     * @param id プロジェクトID
     * @return プロジェクト
     */
    Project findById(Long id);

    /**
     * オーナーユーザ検索
     * @param id ユーザID
     * @return プロジェクトリスト
     */
    List<Project> findByUserId(Long id);

    /**
     * 永続化
     * @param project プロジェクト
     * @return プロジェクト
     */
    Project save(@Validated Project project);

    /**
     * 永続化削除
     * @param id プロジェクトID
     */
    void delete(Long id);
}
