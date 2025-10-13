package com.otakurating.anime.core.exception;

public final class PersonNameNullException extends CoreException {
    public PersonNameNullException() {
        super("person.name.null", "The name cannot be null.");
    }
}
