package com.otakurating.user.core.model;

import com.otakurating.user.core.model.valueobject.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdateEmailRequest {
    private Email email;
}