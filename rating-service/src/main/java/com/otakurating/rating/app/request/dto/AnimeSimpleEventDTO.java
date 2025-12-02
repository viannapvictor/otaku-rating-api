package com.otakurating.rating.app.request.dto;

import java.time.Instant;
import java.util.UUID;

public record AnimeSimpleEventDTO(UUID eventId, Instant timestamp, String animeId) {
}
