package com.otaku.rating.api.response.anime.mapper;

import com.otaku.rating.api.response.anime.dto.AnimeContributionViewDTO;
import com.otaku.rating.api.response.anime.dto.AnimeViewDTO;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AnimeOutputMapper implements OutputMapper<Anime, AnimeViewDTO> {
    @Override
    public AnimeViewDTO toEntity(Anime model) {
        List<AnimeContributionViewDTO> contributionViewDTOs = model.getContributions()
                .stream()
                .map((c) -> AnimeContributionViewDTO
                        .builder()
                        .id(c.getPerson().getId())
                        .role(c.getCreditRole())
                        .build())
                .toList();
        return AnimeViewDTO.builder()
                .id(model.getId().getValue())
                .title(model.getTitle().getValue())
                .description(model.getDescription().getValue())
                .release(model.getRelease().getValue())
                .contributions(contributionViewDTOs)
                .build();
    }
}
