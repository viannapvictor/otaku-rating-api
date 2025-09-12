package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobjects.Email;
import com.otaku.rating.core.user.model.valueobjects.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserLogin {
    private final Email email;
    private final Password password;
}