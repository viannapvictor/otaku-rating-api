package com.otakurating.rating.app.response.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponse<T>(
        List<T> content,
        int size,
        int page,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
    public static <T, E> PageResponse<E> from(Page<T> page, List<E> content) {
        return new PageResponse<>(
                content,
                page.getSize(),
                page.getNumber() + 1,
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
