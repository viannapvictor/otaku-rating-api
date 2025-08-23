package com.otaku.rating.core.user.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.properties.Email;
import com.otaku.rating.core.user.model.properties.Name;
import com.otaku.rating.core.user.model.properties.Password;
import com.otaku.rating.core.user.model.properties.UserName;
import lombok.Getter;

@Getter
public class UserRegister {
    private final UserName userName;
    private final Name name;
    private final Email email;
    private final Password password;

    public UserRegister(String userName, String name, String email, String password) throws ValidationException {
        this.userName = new UserName(userName);
        this.name = new Name(name);
        this.email = new Email(email);
        this.password = new Password(password);
    }
}
