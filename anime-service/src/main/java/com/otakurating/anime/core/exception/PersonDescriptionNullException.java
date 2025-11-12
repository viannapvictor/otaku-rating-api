package com.otakurating.anime.core.exception;

public final class PersonDescriptionNullException extends CoreException {
    public PersonDescriptionNullException() {
        super("person.description.null", "The description cannot be null.");
    }
}
