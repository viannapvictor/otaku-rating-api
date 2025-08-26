package com.otaku.rating.core.user.model.supportobjects;

import com.otaku.rating.core.generic.exception.ValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Email {

    public static final int MIN_LENGTH = 3;
    public static final int MAX_LENGTH = 90;
    public static final Pattern PATTERN = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");

    private String value;

    public Email(String email) throws ValidationException {
        if (email == null) {
            throw new ValidationException("USER_EMAIL_NULL", "User email cannot be null");
        }
        if (email.length() < MIN_LENGTH || email.length() > MAX_LENGTH) {
            throw new ValidationException("USER_INVALID_EMAIL_LENGTH",
                    String.format("User email length must be between %d and %d characters", MIN_LENGTH, MAX_LENGTH));
        }
        Matcher matcher = PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new ValidationException("USER_INVALID_EMAIL", "User name contains invalid characters");
        }
        value = email;
    }

    public static Email parseUnsafe(String email) {
        Email emailObj = new Email();
        emailObj.value = email;

        return emailObj;
    }
}
