package com.otakurating.anime.infra.mapper;

import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.infra.persistence.AnimeContributionEntity;
import com.otakurating.anime.infra.persistence.AnimeContributionIdentifier;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionInfraMapper {
    public AnimeContribution toModel(AnimeContributionEntity entity) {
        return new AnimeContribution(
                entity.getId().getPersonId(),
                entity.getId().getAnimeId(),
                entity.getRole()
        );
    }

    public AnimeContributionEntity toEntity(AnimeContribution model) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(model.getPersonId(), model.getAnimeId());
        return AnimeContributionEntity.builder()
                .id(id)
                .role(model.getCreditRole())
                .build();
    }
}
