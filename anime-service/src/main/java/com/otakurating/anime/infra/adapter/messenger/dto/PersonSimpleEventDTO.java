package com.otakurating.anime.infra.adapter.messenger.dto;

import java.time.Instant;
import java.util.UUID;

public record PersonSimpleEventDTO(UUID id, Instant timestamp, UUID personId) {
}
