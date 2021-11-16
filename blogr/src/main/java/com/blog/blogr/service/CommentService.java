package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.exceptions.CommentNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface CommentService {
    Comment addComment(Comment comment);
    Comment findCommentById(Long id) throws CommentNotFoundException;
    void deleteComment(Long id);
}
