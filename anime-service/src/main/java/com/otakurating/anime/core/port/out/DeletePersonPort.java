package com.otakurating.anime.core.port.out;

import java.util.UUID;

public interface DeletePersonPort {
    void deleteById(UUID id);
}
