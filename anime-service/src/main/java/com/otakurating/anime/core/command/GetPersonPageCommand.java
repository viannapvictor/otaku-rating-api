package com.otakurating.anime.core.command;

public final class GetPersonPageCommand extends BaseCommand {
    private final int page;
    private final int size;

    public GetPersonPageCommand(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
