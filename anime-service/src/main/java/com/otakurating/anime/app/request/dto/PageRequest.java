package com.otakurating.anime.app.request.dto;

public class PageRequest {
    private static final int PAGE_DEFAULT = 1;
    private static final int SIZE_DEFAULT = 5;

    private final int page;
    private final int size;

    public PageRequest(Integer page, String size) {
        int userPage = page != null ? page : PAGE_DEFAULT;
        this.page = Math.max(0, userPage - 1);
        this.size = size != null ? Integer.parseInt(size) : SIZE_DEFAULT;
    }

    public int page() {
        return page;
    }

    public int size() {
        return size;
    }
}
