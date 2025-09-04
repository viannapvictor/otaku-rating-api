package com.otaku.rating.core.user.repository;

import com.otaku.rating.core.user.model.RefreshToken;
import com.otaku.rating.core.user.model.User;

import java.util.Optional;

public interface RefreshTokenRepository {
    void deleteAllByUser(User user);
    boolean existsByCode(String code);
    Optional<RefreshToken> findByCode(String code);
    RefreshToken save(RefreshToken refreshToken);
    void deleteByCode(String code);
}
