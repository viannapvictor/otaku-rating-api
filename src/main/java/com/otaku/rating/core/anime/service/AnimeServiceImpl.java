package com.otaku.rating.core.anime.service;

import com.otaku.rating.core.anime.exception.AnimeAlreadyExistsException;
import com.otaku.rating.core.anime.exception.AnimeNotFoundException;
import com.otaku.rating.core.anime.model.Anime;
import com.otaku.rating.core.anime.repository.AnimeRepository;
import com.otaku.rating.core.generic.exception.ForbiddenException;
import com.otaku.rating.core.generic.utils.PageUtils;
import com.otaku.rating.core.user.model.User;
import com.otaku.rating.core.user.model.valueobject.UserAuthorizationLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnimeServiceImpl implements AnimeService {
    public static final int MAX_PAGE_SIZE = 20;

    private final AnimeRepository animeRepository;

    @Override
    public Page<Anime> getPage(int page, int size) {
        Pageable pageable = PageUtils.createPageable(page, size, MAX_PAGE_SIZE);
        return animeRepository.findAnimePage(pageable);
    }

    @Override
    public Anime add(User authenticatedUser, Anime anime) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(anime);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        if (animeRepository.exists(anime.getId().getValue())) {
            throw new AnimeAlreadyExistsException();
        }
        return animeRepository.save(anime);
    }

    @Override
    @Transactional
    public Anime update(User authenticatedUser, String oldId, Anime anime) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(anime);

        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        Anime oldAnime = getById(oldId);
        if (Objects.equals(oldId, anime.getId().getValue())) {
            return animeRepository.save(anime);
        }
        animeRepository.delete(oldAnime);
        return animeRepository.save(anime);
    }

    @Override
    public void delete(User authenticatedUser, Anime anime) {
        Objects.requireNonNull(authenticatedUser);
        Objects.requireNonNull(anime);
        if (!authenticatedUser.hasAuthorization(UserAuthorizationLevel.MODIFICATION)) {
            throw new ForbiddenException();
        }
        animeRepository.delete(anime);
    }

    @Override
    public Anime getById(String id) {
        return animeRepository.findById(id).orElseThrow(AnimeNotFoundException::new);
    }
}
