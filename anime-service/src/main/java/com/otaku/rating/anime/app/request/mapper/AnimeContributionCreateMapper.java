package com.otaku.rating.anime.app.request.mapper;

import com.otaku.rating.anime.app.request.dto.AnimeContributionCreateDTO;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.generic.core.mapper.InputMapper;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionCreateMapper implements InputMapper<AnimeContribution, AnimeContributionCreateDTO> {
    @Override
    public AnimeContribution toModel(AnimeContributionCreateDTO entity) {
        return new AnimeContribution(entity.getPersonId(), entity.getAnimeId(), entity.getRole());
    }
}
