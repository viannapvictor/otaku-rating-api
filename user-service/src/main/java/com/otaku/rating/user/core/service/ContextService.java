package com.otaku.rating.user.core.service;

import com.otaku.rating.user.core.model.AuthTokens;
import com.otaku.rating.user.core.model.User;

import java.util.Optional;

public interface ContextService {
    Optional<User> getUserOptional();
    User getUserOrThrow();
    AuthTokens refreshTokens();
    void throwIfNotAuthenticated();
    boolean isAuthenticated();
    String getCookieValue(String key);
    String getCookieValueOrDefault(String key, String defaultValue);
}