package com.otaku.rating.core.user.repository;

import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.supportobjects.Email;
import com.otaku.rating.core.user.model.supportobjects.UserName;

import java.util.Optional;

public interface UserRepository {

    boolean existsByEmail(Email email);
    boolean existsByUserName(UserName userName);
    Optional<User> findByEmail(Email email);
    Optional<User> findById(Long id);
    Optional<User> findByUserName(UserName userName);
    User save(User user);
    void delete(User user);
}
