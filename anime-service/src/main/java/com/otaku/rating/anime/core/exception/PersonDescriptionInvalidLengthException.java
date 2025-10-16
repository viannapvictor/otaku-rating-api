package com.otaku.rating.anime.core.exception;

import com.otaku.rating.anime.core.model.PersonDescription;
import com.otaku.rating.generic.core.exception.ValidationException;

public final class PersonDescriptionInvalidLengthException extends ValidationException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            PersonDescription.MAX_LENGTH
    );

    public PersonDescriptionInvalidLengthException() {
        super("person.description.invalid.length", MESSAGE);
    }
}
