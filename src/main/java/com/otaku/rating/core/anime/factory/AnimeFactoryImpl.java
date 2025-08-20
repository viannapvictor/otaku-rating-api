package com.otaku.rating.core.anime.factory;

import com.otaku.rating.core.anime.model.*;
import com.otaku.rating.core.anime.model.AnimeContribution;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AnimeFactoryImpl implements AnimeFactory {
    private final AnimeContributionFactory animeContributionFactory;

    @Override
    public Anime create(AnimeParams params) {
        AnimeTitle title = AnimeTitle.valueOf(params.getTitle());
        AnimeIdentifier id = AnimeIdentifier.valueOf(title);
        AnimeDescription description = AnimeDescription.valueOf(params.getDescription());
        AnimeRelease release = AnimeRelease.valueOf(params.getRelease());
        List<AnimeContribution> contributions = params.getContributions()
                .stream()
                .map(animeContributionFactory::create)
                .toList();
        return new Anime(id, title, description, release, contributions);
    }
}
