package com.otakurating.rating.core.command;

public final class GetRatingByIdCommand extends BaseCommand {
    private final String id;

    public GetRatingByIdCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
