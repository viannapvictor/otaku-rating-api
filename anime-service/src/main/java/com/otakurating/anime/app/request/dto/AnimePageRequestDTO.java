package com.otakurating.anime.app.request.dto;

public final class AnimePageRequestDTO extends PageRequest {
    private final String title;
    private final String description;

    public AnimePageRequestDTO(Integer page, String size, String title, String description) {
        super(page, size);
        this.title = title;
        this.description = description;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }
}
