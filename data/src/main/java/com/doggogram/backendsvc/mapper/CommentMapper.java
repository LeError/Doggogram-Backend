package com.doggogram.backendsvc.mapper;

import com.doggogram.backendsvc.domain.Comment;
import com.doggogram.backendsvc.dto.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentDTO commentToCommentDTO (Comment comment);

}
