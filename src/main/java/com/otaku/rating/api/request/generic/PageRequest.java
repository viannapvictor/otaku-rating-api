package com.otaku.rating.api.request.generic;

import lombok.Getter;

@Getter
public class PageRequest {
    private static final int PAGE_DEFAULT = 0;
    private static final int SIZE_DEFAULT = 5;

    private final int page;
    private final int size;

    public PageRequest(Integer page, Integer size) {
        this.page = page != null ? page : PAGE_DEFAULT;
        this.size = size != null ? size : SIZE_DEFAULT;
    }
}
