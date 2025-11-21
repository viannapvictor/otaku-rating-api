package com.otakurating.anime.core.command;

public final class CreatePersonCommand extends BaseCommand {
    private final String name;
    private final String description;

    public CreatePersonCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
