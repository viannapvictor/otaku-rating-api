package com.otaku.rating.anime.infra.mapper;

import com.otaku.rating.anime.core.model.*;
import com.otaku.rating.anime.infra.persistence.AnimeEntity;
import com.otaku.rating.generic.core.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class AnimeInfraMapper implements Mapper<Anime, AnimeEntity> {
    @Override
    public Anime toModel(AnimeEntity entity) {
        AnimeIdentifier animeIdentifier = AnimeIdentifier.valueOfUnsafe(entity.getId());
        AnimeTitle animeTitle = AnimeTitle.valueOfUnsafe(entity.getTitle());
        AnimeDescription animeDescription = AnimeDescription.valueOfUnsafe(entity.getDescription());
        AnimeRelease animeRelease = AnimeRelease.valueOfUnsafe(entity.getRelease());

        return new Anime(animeIdentifier, animeTitle, animeDescription, animeRelease);
    }

    @Override
    public AnimeEntity toEntity(Anime model) {
        return AnimeEntity.builder()
                .id(model.getId().getValue())
                .title(model.getTitle().getValue())
                .description(model.getDescription().getValue())
                .release(model.getRelease().getValue())
                .build();
    }
}
