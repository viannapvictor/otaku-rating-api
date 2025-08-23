package com.otaku.rating.api.request.anime.mapper;

import com.otaku.rating.api.request.anime.dto.AnimeContributionCreateDTO;
import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.generic.mapper.InputMapper;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionCreateMapper implements InputMapper<AnimeContribution, AnimeContributionCreateDTO> {
    @Override
    public AnimeContribution toModel(AnimeContributionCreateDTO entity) {
        return new AnimeContribution(entity.getPersonId(), entity.getAnimeId(), entity.getRole());
    }
}
