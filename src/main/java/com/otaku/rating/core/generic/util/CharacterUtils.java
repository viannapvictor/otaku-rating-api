package com.otaku.rating.core.generic.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CharacterUtils {
    public static boolean isOneByteDigit(char c) {
        return c >= 48 && c <= 57;
    }

    public static boolean isOneByteLowercaseLetter(char c) {
        return c >= 97 && c <= 122;
    }
}

