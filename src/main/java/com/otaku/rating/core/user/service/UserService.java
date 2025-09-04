package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.UserRegister;
import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.UserName;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface UserService {
    User createUser(UserRegister userRegister);
    Page<User> getPage(int page, int size);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(Email email);
    Optional<User> findByUserName(UserName userName);
    boolean existsByEmail(Email email);
    boolean existsByUserName(UserName userName);
    User updateUser(User user);
    void deleteUser(User user);
}