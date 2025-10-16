package com.otaku.rating.anime.app.response.mapper;

import com.otaku.rating.anime.app.response.dto.AnimeContributionViewDTO;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.generic.core.mapper.OutputMapper;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionViewMapper implements OutputMapper<AnimeContribution, AnimeContributionViewDTO> {
    @Override
    public AnimeContributionViewDTO toEntity(AnimeContribution model) {
        return new AnimeContributionViewDTO(model.getPersonId(), model.getAnimeId(), model.getCreditRole());
    }
}
