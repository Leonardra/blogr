package com.blog.blogr.controller;

import com.blog.blogr.data.model.Comment;
import com.blog.blogr.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    CommentService commentServiceImpl;

    @PostMapping(value ="")
    public ResponseEntity<?> addComment(@RequestBody Comment comment) {
        return new ResponseEntity<>(commentServiceImpl.addComment(comment), HttpStatus.OK);
    }
}
