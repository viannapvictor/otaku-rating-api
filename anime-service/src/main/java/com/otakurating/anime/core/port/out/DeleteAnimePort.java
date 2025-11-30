package com.otakurating.anime.core.port.out;

import java.util.UUID;

public interface DeleteAnimePort {
    void deleteById(UUID id);
}
