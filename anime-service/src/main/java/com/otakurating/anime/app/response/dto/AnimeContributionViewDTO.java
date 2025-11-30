package com.otakurating.anime.app.response.dto;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public record AnimeContributionViewDTO(UUID personId, UUID animeId, CreditRole role) {
}
