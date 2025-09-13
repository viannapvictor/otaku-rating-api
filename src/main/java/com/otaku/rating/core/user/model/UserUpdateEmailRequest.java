package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobjects.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateEmailRequest {
    private Email email;
}