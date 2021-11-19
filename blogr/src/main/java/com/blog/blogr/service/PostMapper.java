package com.blog.blogr.service;

import com.blog.blogr.data.model.Post;
import com.blog.blogr.dto.PostUpdateDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;


@Mapper(componentModel="spring")
public interface PostMapper {
    @BeanMapping(nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
    void mapToPostMapper(PostUpdateDto postUpdateDto,
                         @MappingTarget Post post
                         );
}
