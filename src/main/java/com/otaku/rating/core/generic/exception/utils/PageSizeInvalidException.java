package com.otaku.rating.core.generic.exception.utils;

import com.otaku.rating.core.generic.exception.ValidationException;

public class PageSizeInvalidException extends ValidationException {
    public PageSizeInvalidException(int max) {
        super("page.size.invalid", generateMessage(max));
    }

    private static String generateMessage(int max) {
        return String.format("The page size must be greater than 0 and less or equal to %d", max);
    }
}
