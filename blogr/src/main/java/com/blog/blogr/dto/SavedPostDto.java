package com.blog.blogr.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedPostDto {
    private Long id;
    private String title;
    private String postBody;
    private String author;
    private String timeCreated;
    private String timeUpdated;
}
