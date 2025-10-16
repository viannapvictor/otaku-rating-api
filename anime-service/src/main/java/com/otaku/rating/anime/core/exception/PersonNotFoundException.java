package com.otaku.rating.anime.core.exception;

import com.otaku.rating.generic.core.exception.NotFoundException;

public final class PersonNotFoundException extends NotFoundException {
    public PersonNotFoundException() {
        super("person.not.found", "The person was not found.");
    }
}
