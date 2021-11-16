package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.repository.CommentRepository;
import com.blog.blogr.exceptions.CommentNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;
    @InjectMocks
    private CommentServiceImpl commentService;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addComment() {
        Comment newComment = new Comment();
        newComment.setOwnerName("Iseoluwa Fasoyin");
        newComment.setBody("I hate you");
        when(commentRepository.save(any())).thenReturn(newComment);
        commentService.addComment(newComment);
        verify(commentRepository, times(1)).save(any());
    }

    @Test
    void findCommentById() {
        //when
        Comment newComment = new Comment();
        newComment.setCommentId(1L);
        newComment.setOwnerName("Iseoluwa Fasoyin");
        newComment.setBody("I want to eat");
        when(commentRepository.save(any())).thenReturn(newComment);
        //given
        when(commentRepository.findById(any())).thenReturn(Optional.of(newComment));
        try {
            commentService.findCommentById(1L);
        } catch (CommentNotFoundException e) {
            e.printStackTrace();
        }
        //assert that
        verify(commentRepository, times(1)).findById(any());
    }

    @Test
    void deleteComment() {
        Comment newComment = new Comment();
        newComment.setCommentId(1L);
        newComment.setOwnerName("Iseoluwa Fasoyin");
        newComment.setBody("I want to eat");
        when(commentRepository.save(any())).thenReturn(newComment);
        commentService.addComment(newComment);

        when(commentRepository.findById(any())).thenReturn(Optional.of(newComment)).thenReturn(null);
        commentService.deleteComment(1L);
        verify(commentRepository, times(1)).delete(any());
    }
}