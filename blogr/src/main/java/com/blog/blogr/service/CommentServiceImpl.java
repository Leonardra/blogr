package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.repository.CommentRepository;
import com.blog.blogr.exceptions.CommentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentRepository commentRepository;

    @Override
    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Comment findCommentById(Long id) throws CommentNotFoundException {
        Optional<Comment> foundComment = commentRepository.findById(id);
        if(foundComment.isEmpty()){
            throw new CommentNotFoundException("Comment is not found");
        }
        return foundComment.get();
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> foundComment = commentRepository.findById(id);
        foundComment.ifPresent(comment -> commentRepository.delete(comment));
    }
}
