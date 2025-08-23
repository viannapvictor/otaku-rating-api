package com.otaku.rating.core.user.model.supportobjects;

import com.otaku.rating.core.generic.exception.ValidationException;
import com.otaku.rating.core.generic.utils.CharacterUtils;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class UserName {
    public static final int MIN_LENGTH = 15;
    public static final int MAX_LENGTH = 20;

    private String value;

    public UserName(String userName) throws ValidationException {
        if (userName == null) {
            throw new ValidationException("USER_NAME_NULL", "User name cannot be null");
        }
        if (userName.length() < MIN_LENGTH || userName.length() > MAX_LENGTH) {
            throw new ValidationException("USER_NAME_INVALID_LENGTH", 
                String.format("User name length must be between %d and %d characters", MIN_LENGTH, MAX_LENGTH));
        }
        if (userName.charAt(0) == '-' || userName.charAt(userName.length() - 1) == '-') {
            throw new ValidationException("USER_NAME_INVALID_FORMAT", "User name cannot start or end with '-'");
        }
        if (userNameHasInvalidChars(userName)) {
            throw new ValidationException("USER_NAME_INVALID_CHARACTER", "User name contains invalid characters");
        }
        value = userName;
    }

    private boolean userNameHasInvalidChars(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (c == '-' || CharacterUtils.isOneByteDigit(c) || CharacterUtils.isOneByteLowercaseLetter(c)) {
                continue;
            }
            return true;
        }
        return false;
    }

    public static UserName parseUnsafe(String userName) {
        UserName userNameObj = new UserName();
        userNameObj.value = userName;

        return userNameObj;
    }
}
