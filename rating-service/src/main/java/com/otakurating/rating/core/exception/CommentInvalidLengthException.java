package com.otakurating.rating.core.exception;

import com.otakurating.rating.core.model.Comment;

public final class CommentInvalidLengthException extends CoreException {
    private static final String MESSAGE = String.format(
            "The title size must be less than or equal to %d.",
            Comment.MAX_LENGTH
    );

    public CommentInvalidLengthException() {
        super("comment.invalid.length", MESSAGE);
    }
}
