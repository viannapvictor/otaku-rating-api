package com.otakurating.anime.core.strategy;

import org.springframework.data.mongodb.core.query.Criteria;

public final class TitleAnimeSearchStrategy implements AnimeSearchStrategy {
    private final String search;

    public TitleAnimeSearchStrategy(String search) {
        this.search = search;
    }

    @Override
    public Criteria generateNewCriteria(Criteria criteria) {
        return criteria.and("title").regex(search, "i");
    }
}
