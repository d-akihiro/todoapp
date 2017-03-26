package com.example.todoapp.infrastructure.persistence.jpa;

import com.example.todoapp.domain.model.user.UserRepository;
import com.example.todoapp.domain.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by d_akihiro on 2017/02/23.
 */
public interface JpaUserRepository extends UserRepository, JpaRepository<User, Long> {
}
