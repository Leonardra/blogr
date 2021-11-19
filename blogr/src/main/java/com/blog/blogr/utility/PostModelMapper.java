package com.blog.blogr.utility;

import com.blog.blogr.data.model.Post;
import com.blog.blogr.dto.SavedPostDto;
import org.springframework.stereotype.Component;


@Component
public class PostModelMapper {


    public SavedPostDto mapToPost(Post post){
        if ( post == null ) {
            return null;
        }

        SavedPostDto postDto = new SavedPostDto();

        if ( post.getPostId() != null ) {
            postDto.setId(post.getPostId());
        }

        if ( post.getTitle() != null ) {
            postDto.setTitle( post.getTitle() );
        }
        if ( post.getPostBody() != null ) {
            postDto.setPostBody( post.getPostBody() );
        }
        if ( post.getAuthor() != null ) {
            postDto.setAuthor( post.getAuthor() );
        }
        return postDto;
    }
}
