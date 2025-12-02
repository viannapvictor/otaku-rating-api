package com.otakurating.rating.core.model;

import com.otakurating.rating.core.exception.CommentInvalidLengthException;

public final class Comment {
    public static final int MAX_LENGTH = 512;

    private final String value;

    private Comment(String value) {
        if (value == null) {
            this.value = "";
            return;
        }
        if (value.length() > MAX_LENGTH) {
            throw new CommentInvalidLengthException();
        }
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Comment valueOf(String value) {
        return new Comment(value);
    }
}
