package com.otakurating.anime.core.exception;

public final class PageNumberInvalidException extends CoreException {
    public PageNumberInvalidException() {
        super("page.number.invalid", "The page number cannot be negative.");
    }
}
