package com.otaku.rating.api.request.anime.dto;

import com.otaku.rating.api.request.generic.PageRequest;
import lombok.Getter;

@Getter
public class AnimePageRequestDTO extends PageRequest {
    private final String title;
    private final String description;

    public AnimePageRequestDTO(Integer page, String size, String title, String description) {
        super(page, size);
        this.title = title;
        this.description = description;
    }
}
