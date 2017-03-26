package com.example.todoapp.interfaces.security.facade;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 認証ユーザサービスファサード
 * Created by d_akihiro on 2017/03/19.
 */
public interface UserDetailsServiceFacade extends UserDetailsService {
    /**
     * パスワードエンコーダー
     * @return エンコーダー
     */
    PasswordEncoder getPasswordEncoder();
}
