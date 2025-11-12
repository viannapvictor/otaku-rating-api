package com.otakurating.user.core.model.valueobject;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class ConfirmationCode {
    public static final int CODE_LENGTH = 100;
    public static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+";

    private final String value;

    public ConfirmationCode() {
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(CODE_LENGTH);

        for (int i = 0; i < CODE_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            code.append(CHARACTERS.charAt(index));
        }
        this.value = code.toString();
    }

    public static ConfirmationCode valueOfUnsafe(String value) {
        return new ConfirmationCode(value);
    }

    public boolean matches(String code) {
        return Objects.equals(value, code);
    }

    public String encodeToUrlCode() {
        return URLEncoder.encode(value, StandardCharsets.UTF_8);
    }
}
