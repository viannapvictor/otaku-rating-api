package com.otakurating.anime.core.model;

import lombok.Getter;

@Getter
public final class AnimeIdentifier {
    private final String value;

    private AnimeIdentifier(String value) {
        this.value = value;
    }

    public static AnimeIdentifier valueOf(AnimeTitle title) {
        if (title == null) {
            throw new IllegalArgumentException("The title cannot be null.");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < title.getValue().length(); i++) {
            char c = Character.toLowerCase(title.getValue().charAt(i));
            if (c == ' ') {
                sb.append('-');
                continue;
            }
            sb.append(c);
        }
        return new AnimeIdentifier(sb.toString());
    }

    public static AnimeIdentifier valueOfUnsafe(String value) {
        return new AnimeIdentifier(value);
    }
}
