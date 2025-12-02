package com.otakurating.anime.infra.adapter.persistence.entity;

import java.util.UUID;

public record AnimeContributionIdentifier(UUID animeId, UUID personId) {
}
