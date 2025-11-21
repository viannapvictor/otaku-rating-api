package com.otakurating.anime.core.command;

import java.util.UUID;

public final class UpdatePersonCommand extends BaseCommand {
    private final UUID currentId;
    private final String name;
    private final String description;

    public UpdatePersonCommand(UUID currentId, String name, String description) {
        this.currentId = currentId;
        this.name = name;
        this.description = description;
    }

    public UUID getCurrentId() {
        return currentId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
