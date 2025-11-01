package com.otakurating.anime.app.response.dto;

import com.otakurating.anime.core.model.CreditRole;
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
