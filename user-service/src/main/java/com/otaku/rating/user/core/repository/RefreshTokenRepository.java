package com.otaku.rating.user.core.repository;

import com.otaku.rating.user.core.model.RefreshToken;
import com.otaku.rating.user.core.model.User;

import java.util.Optional;

public interface RefreshTokenRepository {
    void deleteAllByUser(User user);
    boolean existsByCode(String code);
    Optional<RefreshToken> findByCode(String code);
    RefreshToken save(RefreshToken refreshToken);
    void deleteByCode(String code);
}
