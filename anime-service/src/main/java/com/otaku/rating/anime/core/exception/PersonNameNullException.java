package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PersonNameNullException extends ValidationException {
    public PersonNameNullException() {
        super("person.name.null", "The name cannot be null.");
    }
}
