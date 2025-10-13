package com.otaku.rating.anime.core.exception;

import com.otaku.rating.anime.core.model.PersonName;
import com.otaku.rating.generic.core.exception.ValidationException;

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
