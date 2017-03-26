package com.example.todoapp.interfaces.security.facade.impl.assembler;

import com.example.todoapp.interfaces.security.facade.dto.UserDetailsDto;
import com.example.todoapp.domain.model.user.User;

/**
 * 認証ユーザ組み立て
 * Created by d_akihiro on 2017/03/19.
 */
public class UserDetailsDtoAssembler {
    /**
     * ユーザから認証ユーザを組み立てる
     * @param user ユーザ
     * @return 認証ユーザ
     */
    public UserDetailsDto toDto(User user){
        return new UserDetailsDto(user.getId(), user.getUsername(), user.getPassword());
    }
}
