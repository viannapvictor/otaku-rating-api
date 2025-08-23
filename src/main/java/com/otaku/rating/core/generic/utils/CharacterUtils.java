package com.otaku.rating.core.generic.utils;

public final class CharacterUtils {
    private CharacterUtils() {
    }

    public static boolean isOneByteDigit(char c) {
        return c >= 48 && c <= 57;
    }

    public static boolean isOneByteLowercaseLetter(char c) {
        return c >= 97 && c <= 122;
    }
}

