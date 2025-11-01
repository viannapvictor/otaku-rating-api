package com.otakurating.anime.app.response.mapper;

import com.otakurating.anime.app.response.dto.AnimeViewDTO;
import com.otakurating.anime.core.model.Anime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimeViewMapper {
    public AnimeViewDTO toEntity(Anime model) {
        return AnimeViewDTO.builder()
                .id(model.getId().getValue())
                .title(model.getTitle().getValue())
                .description(model.getDescription().getValue())
                .release(model.getRelease().getValue())
                .build();
    }
}
