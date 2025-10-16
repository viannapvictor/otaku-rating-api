package com.otaku.rating.user.core.model;

import com.otaku.rating.user.core.model.valueobject.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailConfirmationRequest {
    private final String code;
    private final Email email;
}