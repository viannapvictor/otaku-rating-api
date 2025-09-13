package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.Password;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PasswordResetConfirm {
    private final String code;
    private final Password newPassword;
}