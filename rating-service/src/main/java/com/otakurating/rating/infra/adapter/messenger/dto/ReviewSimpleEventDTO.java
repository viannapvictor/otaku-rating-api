package com.otakurating.rating.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public record ReviewSimpleEventDTO(UUID id, Instant timestamp, String ratingId, String userId) {
}
