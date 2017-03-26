package com.example.todoapp.application.service;

import com.example.todoapp.domain.model.user.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 * ユーザ管理サービス
 * Created by d_akihiro on 2017/02/21.
 */
public interface UserService {
    /**
     * パスワードエンコーダー
     * @return エンコーダー
     */
    PasswordEncoder getPasswordEncoder();

    /**
     * ユーザ名によるユーザ検索
     * @param username ユーザ名
     * @return ユーザ
     */
    User getUserByUsername(String username);

    /**
     * ユーザIDによるユーザ検索
     * @param id ユーザID
     * @return ユーザ
     */
    User getUser(long id);

    /**
     * ユーザ一覧取得
     * @return ユーザ一覧
     */
    List<User> getUserList();

    /**
     * ユーザ作成
     * @param username ユーザ名
     * @param password パスワード
     * @return 新規ユーザ
     */
    User createUser(String username, String password);

    /**
     * ユーザ更新
     * @param id ユーザID
     * @param username ユーザ名
     * @param password パスワード
     * @return ユーザ
     */
    User updateUser(long id, String username, String password);

    /**
     * ユーザ削除
     * @param id ユーザID
     */
    void deleteUser(long id);
}
