package com.otakurating.user.core.model.valueobject;

import com.otakurating.user.core.exception.NameInvalidCharacterException;
import com.otakurating.user.core.exception.NameInvalidLengthException;
import com.otakurating.user.core.exception.NameNullException;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class Name {
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 150;

    private final String value;

    public static Name valueOf(String name) {
        if (name == null) {
            throw new NameNullException();
        }
        if (name.length() < MIN_LENGTH || name.length() > MAX_LENGTH) {
            throw new NameInvalidLengthException();
        }
        if (nameHasInvalidChars(name)) {
            throw new NameInvalidCharacterException();
        }
        return new Name(name);
    }

    public static Name valueOfUnsafe(String name) {
        return new Name(name);
    }

    private static boolean nameHasInvalidChars(String name) {
        if (name.charAt(0) == ' ' || name.charAt(name.length() - 1) == ' ') {
            return true;
        }
        int whitespaceSequence = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
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