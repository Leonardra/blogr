package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.model.Post;
import com.blog.blogr.data.repository.PostRepository;
import com.blog.blogr.dto.AddPostDto;
import com.blog.blogr.dto.SavedPostDto;
import com.blog.blogr.exceptions.PostAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {


    @Mock
    private PostRepository postRepository;


    private ModelMapper modelMapper;

    @InjectMocks
    private PostServiceImpl postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        modelMapper = new ModelMapper();
    }

    @Test
    void thatPostCanBeAdded(){
        AddPostDto  newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"
        );

        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            SavedPostDto savedPost = postService.savePost(newPost);
            System.out.println(savedPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }
        verify(postRepository, times(1)).save(any(Post.class));
    }


    @Test
    void thatPostCannotBeAddedIfTitleExists(){
        AddPostDto newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"
        );
        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            postService.savePost(newPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }

        AddPostDto newPost2 = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatolu"
        );
        Post post2 = modelMapper.map(newPost, Post.class);
        when(postRepository.findByTitle(any())).thenReturn(Optional.of(post2));
        assertThrows(PostAlreadyExistsException.class, () -> postService.savePost(newPost2));
    }



    @Test
    void testThatPostCanBeFoundPostById(){
        //given
        AddPostDto newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"
        );
        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            postService.savePost(newPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }
        //when
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        postService.findPostById(1L);
        verify(postRepository, times(1)).findById(any());
    }
//
    @Test
    void testThatPostCanBeFoundPostByTitle(){
        //given
        AddPostDto newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"
        );

        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            postService.savePost(newPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }
        //when
        when(postRepository.findByTitle(any())).thenReturn(Optional.of(post));
        postService.findPostByTitle("The evolution of humans");
        verify(postRepository, times(2)).findByTitle(any());
    }

    @Test
    void testThatPostCanBeFoundPostByAuthor(){
        //given
        AddPostDto newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"
        );
        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            postService.savePost(newPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }
        //when
        when(postRepository.findByAuthor(any())).thenReturn(List.of(Optional.of(post)));
        postService.findPostByAuthor("Jolayemi Oluwatobi");
        verify(postRepository, times(1)).findByAuthor(any());
    }

    @Test
    void testThatPostCanBeDeleted(){
        AddPostDto newPost = new AddPostDto(
                "The evolution of humans",
                "Lorem Ipsum is simply dummy text of the printing and",
                "Jolayemi Oluwatobi"

        );
        Post post = modelMapper.map(newPost, Post.class);
        when(postRepository.save(any())).thenReturn(post);
        try {
            postService.savePost(newPost);
        } catch (PostAlreadyExistsException e) {
            e.printStackTrace();
        }
        //when
        when(postRepository.findById(any())).thenReturn(Optional.of(post)).thenReturn(null);
        postService.deletePost(1L);
        verify(postRepository, times(1)).delete(any());
    }


}