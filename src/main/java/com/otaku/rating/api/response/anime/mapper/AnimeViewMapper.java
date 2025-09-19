package com.otaku.rating.api.response.anime.mapper;

import com.otaku.rating.api.response.anime.dto.AnimeViewDTO;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.generic.mapper.OutputMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeViewMapper implements OutputMapper<Anime, AnimeViewDTO> {
    @Override
    public AnimeViewDTO toEntity(Anime model) {
        return AnimeViewDTO.builder()
                .id(model.getId().getValue())
                .title(model.getTitle().getValue())
                .description(model.getDescription().getValue())
                .release(model.getRelease().getValue())
                .build();
    }
}
