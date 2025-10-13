package com.otaku.rating.anime.infra.mapper;

import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.anime.infra.persistence.AnimeContributionEntity;
import com.otaku.rating.anime.infra.persistence.AnimeContributionIdentifier;
import com.otaku.rating.generic.core.mapper.Mapper;
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
