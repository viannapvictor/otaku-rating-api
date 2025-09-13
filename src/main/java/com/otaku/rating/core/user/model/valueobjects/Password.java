package com.otaku.rating.core.user.model.valueobjects;

import com.otaku.rating.core.generic.exception.ValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Password {
    public static final int MIN_LENGTH = 8;
    public static final int MAX_BYTES = 72;

    private String value;

    public Password(String password) throws ValidationException {
        if (password == null) {
            throw new ValidationException("user.password.null", "User password cannot be null.");
        }
        if (password.length() < MIN_LENGTH) {
            throw new ValidationException("user.password.null", "User password cannot be null.");
        }
        if (passwordHasInvalidChars(password)) {
            throw new ValidationException("user.invalid.password", "User password has invalid characters.");
        }
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        if (bytes.length > MAX_BYTES) {
            throw new ValidationException("user.invalid.password", "User password has exceeds the maximum allowed bytes.");
        }
        value = password;
    }

    private static boolean passwordHasInvalidChars(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (Character.isISOControl(c)) return true;
        }
        return false;
    }

    public static Password parseUnsafe(String password) {
        Password passwordObj = new Password();
        passwordObj.value = password;

        return passwordObj;
    }
}
