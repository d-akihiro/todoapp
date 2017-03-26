package com.example.todoapp.interfaces.user.facade.impl.assembler;

import com.example.todoapp.domain.model.user.User;
import com.example.todoapp.interfaces.user.facade.dto.UserDto;

/**
 * ユーザDto組み立てクラス
 * Created by d_akihiro on 2017/03/17.
 */
public class UserDtoAssembler {
    /**
     * ユーザからユーザDtoを組み立てる
     * @param user ユーザ
     * @return ユーザDto
     */
    public UserDto toDto(User user){
        if(user == null) return null;
        return createDto(user.getId(), user.getUsername(), "");
    }

    public UserDto createDto(long id, String username, String password){
        UserDto userDto = defaultUserDto();
        userDto.setId(id);
        userDto.setUsername(username);
        userDto.setPassword(password);
        return userDto;
    }

    /**
     * デフォルトユーザDto
     * @return ユーザDto
     */
    public UserDto defaultUserDto(){
        UserDto userDto = new UserDto();
        userDto.setId(0);
        userDto.setUsername("");
        userDto.setUsername("");
        return userDto;
    }
}
