package com.otaku.rating.generic.core.exception.utils;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PageNumberInvalidException extends ValidationException {
    public PageNumberInvalidException() {
        super("page.number.invalid", "The page number cannot be negative.");
    }
}
