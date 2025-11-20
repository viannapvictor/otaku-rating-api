package com.otakurating.anime.core.command;

public final class DeleteAnimeCommand extends BaseCommand {
    private final String id;

    public DeleteAnimeCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
