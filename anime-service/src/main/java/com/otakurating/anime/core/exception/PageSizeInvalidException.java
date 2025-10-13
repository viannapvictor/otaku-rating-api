package com.otakurating.anime.core.exception;

public class PageSizeInvalidException extends CoreException {
    public PageSizeInvalidException(int max) {
        super("page.size.invalid", generateMessage(max));
    }

    private static String generateMessage(int max) {
        return String.format("The page size must be greater than 0 and less or equal to %d", max);
    }
}
