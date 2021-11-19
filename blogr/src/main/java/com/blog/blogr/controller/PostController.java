package com.blog.blogr.controller;


import com.blog.blogr.data.model.Comment;
import com.blog.blogr.data.model.Post;
import com.blog.blogr.dto.AddPostDto;
import com.blog.blogr.dto.PostUpdateDto;
import com.blog.blogr.exceptions.PostAlreadyExistsException;
import com.blog.blogr.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    @Autowired
    private PostService postService;


    @PostMapping("")
    public ResponseEntity<?> savePost(@RequestBody AddPostDto post) {
        try {
            return new ResponseEntity<>(postService.savePost(post), HttpStatus.CREATED);
        } catch (PostAlreadyExistsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findPostById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(postService.findPostById(id), HttpStatus.FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findPostByTitle/{title}")
    public ResponseEntity<?> findPostByTitle(@PathVariable String title) {
        try {
            return new ResponseEntity<>(postService.findPostByTitle(title), HttpStatus.FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> findAllPost(){
        return new ResponseEntity<>(postService.findAll(), HttpStatus.FOUND);
    }
    @GetMapping("/findCourseByAuthor/{author}")
    public ResponseEntity<?> findPostByAuthor(@PathVariable String author) {
        try {
            return new ResponseEntity<>(postService.findPostByAuthor(author), HttpStatus.FOUND);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id){
        try {
            postService.deletePost(id);
            return new ResponseEntity<>( HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value ="/{id}")
    public ResponseEntity<?> updatePost(@PathVariable Long id, @RequestBody PostUpdateDto post) {
        try {
            return new ResponseEntity<>(postService.updatePost(id, post), HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping(value = "/comment/{id}")
    public ResponseEntity<?> addComment(@PathVariable Long id, @RequestBody Comment comment) {
        postService.createComment(id, comment);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
