package com.blog.blogr.service;

import com.blog.blogr.data.model.Post;
import com.blog.blogr.dto.PostCreationDto;
import com.blog.blogr.dto.PostUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    Post savePost(Post post);
    Post findPostById(Long id);
    Post findPostByTitle(String title);
    List<Post> findPostByAuthor(String author);
    void deletePost(Long id);
    Post updatePost(Long id, PostUpdateDto postUpdate);
    
}
