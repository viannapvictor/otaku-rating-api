package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Name;
import com.otaku.rating.core.user.model.valueobject.Password;
import com.otaku.rating.core.user.model.valueobject.UserName;
import lombok.Getter;

@Getter
public class UserRegister {
    private final UserName userName;
    private final Name name;
    private final Email email;
    private final Password password;

    public UserRegister(String userName, String name, String email, String password) {
        this.userName = UserName.valueOf(userName);
        this.name = Name.valueOf(name);
        this.email = Email.valueOf(email);
        this.password = Password.valueOf(password);
    }
}
