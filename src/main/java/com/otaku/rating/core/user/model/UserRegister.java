package com.otaku.rating.core.user.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.user.model.supportobjects.Email;
import com.otaku.rating.core.user.model.supportobjects.Name;
import com.otaku.rating.core.user.model.supportobjects.Password;
import com.otaku.rating.core.user.model.supportobjects.UserName;
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
