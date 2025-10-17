package com.otaku.rating.core.person.model;

import com.otaku.rating.core.person.exception.PersonNameInvalidCharacterException;
import com.otaku.rating.core.person.exception.PersonNameInvalidLengthException;
import com.otaku.rating.core.person.exception.PersonNameNullException;
import lombok.Getter;

@Getter
public final class PersonName {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 128;

    private final String value;

    private PersonName(String value) {
        this.value = value;
    }

    public static PersonName valueOf(String value) {
        if (value == null) {
            throw new PersonNameNullException();
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() < MIN_LENGTH || sanitizedValue.length() > MAX_LENGTH) {
            String message = String.format(
                    "The name size must be greater than or equal to %d and less than or equal to %d.",
                    MIN_LENGTH,
                    MAX_LENGTH
            );
            throw new PersonNameInvalidLengthException();
        }
        if (hasInvalidChars(sanitizedValue)) {
            throw new PersonNameInvalidCharacterException();
        }
        return new PersonName(sanitizedValue);
    }

    public static PersonName valueOfUnsafe(String value) {
        return new PersonName(value);
    }

    private static boolean hasInvalidChars(String value) {
        int whitespaceSequence = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (Character.isISOControl(c)) {
                return true;
            }
            if (c != ' ') {
                if (Character.isWhitespace(c)) return true;

                whitespaceSequence = 0;
                continue;
            }
            whitespaceSequence += 1;
            if (whitespaceSequence > 1) {
                return true;
            }
        }
        return false;
    }
}
