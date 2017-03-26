package com.example.todoapp.interfaces.user.facade;

import com.example.todoapp.interfaces.user.facade.dto.UserDto;

import java.util.List;

/**
 * ユーザ管理サービスファサード
 * Created by d_akihiro on 2017/03/04.
 */
public interface UserServiceFacade {
    /**
     * デフォルトユーザ
     * @return ユーザ
     */
    UserDto defaultUser();

    /**
     * 全ユーザ取得
     * @return ユーザ一覧
     */
    List<UserDto> getUserList();

    /**
     * ユーザ取得
     * @param id ユーザID
     * @return ユーザ
     */
    UserDto getUser(long id);

    /**
     * ユーザ作成
     * @param userDto ユーザ
     * @return ユーザ
     */
    UserDto createUser(UserDto userDto);

    /**
     * ユーザ更新
     * @param userDto ユーザ
     * @return ユーザ
     */
    UserDto updateUser(UserDto userDto);

    /**
     * ユーザ削除
     * @param id ユーザID
     */
    void deleteUser(long id);
}
