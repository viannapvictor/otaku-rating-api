package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PersonDescriptionNullException extends ValidationException {
    public PersonDescriptionNullException() {
        super("person.description.null", "The description cannot be null.");
    }
}
