package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.ValidationException;

public final class PersonNameInvalidCharacterException extends ValidationException {
    public PersonNameInvalidCharacterException() {
        super("person.name.invalid.char", "The name has invalid characters.");
    }
}
