package com.otakurating.anime.core.exception;

public final class PersonNameInvalidCharacterException extends CoreException {
    public PersonNameInvalidCharacterException() {
        super("person.name.invalid.char", "The name has invalid characters.");
    }
}
