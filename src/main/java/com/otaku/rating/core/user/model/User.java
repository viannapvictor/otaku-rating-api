package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.properties.*;
import com.otaku.rating.core.user.service.PasswordEncoderServiceImpl;
import lombok.Getter;

@Getter
public class User {
    private final Long id;
    private UserName userName;
    private Name name;
    private Email email;
    private String encryptedPassword;
    private final EnumUserRole role;
    private final boolean active;

    public User(PasswordEncoderServiceImpl passwordEncoderServiceImpl, UserRegister userRegister) {
        if (passwordEncoderServiceImpl == null) {
            throw new IllegalArgumentException("The password encoder must not be null.");
        }
        if (userRegister == null) {
            throw new IllegalArgumentException("The user register must not be null.");
        }

        this.id = null;
        this.userName = userRegister.getUserName();
        this.name = userRegister.getName();
        this.email = userRegister.getEmail();
        this.encryptedPassword = passwordEncoderServiceImpl.encryptPassword(userRegister.getPassword());
        this.role = EnumUserRole.COMMON;
        this.active = false;
    }

    private User(
            Long id,
            Email email,
            UserName userName,
            Name name,
            String encryptedPassword,
            EnumUserRole role,
            boolean active
    ) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.name = name;
        this.encryptedPassword = encryptedPassword;
        this.role = role;
        this.active = active;
    }

    public void setPassword(PasswordEncoderServiceImpl passwordEncoderServiceImpl, Password password) {
        if (passwordEncoderServiceImpl == null) {
            throw new IllegalArgumentException("The password encoder must not be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("Cannot set null to the password field.");
        }
        encryptedPassword = passwordEncoderServiceImpl.encryptPassword(password);
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
            boolean active
    ) {
        return new User(id, email, userName, name, encryptedPassword, userRole, active);
    }
}
