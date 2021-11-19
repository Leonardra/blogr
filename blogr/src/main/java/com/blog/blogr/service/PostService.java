package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.model.Post;
import com.blog.blogr.dto.AddPostDto;
import com.blog.blogr.dto.PostUpdateDto;
import com.blog.blogr.dto.SavedPostDto;
import com.blog.blogr.exceptions.PostAlreadyExistsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    SavedPostDto savePost(AddPostDto post) throws PostAlreadyExistsException;
    SavedPostDto findPostById(Long id);
    SavedPostDto findPostByTitle(String title);
    List<SavedPostDto> findPostByAuthor(String author);
    void deletePost(Long id);
    SavedPostDto updatePost(Long id, PostUpdateDto postUpdate);
    List<Post> findAll();
    void createComment(Long id, Comment comment);
}
