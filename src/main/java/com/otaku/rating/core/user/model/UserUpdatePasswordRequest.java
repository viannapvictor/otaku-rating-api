package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobjects.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserUpdatePasswordRequest {
    private Password oldPassword;
    private Password newPassword;
} 