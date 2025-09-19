package com.otaku.rating.api.response.anime.dto;

import com.otaku.rating.core.anime.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeContributionViewDTO {
    private UUID personId;
    private String animeId;
    private CreditRole role;
}
