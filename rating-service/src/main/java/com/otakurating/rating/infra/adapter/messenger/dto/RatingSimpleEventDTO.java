package com.otakurating.rating.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public record RatingSimpleEventDTO(UUID id, Instant timestamp, String ratingId) {
}
