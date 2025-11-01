package com.otakurating.user.app.response.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class ErrorData {
    private final String code;
    private final String message;
}
