package com.otaku.rating.user.core.model;

import com.otaku.rating.user.core.model.valueobject.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateEmailRequest {
    private Email email;
}