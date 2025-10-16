package com.otaku.rating.infra.anime.repository;

import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.repository.AnimeRepository;
import com.otaku.rating.core.anime.strategy.AnimeSearchStrategy;
import com.otaku.rating.core.generic.mapper.Mapper;
import com.otaku.rating.infra.anime.persistence.AnimeEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AnimeRepositoryImpl implements AnimeRepository {
    private final AnimeMongoRepository animeMongoRepository;
    private final Mapper<Anime, AnimeEntity> animeEntityMapper;
    private final MongoTemplate mongoTemplate;

    @Override
    public Anime save(Anime anime) {
        AnimeEntity entity = animeEntityMapper.toEntity(anime);
        AnimeEntity savedEntity = animeMongoRepository.save(entity);
        return animeEntityMapper.toModel(savedEntity);
    }

    @Override
    public boolean exists(String id) {
        return animeMongoRepository.existsById(id);
    }

    @Override
    public Optional<Anime> findById(String id) {
        return animeMongoRepository.findById(id)
                .map(animeEntityMapper::toModel);
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
                .map(animeEntityMapper::toModel)
                .toList();
        return new PageImpl<>(animes, pageable, total);
    }

    @Override
    public void delete(Anime anime) {
        animeMongoRepository.deleteById(anime.getId().getValue());
    }
}
