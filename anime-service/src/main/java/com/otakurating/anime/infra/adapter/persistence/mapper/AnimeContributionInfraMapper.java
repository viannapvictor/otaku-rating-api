package com.otakurating.anime.infra.adapter.persistence.mapper;

import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionEntity;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionIdentifier;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionInfraMapper {
    public AnimeContribution toModel(AnimeContributionEntity entity) {
        return new AnimeContribution(
                entity.id().animeId(),
                entity.id().personId(),
                entity.role()
        );
    }

    public AnimeContributionEntity toEntity(AnimeContribution model) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(model.getAnimeId(), model.getPersonId());
        return new AnimeContributionEntity(id, model.getCreditRole());
    }
}
