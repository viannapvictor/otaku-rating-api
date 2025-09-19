package com.otaku.rating.core.generic.utils;

import com.otaku.rating.core.generic.exception.utils.PageNumberInvalidException;
import com.otaku.rating.core.generic.exception.utils.PageSizeInvalidException;
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
