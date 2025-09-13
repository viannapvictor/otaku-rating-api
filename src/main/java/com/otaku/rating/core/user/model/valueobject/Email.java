package com.otaku.rating.core.user.model.valueobject;

import com.otaku.rating.core.user.exception.EmailInvalidFormatException;
import com.otaku.rating.core.user.exception.EmailInvalidLengthException;
import com.otaku.rating.core.user.exception.EmailNullException;
import lombok.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class Email {
    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 90;
    public static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private final String value;

    public static Email valueOf(String email) {
        if (email == null) {
            throw new EmailNullException();
        }
        if (email.length() < MIN_LENGTH || email.length() > MAX_LENGTH) {
            throw new EmailInvalidLengthException();
        }
        Matcher matcher = PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new EmailInvalidFormatException();
        }
        return new Email(email);
    }

    public static Email valueOfUnsafe(String email) {
        return new Email(email);
    }
}
