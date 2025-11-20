package com.otakurating.anime.core.port.out;

import java.util.UUID;

public interface DeleteAnimeContributionPort {
    void deleteByAnimeIdAndPersonId(String animeId, UUID personId);
    void deleteByAnimeId(String animeId);
    void deleteByPersonId(UUID personId);
}
