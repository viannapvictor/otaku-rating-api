package com.otaku.rating.core.user.repository;

import com.otaku.rating.core.user.model.PasswordReset;
import com.otaku.rating.core.user.model.User;

import java.util.Optional;

public interface PasswordResetRepository {
    Optional<PasswordReset> findByCode(String code);
    Optional<PasswordReset> findByUser(User user);
    void delete(PasswordReset passwordReset);
    void deleteFromUser(User user);
    PasswordReset save(PasswordReset passwordReset);
}