package com.otakurating.anime.core.utils;

import com.otakurating.anime.core.exception.PageNumberInvalidException;
import com.otakurating.anime.core.exception.PageSizeInvalidException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public final class PageUtils {
    public static Pageable createPageable(int page, int size, int max_size) {
        if (page < 0) {
            throw new PageNumberInvalidException();
        }
        if (size < 1 || size > max_size) {
            throw new PageSizeInvalidException(max_size);
        }
        return PageRequest.of(page, size);
    }
}
