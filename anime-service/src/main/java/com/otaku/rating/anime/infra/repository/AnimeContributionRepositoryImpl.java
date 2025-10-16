package com.otaku.rating.anime.infra.repository;

import com.otaku.rating.anime.core.model.Anime;
import com.otaku.rating.anime.core.model.AnimeContribution;
import com.otaku.rating.anime.core.model.Person;
import com.otaku.rating.anime.core.repository.AnimeContributionRepository;
import com.otaku.rating.anime.infra.persistence.AnimeContributionEntity;
import com.otaku.rating.anime.infra.persistence.AnimeContributionIdentifier;
import com.otaku.rating.generic.core.mapper.Mapper;
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

    @Override
    public void deleteByAnime(Anime anime) {
        animeContributionMongoRepository.deleteByIdAnimeId(anime.getId().getValue());
    }

    @Override
    public void deleteByPerson(Person person) {
        animeContributionMongoRepository.deleteByIdPersonId(person.getId());
    }
}
