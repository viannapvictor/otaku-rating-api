package com.otaku.rating.user.core.model;

import com.otaku.rating.user.core.model.valueobject.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordResetConfirm {
    private final String code;
    private final Password newPassword;
}