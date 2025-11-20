package com.otakurating.anime.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public record AnimeContributionSimpleEventDTO(UUID id, Instant timestamp, String animeId, UUID personId) {
}
