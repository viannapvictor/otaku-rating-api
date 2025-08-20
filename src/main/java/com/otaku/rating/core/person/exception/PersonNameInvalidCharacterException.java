package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.ValidationException;

public final class PersonNameInvalidCharacterException extends ValidationException {
    public PersonNameInvalidCharacterException() {
        super("person.name.invalid.char", "The name has invalid characters.");
    }
}
