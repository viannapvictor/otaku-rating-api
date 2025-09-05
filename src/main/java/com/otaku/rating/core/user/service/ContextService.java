package com.otaku.rating.core.user.service;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.AuthTokens;
import com.otaku.rating.core.user.model.User;

import java.util.Optional;

public interface ContextService {
    Optional<User> getUserOptional();
    User getUserOrThrow() throws ValidationException;
    AuthTokens refreshTokens() throws ValidationException;
    void throwIfNotAuthenticated() throws ValidationException;
    boolean isAuthenticated();
    String getCookieValue(String key);
    String getCookieValueOrDefault(String key, String defaultValue);
}