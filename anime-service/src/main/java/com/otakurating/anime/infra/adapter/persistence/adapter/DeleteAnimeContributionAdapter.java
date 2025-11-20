package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.port.out.DeleteAnimeContributionPort;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeContributionIdentifier;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeContributionMongoRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DeleteAnimeContributionAdapter implements DeleteAnimeContributionPort {
    private final AnimeContributionMongoRepository animeContributionMongoRepository;

    public DeleteAnimeContributionAdapter(AnimeContributionMongoRepository animeContributionMongoRepository) {
        this.animeContributionMongoRepository = animeContributionMongoRepository;
    }

    @Override
    public void deleteByAnimeIdAndPersonId(String animeId, UUID personId) {
        AnimeContributionIdentifier id = new AnimeContributionIdentifier(animeId, personId);
        animeContributionMongoRepository.deleteById(id);
    }

    @Override
    public void deleteByAnimeId(String animeId) {
        animeContributionMongoRepository.deleteByIdAnimeId(animeId);
    }

    @Override
    public void deleteByPersonId(UUID personId) {
        animeContributionMongoRepository.deleteByIdPersonId(personId);
    }
}
