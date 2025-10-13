package com.otakurating.anime.app.response.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content;
    private int size;
    private int page;
    private long totalElements;
    private int totalPages;
    private boolean first;
    private boolean last;

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
