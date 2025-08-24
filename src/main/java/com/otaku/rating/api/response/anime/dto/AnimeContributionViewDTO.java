package com.otaku.rating.api.response.anime.dto;

import com.otaku.rating.core.anime.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnimeContributionViewDTO {
    private Long id;
    private CreditRole role;
}
