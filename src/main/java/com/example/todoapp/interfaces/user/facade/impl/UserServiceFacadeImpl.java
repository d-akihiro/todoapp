package com.example.todoapp.interfaces.user.facade.impl;

import com.example.todoapp.application.service.UserService;
import com.example.todoapp.domain.model.user.User;
import com.example.todoapp.interfaces.user.facade.UserServiceFacade;
import com.example.todoapp.interfaces.user.facade.dto.UserDto;
import com.example.todoapp.interfaces.user.facade.impl.assembler.UserDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * ユーザ管理サービスファサード(実装)
 * Created by d_akihiro on 2017/03/19.
 */
@Component
public class UserServiceFacadeImpl implements UserServiceFacade {
    @Autowired
    private UserService userService;

    /**
     * ユーザからユーザDtoを作成する
     * @param user ユーザ
     * @return ユーザDto
     */
    private UserDto assemblerUserDto(User user){
        UserDtoAssembler assembler = new UserDtoAssembler();
        return assembler.toDto(user);
    }

    @Override
    public UserDto defaultUser() {
        UserDtoAssembler assembler = new UserDtoAssembler();
        return assembler.defaultUserDto();
    }

    @Override
    public List<UserDto> getUserList() {
        List<UserDto> result = new ArrayList<UserDto>();

        List<User> userList = userService.getUserList();
        if(userList != null) {
            for (User user : userList) {
                result.add(assemblerUserDto(user));
            }
        }
        return result;
    }

    @Override
    public UserDto getUser(long id) {
        User user = userService.getUser(id);
        return assemblerUserDto(user);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = userService.createUser(
                userDto.getUsername(),
                userDto.getPassword());
        return assemblerUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userService.updateUser(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword());
        return assemblerUserDto(user);
    }

    @Override
    public void deleteUser(long id) {
        userService.deleteUser(id);
    }
}
