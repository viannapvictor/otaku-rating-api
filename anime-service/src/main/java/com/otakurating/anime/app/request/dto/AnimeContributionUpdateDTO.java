package com.otakurating.anime.app.request.dto;

import com.otakurating.anime.core.model.CreditRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimeContributionUpdateDTO {
    private CreditRole role;
}
