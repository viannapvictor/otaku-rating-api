package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.*;
import com.otaku.rating.core.user.service.PasswordEncoderService;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class User {
    private final Long id;
    private UserName userName;
    private Name name;
    private Email email;
    private String encryptedPassword;
    private final EnumUserRole role;
    private final boolean active;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public User(PasswordEncoderService passwordEncoderService, UserRegister userRegister) {
        if (passwordEncoderService == null) {
            throw new IllegalArgumentException("The password encoder must not be null.");
        }
        if (userRegister == null) {
            throw new IllegalArgumentException("The user register must not be null.");
        }

        LocalDateTime now = LocalDateTime.now();
        this.id = null;
        this.userName = userRegister.getUserName();
        this.name = userRegister.getName();
        this.email = userRegister.getEmail();
        this.encryptedPassword = passwordEncoderService.encryptPassword(userRegister.getPassword());
        this.role = EnumUserRole.COMMON;
        this.active = false;
        this.createdAt = now;
        this.updatedAt = now;
    }

    private User(
            Long id,
            Email email,
            UserName userName,
            Name name,
            String encryptedPassword,
            EnumUserRole role,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void setPassword(PasswordEncoderService passwordEncoderService, Password password) {
        if (passwordEncoderService == null) {
            throw new IllegalArgumentException("The password encoder must not be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot set null to the password field.");
        }
        encryptedPassword = passwordEncoderService.encryptPassword(password);
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
            String encryptedPassword,
            EnumUserRole userRole,
            boolean active,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return new User(id, email, userName, name, encryptedPassword, userRole, active, createdAt, updatedAt);
    }
}
