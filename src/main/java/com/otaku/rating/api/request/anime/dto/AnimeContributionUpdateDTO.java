package com.otaku.rating.api.request.anime.dto;

import com.otaku.rating.core.anime.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeContributionUpdateDTO {
    private CreditRole role;
}
