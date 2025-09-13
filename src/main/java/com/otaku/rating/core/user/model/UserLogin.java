package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.Email;
import com.otaku.rating.core.user.model.valueobject.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLogin {
    private final Email email;
    private final Password password;
}