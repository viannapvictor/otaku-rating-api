package com.otaku.rating.core.person.exception;

import com.otaku.rating.core.generic.exception.NotFoundException;

public final class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException() {
        super("person.not.found", "The person was not found.");
    }
}
