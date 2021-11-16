package com.blog.blogr.exceptions;

public class CommentNotFoundException extends Throwable {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
