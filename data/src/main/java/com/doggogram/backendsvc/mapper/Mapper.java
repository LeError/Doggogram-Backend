package com.doggogram.backendsvc.mapper;

import com.doggogram.backendsvc.domain.Comment;
import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.UserDTO;

public interface Mapper {

    UserDTO userToUserDTO(User user);
    ImageDTO imageToImageDTO(Image image);
    CommentDTO commentToCommentDTO(Comment comment);

}
