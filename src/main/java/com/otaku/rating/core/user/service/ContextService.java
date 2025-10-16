package com.otaku.rating.core.user.service;

import com.otaku.rating.core.user.model.User;

import java.util.Optional;

public interface ContextService {
    Optional<User> getUserOptional();
    User getUserOrThrow();
    String getPrincipalName();
}