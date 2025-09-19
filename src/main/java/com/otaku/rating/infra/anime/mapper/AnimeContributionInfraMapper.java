package com.otaku.rating.infra.anime.mapper;

import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.infra.anime.persistence.AnimeContributionEntity;
import com.otaku.rating.infra.anime.persistence.AnimeContributionIdentifier;
import org.springframework.stereotype.Component;

@Component
public class AnimeContributionInfraMapper implements Mapper<AnimeContribution, AnimeContributionEntity> {
    @Override
    public AnimeContribution toModel(AnimeContributionEntity entity) {
        return new AnimeContribution(
                entity.getId().getPersonId(),
                entity.getId().getAnimeId(),
                entity.getRole()
        );
    }

    @Override
    public AnimeContributionEntity toEntity(AnimeContribution model) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(model.getPersonId(), model.getAnimeId());
        return AnimeContributionEntity.builder()
                .id(id)
                .role(model.getCreditRole())
                .build();
    }
}
