package com.otakurating.anime.core.exception;

import com.otakurating.anime.core.model.PersonName;

public final class PersonNameInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The name size must be greater than or equal to %d and less than or equal to %d.",
            PersonName.MIN_LENGTH,
            PersonName.MAX_LENGTH
    );

    public PersonNameInvalidLengthException() {
        super("person.name.invalid.length", MESSAGE);
    }
}
