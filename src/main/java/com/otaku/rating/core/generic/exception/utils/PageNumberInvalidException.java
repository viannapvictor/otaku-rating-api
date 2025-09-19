package com.otaku.rating.core.generic.exception.utils;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PageNumberInvalidException extends ValidationException {
    public PageNumberInvalidException() {
        super("page.number.invalid", "The page number cannot be negative.");
    }
}
