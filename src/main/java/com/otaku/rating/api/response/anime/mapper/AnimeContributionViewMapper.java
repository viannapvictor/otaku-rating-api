package com.otaku.rating.api.response.anime.mapper;

import com.otaku.rating.api.response.anime.dto.AnimeContributionViewDTO;
import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionViewMapper implements OutputMapper<AnimeContribution, AnimeContributionViewDTO> {
    @Override
    public AnimeContributionViewDTO toEntity(AnimeContribution model) {
        return new AnimeContributionViewDTO(model.getPersonId(), model.getAnimeId(), model.getCreditRole());
    }
}
