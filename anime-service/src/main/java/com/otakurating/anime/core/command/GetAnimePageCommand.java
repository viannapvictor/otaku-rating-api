package com.otakurating.anime.core.command;

public final class GetAnimePageCommand extends BaseCommand {
    private final int page;
    private final int size;
    private final String title;
    private final String description;

    public GetAnimePageCommand(int page, int size, String title, String description) {
        this.page = page;
        this.size = size;
        this.title = title;
        this.description = description;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
