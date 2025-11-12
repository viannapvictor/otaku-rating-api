package com.otakurating.user.core.model;

import com.otakurating.user.core.model.valueobject.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdatePasswordRequest {
    private Password oldPassword;
    private Password newPassword;
} 