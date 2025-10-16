package com.otaku.rating.core.anime.strategy;

import org.springframework.data.mongodb.core.query.Criteria;

public interface AnimeSearchStrategy {
    Criteria generateNewCriteria(Criteria criteria);
}
