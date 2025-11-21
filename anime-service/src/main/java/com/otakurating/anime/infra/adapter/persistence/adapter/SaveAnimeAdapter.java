package com.otakurating.anime.infra.adapter.persistence.adapter;

import com.otakurating.anime.core.model.Anime;
import com.otakurating.anime.core.port.out.SavePort;
import com.otakurating.anime.infra.adapter.persistence.entity.AnimeEntity;
import com.otakurating.anime.infra.adapter.persistence.mapper.AnimeInfraMapper;
import com.otakurating.anime.infra.adapter.persistence.repository.AnimeMongoRepository;
import org.springframework.stereotype.Component;

@Component
public class SaveAnimeAdapter implements SavePort<Anime> {
    private final AnimeMongoRepository animeMongoRepository;
    private final AnimeInfraMapper animeInfraMapper;

    public SaveAnimeAdapter(AnimeMongoRepository animeMongoRepository, AnimeInfraMapper animeInfraMapper) {
        this.animeMongoRepository = animeMongoRepository;
        this.animeInfraMapper = animeInfraMapper;
    }

    @Override
    public Anime save(Anime item) {
        AnimeEntity entity = animeInfraMapper.toEntity(item);
        AnimeEntity savedEntity = animeMongoRepository.save(entity);

        return animeInfraMapper.toModel(savedEntity);
    }
}
