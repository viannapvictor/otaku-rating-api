package com.otakurating.anime.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public record AnimeUpdatedEventDTO(UUID id, Instant timestamp, String oldAnimeId, String animeId) {
}
