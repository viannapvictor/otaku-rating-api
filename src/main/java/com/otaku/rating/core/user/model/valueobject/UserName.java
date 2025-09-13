package com.otaku.rating.core.user.model.valueobject;

import com.otaku.rating.core.generic.util.CharacterUtils;
import com.otaku.rating.core.user.exception.UserNameInvalidCharacterException;
import com.otaku.rating.core.user.exception.UserNameInvalidFormatException;
import com.otaku.rating.core.user.exception.UserNameInvalidLengthException;
import com.otaku.rating.core.user.exception.UserNameNullException;
import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EqualsAndHashCode
public final class UserName {
    public static final int MIN_LENGTH = 6;
    public static final int MAX_LENGTH = 20;

    private final String value;

    public static UserName valueOf(String userName) {
        if (userName == null) {
            throw new UserNameNullException();
        }
        if (userName.length() < MIN_LENGTH || userName.length() > MAX_LENGTH) {
            throw new UserNameInvalidLengthException();
        }
        if (userName.charAt(0) == '-' || userName.charAt(userName.length() - 1) == '-') {
            throw new UserNameInvalidFormatException();
        }
        if (userNameHasInvalidChars(userName)) {
            throw new UserNameInvalidCharacterException();
        }
        return new UserName(userName);
    }

    public static UserName valueOfUnsafe(String userName) {
        return new UserName(userName);
    }

    private static boolean userNameHasInvalidChars(String userName) {
        for (int i = 0; i < userName.length(); i++) {
            char c = userName.charAt(i);
            if (c == '-' || CharacterUtils.isOneByteDigit(c) || CharacterUtils.isOneByteLowercaseLetter(c)) {
                continue;
            }
            return true;
        }
        return false;
    }
}
