package com.otakurating.user.core.model.valueobject;

import com.otakurating.user.core.exception.EmailInvalidFormatException;
import com.otakurating.user.core.exception.EmailInvalidLengthException;
import com.otakurating.user.core.exception.EmailNullException;
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
