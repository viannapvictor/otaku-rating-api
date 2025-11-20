package com.otakurating.anime.app.request.dto;

import com.otakurating.anime.core.model.CreditRole;

import java.util.UUID;

public record AnimeContributionCreateDTO(UUID personId, String animeId, CreditRole role) {
}
