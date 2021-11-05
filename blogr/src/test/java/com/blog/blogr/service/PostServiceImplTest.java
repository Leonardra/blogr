package com.blog.blogr.service;

import com.blog.blogr.data.model.Post;
import com.blog.blogr.data.repository.PostRepository;
import com.blog.blogr.dto.PostCreationDto;
import com.blog.blogr.dto.PostUpdateDto;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.PostUpdate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {


    @Mock
    private PostRepository postRepository;
    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void thatPostCanBeAdded(){
        Post newPost = new Post();
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        when(postRepository.save(any())).thenReturn(newPost);
        postService.savePost(newPost);
        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void testThatPostCanBeFoundPostById(){
        //given
        Post newPost = new Post();
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        postService.savePost(newPost);
        //when
        when(postRepository.findById(any())).thenReturn(Optional.of(newPost));
        postService.findPostById(1L);
        verify(postRepository, times(1)).findById(any());
    }

    @Test
    void testThatPostCanBeFoundPostByTitle(){
        //given
        Post newPost = new Post();
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        postService.savePost(newPost);
        //when
        when(postRepository.findByTitle(any())).thenReturn(Optional.of(newPost));
        postService.findPostByTitle("The evolution of humans");
        verify(postRepository, times(1)).findByTitle(any());
    }

    @Test
    void testThatPostCanBeFoundPostByAuthor(){
        //given
        Post newPost = new Post();
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        postService.savePost(newPost);
        //when
        when(postRepository.findByAuthor(any())).thenReturn(List.of(Optional.of(newPost)));
        postService.findPostByAuthor("Jolayemi Oluwatobi");
        verify(postRepository, times(1)).findByAuthor(any());
    }

    @Test
    void testThatPostCanBeDeleted(){
        Post newPost = new Post();
        newPost.setId(1L);
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        when(postRepository.save(any())).thenReturn(newPost);
        postService.savePost(newPost);
        //when
        when(postRepository.findById(any())).thenReturn(Optional.of(newPost)).thenReturn(null);
        postService.deletePost(1L);
        verify(postRepository, times(1)).delete(any());
    }

    @Test
    void testThatPostCanBeUpdated(){
        Post newPost = new Post();
        newPost.setId(1L);
        newPost.setTitle("The evolution of humans");
        newPost.setPostBody( "Lorem Ipsum is simply dummy text of the printing and");
        newPost.setAuthor("Jolayemi Oluwatobi");
        when(postRepository.save(any())).thenReturn(newPost);
        postService.savePost(newPost);

        PostUpdateDto postUpdate = new PostUpdateDto();
        postUpdate.setPostBody("This is the end" );
        postUpdate.setAuthor("Oluwatolu Jolayemi");
        when(postRepository.findById(1L)).thenReturn(Optional.of(newPost));
        when(postRepository.save(any())).thenReturn(newPost);
        postService.updatePost(1L, postUpdate);
        System.out.println(newPost);
        verify(postRepository, times(2)).findById(any());
    }
}