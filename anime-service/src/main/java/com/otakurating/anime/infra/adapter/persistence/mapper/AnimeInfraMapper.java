package com.otakurating.anime.infra.adapter.persistence.mapper;

import com.otakurating.anime.core.model.*;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeEntity;
import org.springframework.stereotype.Component;

@Component
public class AnimeInfraMapper {
    public Anime toModel(AnimeEntity entity) {
        AnimeTitle title = AnimeTitle.valueOf(entity.title());
        AnimeDescription description = AnimeDescription.valueOf(entity.description());
        AnimeRelease release = AnimeRelease.valueOf(entity.release());

        return new Anime(entity.id(), title, description, release);
    }

    public AnimeEntity toEntity(Anime model) {
        return new AnimeEntity(
                model.getId(),
                model.getTitle().getValue(),
                model.getDescription().getValue(),
                model.getRelease().getValue()
        );
    }
}
