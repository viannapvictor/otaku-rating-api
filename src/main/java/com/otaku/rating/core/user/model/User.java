package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long id;
    private UserName userName;
    private Name name;
    private Email email;
    private final EnumUserRole role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public User(UserRegister userRegister) {
        if (userRegister == null) {
            throw new IllegalArgumentException("The user register must not be null.");
        }

        LocalDateTime now = LocalDateTime.now();
        this.id = null;
        this.userName = userRegister.getUserName();
        this.name = userRegister.getName();
        this.email = userRegister.getEmail();
        this.role = EnumUserRole.COMMON;
        this.createdAt = now;
        this.updatedAt = now;
    }

    private User(
            Long id,
            Email email,
            UserName userName,
            Name name,
            EnumUserRole role,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setName(Name name) {
        if (name == null) {
            throw new IllegalArgumentException("Cannot set null to the name field.");
        }
        this.name = name;
    }

    public void setEmail(Email email) {
        if (email == null) {
            throw new IllegalArgumentException("Cannot set null to the email field.");
        }
        this.email = email;
    }

    public void setUserName(UserName userName) {
        if (userName == null) {
            throw new IllegalArgumentException("Cannot set null to the userName field.");
        }
        this.userName = userName;
    }

    public int authorizationLevel() {
        return role.getAuthorizationLevel();
    }

    public boolean hasAuthorization(int authorizationLevel) {
        return (authorizationLevel() & authorizationLevel) != 0;
    }

    public static User parseUnsafe(
            Long id,
            Email email,
            UserName userName,
            Name name,
            EnumUserRole userRole,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new User(id, email, userName, name, userRole, createdAt, updatedAt);
    }
}
