package com.blog.blogr.dto;


import com.blog.blogr.data.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedPostDto {
    private Long id;
    private String title;
    private String postBody;
    private String author;
    private List<Comment> comments;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
}
