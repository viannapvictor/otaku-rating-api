package com.otakurating.anime.app.response.mapper;

import com.otakurating.anime.app.response.dto.AnimeContributionViewDTO;
import com.otakurating.anime.core.model.AnimeContribution;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionViewMapper {
    public AnimeContributionViewDTO toEntity(AnimeContribution model) {
        return new AnimeContributionViewDTO(model.getPersonId(), model.getAnimeId(), model.getCreditRole());
    }
}
