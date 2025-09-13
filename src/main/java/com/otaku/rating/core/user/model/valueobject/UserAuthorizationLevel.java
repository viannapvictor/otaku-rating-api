package com.otaku.rating.core.user.model.valueobject;

public final class UserAuthorizationLevel {
    private UserAuthorizationLevel() {
    }

    public static final int NONE = 0;
    public static final int DETAILED_USER = 1;
    public static final int MODERATOR = DETAILED_USER;
    public static final int ALL = DETAILED_USER;
}
