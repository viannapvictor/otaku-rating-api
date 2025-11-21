package com.otakurating.anime.infra.adapter.persistence.entity;

import java.util.UUID;

public record AnimeContributionIdentifier(String animeId, UUID personId) {
}
