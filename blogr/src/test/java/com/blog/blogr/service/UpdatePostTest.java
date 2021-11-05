package com.blog.blogr.service;


import com.blog.blogr.data.model.Post;
import com.blog.blogr.data.repository.PostRepository;
import com.blog.blogr.dto.PostUpdateDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
@Sql("/db/insert.sql")
public class UpdatePostTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    @Test
    void thatPostCanBeUpdated(){
        Post post = postRepository.findById(111L).orElse(null);
        assertThat(post).isNotNull();
        assertThat(post.getAuthor()).isEqualTo("Oluwatobi Jolayemi");

        PostUpdateDto postUpdate = new PostUpdateDto();
        postUpdate.setPostBody("This is the end" );
        postUpdate.setAuthor("Oluwatolu Jolayemi");

        post = postService.updatePost(111L, postUpdate);
        assertThat(post.getAuthor()).isEqualTo("Oluwatolu Jolayemi");
    }
}
