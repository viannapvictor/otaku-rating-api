package com.otaku.rating.infra.anime.repository;

import com.otaku.rating.core.anime.model.AnimeContribution;
import com.otaku.rating.core.anime.repository.AnimeContributionRepository;
import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.infra.anime.persistence.AnimeContributionEntity;
import com.otaku.rating.infra.anime.persistence.AnimeContributionIdentifier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class AnimeContributionRepositoryImpl implements AnimeContributionRepository {
    private final AnimeContributionMongoRepository animeContributionMongoRepository;
    private final Mapper<AnimeContribution, AnimeContributionEntity> animeContributionEntityMapper;

    @Override
    public AnimeContribution save(AnimeContribution animeContribution) {
        AnimeContributionEntity entity = animeContributionEntityMapper.toEntity(animeContribution);
        AnimeContributionEntity savedEntity = animeContributionMongoRepository.save(entity);
        return animeContributionEntityMapper.toModel(savedEntity);
    }

    @Override
    public boolean exists(String animeId, UUID personId) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(personId, animeId);
        return animeContributionMongoRepository.existsById(id);
    }

    @Override
    public Optional<AnimeContribution> findById(String animeId, UUID personId) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(personId, animeId);
        return animeContributionMongoRepository.findById(id)
                .map(animeContributionEntityMapper::toModel);
    }

    @Override
    public void delete(AnimeContribution animeContribution) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(
                animeContribution.getPersonId(),
                animeContribution.getAnimeId()
        );
        animeContributionMongoRepository.deleteById(id);
    }
}
