package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.person.model.PersonName;

public final class PersonNameInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The name size must be greater than or equal to %d and less than or equal to %d.",
            PersonName.MIN_LENGTH,
            PersonName.MAX_LENGTH
    );

    public PersonNameInvalidLengthException() {
        super("person.name.invalid.length", MESSAGE);
    }
}
