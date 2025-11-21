package com.otakurating.anime.core.model;

import com.otakurating.anime.core.exception.AnimeTitleInvalidCharacterException;
import com.otakurating.anime.core.exception.AnimeTitleInvalidLengthException;
import com.otakurating.anime.core.exception.AnimeTitleNullException;

public final class AnimeTitle {
    public static final int MIN_LENGTH = 1;
    public static final int MAX_LENGTH = 128;

    private final String value;

    private AnimeTitle(String value) {
        if (value == null) {
            throw new AnimeTitleNullException();
        }
        String sanitizedValue = value.trim();
        if (sanitizedValue.length() < MIN_LENGTH || sanitizedValue.length() > MAX_LENGTH) {
            throw new AnimeTitleInvalidLengthException();
        }
        if (hasInvalidChars(sanitizedValue)) {
            throw new AnimeTitleInvalidCharacterException();
        }
        this.value = sanitizedValue;
    }

    public String getValue() {
        return value;
    }

    public static AnimeTitle valueOf(String value) {
        return new AnimeTitle(value);
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
