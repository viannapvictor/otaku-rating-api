package com.otakurating.anime.core.exception;

import com.otakurating.anime.core.model.PersonDescription;

public final class PersonDescriptionInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The description size must be less than or equal to %d.",
            PersonDescription.MAX_LENGTH
    );

    public PersonDescriptionInvalidLengthException() {
        super("person.description.invalid.length", MESSAGE);
    }
}
