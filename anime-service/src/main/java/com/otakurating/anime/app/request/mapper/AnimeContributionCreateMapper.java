package com.otakurating.anime.app.request.mapper;

import com.otakurating.anime.app.request.dto.AnimeContributionCreateDTO;
import com.otakurating.anime.core.model.AnimeContribution;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionCreateMapper {
    public AnimeContribution toModel(AnimeContributionCreateDTO entity) {
        return new AnimeContribution(entity.getPersonId(), entity.getAnimeId(), entity.getRole());
    }
}
