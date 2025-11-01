package com.otakurating.anime.core.exception;

public final class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException() {
        super("person.not.found", "The person was not found.");
    }
}
