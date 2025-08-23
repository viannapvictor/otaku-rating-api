package com.otaku.rating.core.user.model.properties;

import lombok.Getter;

@Getter
public enum EnumUserRole {
    COMMON(UserAuthorizationLevel.NONE ),
    MODERATOR(UserAuthorizationLevel.MODERATOR),
    ADMIN(UserAuthorizationLevel.ALL);

    private final int authorizationLevel;

    EnumUserRole(int authorizationLevel) {
        this.authorizationLevel = authorizationLevel;
    }
}
