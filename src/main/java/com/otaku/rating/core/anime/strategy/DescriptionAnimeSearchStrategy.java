package com.otaku.rating.core.anime.strategy;

import org.springframework.data.mongodb.core.query.Criteria;

public final class DescriptionAnimeSearchStrategy implements AnimeSearchStrategy {
    private final String search;

    public DescriptionAnimeSearchStrategy(String search) {
        this.search = search;
    }

    @Override
    public Criteria generateNewCriteria(Criteria criteria) {
        return criteria.and("description").regex(search, "i");
    }
}
