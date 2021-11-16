package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.model.Post;
import com.blog.blogr.data.repository.PostRepository;
import com.blog.blogr.dto.PostUpdateDto;
import com.blog.blogr.exceptions.PostAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostMapper postMapper;

    @Override
    public Post savePost(Post post) throws PostAlreadyExistsException {
        Optional<Post> foundPost = postRepository.findByTitle(post.getTitle());
        if(foundPost.isPresent()){
            throw new PostAlreadyExistsException("This title is not unique");
        }
        return postRepository.save(post);
    }

    @Override
    public Post findPostById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    @Override
    public Post findPostByTitle(String title) {
        Optional<Post> foundPost = postRepository.findByTitle(title);
        if (foundPost.isPresent()) {
            return foundPost.get();
        }
        throw new IllegalArgumentException("Title does not exist");
    }

    @Override
    public List<Post> findPostByAuthor(String author) {
        List<Optional<Post>> optionalPosts = postRepository.findByAuthor(author);
        return optionalPosts.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public void deletePost(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        if (foundPost.isEmpty()) {
            throw new IllegalArgumentException("Id cannot be found");
        }
            postRepository.delete(foundPost.get());
    }

    @Override
    public Post updatePost(Long id, PostUpdateDto postUpdate) {
        if(postUpdate == null){
            throw new NullPointerException("Post cannot be null");
        }
        Optional<Post> foundProduct = postRepository.findById(id);

        if(foundProduct.isPresent()){
            Post existingPost = foundProduct.get();
            postMapper.mapToPostMapper(postUpdate, existingPost);
            return postRepository.save(existingPost);
        }else {
            throw new IllegalArgumentException("Post does not exist");
        }
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public void createComment(Long id, Comment comment) {
        Optional<Post> foundPost= postRepository.findById(id);

        if(foundPost.isPresent()){
            Post post = foundPost.get();
            post.getComments().add(comment);
            postRepository.save(post);
        }
    }
}
