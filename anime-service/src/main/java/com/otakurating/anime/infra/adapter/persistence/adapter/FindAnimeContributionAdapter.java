package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.AnimeContribution;
import com.otakurating.anime.core.port.out.FindAnimeContributionPort;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionIdentifier;
import com.otakurating.anime.infra.adapter.persistence.mapper.AnimeContributionInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeContributionMongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class FindAnimeContributionAdapter implements FindAnimeContributionPort {
    private final AnimeContributionMongoRepository animeContributionMongoRepository;
    private final AnimeContributionInfraMapper animeContributionInfraMapper;

    public FindAnimeContributionAdapter(
            AnimeContributionMongoRepository animeContributionMongoRepository,
            AnimeContributionInfraMapper animeContributionInfraMapper
    ) {
        this.animeContributionMongoRepository = animeContributionMongoRepository;
        this.animeContributionInfraMapper = animeContributionInfraMapper;
    }

    @Override
    public Optional<AnimeContribution> findByAnimeIdAndPersonId(UUID animeId, UUID personId) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(animeId, personId);
        return animeContributionMongoRepository.findById(id)
                .map(animeContributionInfraMapper::toModel);
    }

    @Override
    public List<AnimeContribution> findByAnimeId(UUID animeId) {
        return animeContributionMongoRepository.findByIdAnimeId(animeId)
                .stream()
                .map(animeContributionInfraMapper::toModel)
                .toList();
    }

    @Override
    public boolean existsByAnimeIdAndPersonId(UUID animeId, UUID personId) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(animeId, personId);
        return animeContributionMongoRepository.existsById(id);
    }
}
