package com.otaku.rating.anime.app.response.mapper;

import com.otaku.rating.anime.app.response.dto.AnimeViewDTO;
import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.generic.core.mapper.OutputMapper;
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
