package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.out.SavePort;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionEntity;
import com.otakurating.anime.infra.adapter.persistence.mapper.AnimeContributionInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeContributionMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveAnimeContributionAdapter implements SavePort<AnimeContribution> {
    private final AnimeContributionMongoRepository animeContributionMongoRepository;
    private final AnimeContributionInfraMapper animeContributionInfraMapper;

    public SaveAnimeContributionAdapter(AnimeContributionMongoRepository animeContributionMongoRepository, AnimeContributionInfraMapper animeContributionInfraMapper) {
        this.animeContributionMongoRepository = animeContributionMongoRepository;
        this.animeContributionInfraMapper = animeContributionInfraMapper;
    }

    @Override
    public AnimeContribution save(AnimeContribution item) {
        AnimeContributionEntity entity = animeContributionInfraMapper.toEntity(item);
        AnimeContributionEntity savedEntity = animeContributionMongoRepository.save(entity);

        return animeContributionInfraMapper.toModel(savedEntity);
    }
}
