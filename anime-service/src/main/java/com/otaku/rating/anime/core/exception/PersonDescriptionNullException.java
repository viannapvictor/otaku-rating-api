package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PersonDescriptionNullException extends ValidationException {
    public PersonDescriptionNullException() {
        super("person.description.null", "The description cannot be null.");
    }
}
