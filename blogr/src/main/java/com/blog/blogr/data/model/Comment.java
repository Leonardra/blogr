package com.blog.blogr.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    private Long id;
    private String ownerName;
    private String body;
    private LocalDate dateCreated;
    private LocalDate dateUpdated;
}
