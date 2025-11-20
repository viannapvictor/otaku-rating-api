package com.otakurating.anime.core.command;

public final class GetAnimeCommand extends BaseCommand {
    private final String id;

    public GetAnimeCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
