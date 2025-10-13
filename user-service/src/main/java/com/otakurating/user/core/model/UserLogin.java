package com.otakurating.user.core.model;

import com.otakurating.user.core.model.valueobject.Email;
import com.otakurating.user.core.model.valueobject.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLogin {
    private final Email email;
    private final Password password;
}