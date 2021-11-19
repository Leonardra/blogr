package com.blog.blogr.service;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.model.Post;
import com.blog.blogr.data.repository.PostRepository;
import com.blog.blogr.dto.AddPostDto;
import com.blog.blogr.dto.PostUpdateDto;
import com.blog.blogr.dto.SavedPostDto;
import com.blog.blogr.exceptions.PostAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
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
    public SavedPostDto savePost(AddPostDto post) throws PostAlreadyExistsException {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Post> foundPost = postRepository.findByTitle(post.getTitle());
        if(foundPost.isPresent()){
            throw new PostAlreadyExistsException("This title is not unique");
        }
        Post mappedPost = modelMapper.map(post, Post.class);
        Post savedPost = postRepository.save(mappedPost);
        return modelMapper.map(savedPost, SavedPostDto.class);
    }

    @Override
    public SavedPostDto findPostById(Long id) {
        Optional<Post> foundPost = postRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        if (foundPost.isPresent()) {
            Post post = foundPost.get();
            return modelMapper.map(post, SavedPostDto.class);
        }
        throw new IllegalArgumentException("Id does not exist");
    }

    @Override
    public SavedPostDto findPostByTitle(String title) {
        Optional<Post> foundPost = postRepository.findByTitle(title);
        ModelMapper modelMapper = new ModelMapper();
        if (foundPost.isPresent()) {
            Post post = foundPost.get();
            return modelMapper.map(post, SavedPostDto.class);
        }
        throw new IllegalArgumentException("Title does not exist");
    }

    @Override
    public List<SavedPostDto> findPostByAuthor(String author) {
        List<Optional<Post>> optionalPosts = postRepository.findByAuthor(author);

        ModelMapper modelMapper = new ModelMapper();
        List<Post> posts =  optionalPosts.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        if(posts.isEmpty()) {
            throw new IllegalArgumentException("Posts by author not found");
        }
        return posts.stream()
                .map(e -> modelMapper.map(e, SavedPostDto.class))
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
    public SavedPostDto updatePost(Long id, PostUpdateDto postUpdate) {
        if(postUpdate == null){
            throw new NullPointerException("Post cannot be null");
        }
        Optional<Post> foundProduct = postRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        if(foundProduct.isPresent()){
            Post existingPost = foundProduct.get();
            postMapper.mapToPostMapper(postUpdate, existingPost);
            Post post = postRepository.save(existingPost);
            return modelMapper.map(post, SavedPostDto.class);
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
