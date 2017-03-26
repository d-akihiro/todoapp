package com.example.todoapp.interfaces.security.facade.impl;

import com.example.todoapp.application.service.UserService;
import com.example.todoapp.domain.model.user.User;
import com.example.todoapp.interfaces.security.facade.UserDetailsServiceFacade;
import com.example.todoapp.interfaces.security.facade.impl.assembler.UserDetailsDtoAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 認証ユーザサービスファサード(実装)
 * Created by d_akihiro on 2017/03/19.
 */
@Component
public class UserDetailsServiceFacadeImpl implements UserDetailsServiceFacade {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(username)){
            throw new IllegalArgumentException();
        }

        User user = userService.getUserByUsername(username);
        if(user == null){
            throw new UsernameNotFoundException(username);
        }

        UserDetailsDtoAssembler userDetailsDtoAssembler = new UserDetailsDtoAssembler();
        return userDetailsDtoAssembler.toDto(user);
    }

    @Override
    public PasswordEncoder getPasswordEncoder() {
        return userService.getPasswordEncoder();
    }
}
