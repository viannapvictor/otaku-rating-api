package com.otakurating.rating.app.request.dto;

import java.time.Instant;
import java.util.UUID;

public record AnimeUpdateEventDTO(UUID eventId, Instant timestamp, String oldAnimeId, String animeId) {
}
