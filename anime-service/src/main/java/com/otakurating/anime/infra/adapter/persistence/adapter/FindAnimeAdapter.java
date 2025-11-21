package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.out.FindAnimePort;
import com.otakurating.anime.core.strategy.AnimeSearchStrategy;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeEntity;
import com.otakurating.anime.infra.adapter.persistence.mapper.AnimeInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeMongoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FindAnimeAdapter implements FindAnimePort {
    private final AnimeMongoRepository animeMongoRepository;
    private final AnimeInfraMapper animeInfraMapper;
    private final MongoTemplate mongoTemplate;

    public FindAnimeAdapter(AnimeMongoRepository animeMongoRepository, AnimeInfraMapper animeInfraMapper, MongoTemplate mongoTemplate) {
        this.animeMongoRepository = animeMongoRepository;
        this.animeInfraMapper = animeInfraMapper;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Optional<Anime> findById(String id) {
        return animeMongoRepository.findById(id)
                .map(animeInfraMapper::toModel);
    }

    @Override
    public boolean existsById(String id) {
        return animeMongoRepository.existsById(id);
    }

    @Override
    public Page<Anime> findAnimePage(Pageable pageable, List<AnimeSearchStrategy> strategies) {
        Criteria criteria = new Criteria();
        for (AnimeSearchStrategy strategy : strategies) {
            criteria = strategy.generateNewCriteria(criteria);
        }
        Query query = new Query(criteria).with(pageable);
        List<AnimeEntity> entities = mongoTemplate.find(query, AnimeEntity.class);

        long total = mongoTemplate.count(new Query(criteria), AnimeEntity.class);
        List<Anime> animes = entities.stream()
                .map(animeInfraMapper::toModel)
                .toList();
        return new PageImpl<>(animes, pageable, total);
    }
}
