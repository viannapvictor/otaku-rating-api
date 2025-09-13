package com.otaku.rating.core.user.model;

import com.otaku.rating.core.user.model.valueobject.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class EmailConfirmationRequest {
    private final String code;
    private final Email email;
}