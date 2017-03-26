package com.example.todoapp.interfaces.security.facade.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 認証ユーザ
 * Created by d_akihiro on 2017/03/19.
 */
@AllArgsConstructor
public class UserDetailsDto implements UserDetails {
    /**
     * ユーザID
     */
    @Getter
    private long id;

    /**
     * ユーザ名
     */
    private String username;

    /**
     * パスワード(エンコード済み)
     */
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
