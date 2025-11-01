package com.otakurating.user.core.model.valueobject;

import com.otakurating.user.core.exception.PasswordBytesOverflowException;
import com.otakurating.user.core.exception.PasswordInvalidCharacterException;
import com.otakurating.user.core.exception.PasswordInvalidLengthException;
import com.otakurating.user.core.exception.PasswordNullException;
import lombok.*;

import java.nio.charset.StandardCharsets;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class Password {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_BYTES = 72;

    private final String value;

    public static Password valueOf(String password) {
        if (password == null) {
            throw new PasswordNullException();
        }
        if (password.length() < MIN_LENGTH) {
            throw new PasswordInvalidLengthException();
        }
        if (passwordHasInvalidChars(password)) {
            throw new PasswordInvalidCharacterException();
        }
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > MAX_BYTES) {
            throw new PasswordBytesOverflowException();
        }
        return new Password(password);
    }

    public static Password valueOfUnsafe(String password) {
        return new Password(password);
    }

    private static boolean passwordHasInvalidChars(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isISOControl(c)) return true;
        }
        return false;
    }
}
