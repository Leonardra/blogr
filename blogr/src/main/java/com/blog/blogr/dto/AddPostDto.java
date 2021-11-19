package com.blog.blogr.dto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPostDto {
    private String title;
    private String postBody;
    private String author;
}
