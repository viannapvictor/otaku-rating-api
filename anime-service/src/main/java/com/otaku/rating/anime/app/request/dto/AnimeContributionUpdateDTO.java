package com.otaku.rating.anime.app.request.dto;

import com.otaku.rating.anime.core.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeContributionUpdateDTO {
    private CreditRole role;
}
