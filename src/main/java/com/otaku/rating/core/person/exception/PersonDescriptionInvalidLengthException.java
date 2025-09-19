package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.person.model.PersonDescription;

public final class PersonDescriptionInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            PersonDescription.MAX_LENGTH
    );

    public PersonDescriptionInvalidLengthException() {
        super("person.description.invalid.length", MESSAGE);
    }
}
