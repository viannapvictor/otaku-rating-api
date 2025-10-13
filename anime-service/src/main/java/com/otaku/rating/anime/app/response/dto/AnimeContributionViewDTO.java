package com.otaku.rating.anime.app.response.dto;

import com.otaku.rating.anime.core.model.CreditRole;
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
