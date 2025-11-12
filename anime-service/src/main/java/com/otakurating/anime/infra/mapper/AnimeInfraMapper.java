package com.otakurating.anime.infra.mapper;

import com.otakurating.anime.core.model.*;
import com.otakurating.anime.infra.persistence.AnimeEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimeInfraMapper {
    public Anime toModel(AnimeEntity entity) {
        AnimeIdentifier animeIdentifier = AnimeIdentifier.valueOfUnsafe(entity.getId());
        AnimeTitle animeTitle = AnimeTitle.valueOfUnsafe(entity.getTitle());
        AnimeDescription animeDescription = AnimeDescription.valueOfUnsafe(entity.getDescription());
        AnimeRelease animeRelease = AnimeRelease.valueOfUnsafe(entity.getRelease());

        return new Anime(animeIdentifier, animeTitle, animeDescription, animeRelease);
    }

    public AnimeEntity toEntity(Anime model) {
        return AnimeEntity.builder()
                .id(model.getId().getValue())
                .title(model.getTitle().getValue())
                .description(model.getDescription().getValue())
                .release(model.getRelease().getValue())
                .build();
    }
}
