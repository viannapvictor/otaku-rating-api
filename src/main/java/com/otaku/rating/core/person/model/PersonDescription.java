package com.otaku.rating.core.person.model;

import com.otaku.rating.core.generic.exception.ValidationException;
import lombok.Getter;

@Getter
public final class PersonDescription {
    public static final int MAX_LENGTH = 512;

    private final String value;

    private PersonDescription(String value) {
        this.value = value;
    }

    public static PersonDescription valueOf(String value) {
        if (value == null) {
            throw new ValidationException("person.description.null", "The description cannot be null.");
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() > MAX_LENGTH) {
            String message = String.format("The description size must be less than or equal to %d.", MAX_LENGTH);
            throw new ValidationException("person.description.invalid.length", message);
        }
        return new PersonDescription(sanitizedValue);
    }

    public static PersonDescription fromInfra(String value) {
        return new PersonDescription(value);
    }
}
