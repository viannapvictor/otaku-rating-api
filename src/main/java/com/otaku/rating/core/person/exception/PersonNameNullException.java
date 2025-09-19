package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PersonNameNullException extends ValidationException {
    public PersonNameNullException() {
        super("person.name.null", "The name cannot be null.");
    }
}
