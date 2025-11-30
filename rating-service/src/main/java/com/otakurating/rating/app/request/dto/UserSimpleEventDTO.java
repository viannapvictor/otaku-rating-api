package com.otakurating.rating.app.request.dto;

import java.time.Instant;
import java.util.UUID;

public record UserSimpleEventDTO(UUID eventId, Instant timestamp, String userId) {
}
