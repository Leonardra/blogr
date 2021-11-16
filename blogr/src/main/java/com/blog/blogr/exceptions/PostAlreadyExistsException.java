package com.blog.blogr.exceptions;

public class PostAlreadyExistsException extends Exception {
    public PostAlreadyExistsException(String message) {
        super(message);
    }
}
