package com.otakurating.anime.core.model;

import com.otakurating.anime.core.exception.AnimeReleaseInvalidException;
import com.otakurating.anime.core.exception.AnimeReleaseNullException;

import java.time.LocalDate;
import java.time.ZoneOffset;

public final class AnimeRelease {
    public static final int MIN_YEAR = 1900;

    private final LocalDate value;

    private AnimeRelease(LocalDate value) {
        if (value == null) {
            throw new AnimeReleaseNullException();
        }
        LocalDate now = LocalDate.now(ZoneOffset.UTC);
        if (value.getYear() < MIN_YEAR || value.isAfter(now)) {
            throw new AnimeReleaseInvalidException();
        }
        this.value = value;
    }

    public LocalDate getValue() {
        return value;
    }

    public static AnimeRelease valueOf(LocalDate value) {
        return new AnimeRelease(value);
    }
}
