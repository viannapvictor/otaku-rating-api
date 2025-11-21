package com.otakurating.anime.app.response.mapper;

import com.otakurating.anime.app.response.dto.AnimeViewDTO;
import com.otakurating.anime.core.model.Anime;
import org.springframework.stereotype.Component;

@Component
public class AnimeViewMapper {
    public AnimeViewDTO toEntity(Anime model) {
        return new AnimeViewDTO(
                model.getId(),
                model.getTitle().getValue(),
                model.getDescription().getValue(),
                model.getRelease().getValue()
        );
    }
}
