package com.doggogram.backendsvc.mapper;

import com.doggogram.backendsvc.domain.Comment;
import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.dto.ImageDTO;
import com.doggogram.backendsvc.dto.UserDTO;
import com.doggogram.backendsvc.repositories.CommentRepository;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class MapperImpl implements Mapper {

    private final UserRepository userRepository;
    private final ImageRepository imageRepository;
    private final CommentRepository commentRepository;

    public MapperImpl (UserRepository userRepository, ImageRepository imageRepository, CommentRepository commentRepository) {
        this.userRepository = userRepository;
        this.imageRepository = imageRepository;
        this.commentRepository = commentRepository;
    }

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUser(user.getUser());
        userDTO.setBio(user.getBio());
        userDTO.setUserImage(user.getUserImage());
        userDTO.setImages(imageRepository.countUserImages(user.getUser()));
        userDTO.setFollowing(userRepository.countFollowing(user.getUser()));
        userDTO.setFollowers(userRepository.countFollowers(user.getUser()));
        return userDTO;
    }

    public ImageDTO imageToImageDTO(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setImage(image.getImage());
        imageDTO.setUser(userRepository.findOwnerUserByImageId(image.getId()));
        imageDTO.setUserImage(userRepository.findOwnerUserImageByImageId(image.getId()));
        imageDTO.setTitle(image.getTitle());
        imageDTO.setBio(image.getBio());
        imageDTO.setLikes(imageRepository.countImageLikes(image.getId()));
        imageDTO.setCreated(image.getCreated());
        imageDTO.setComments(commentRepository.countCommentsOnImage(image.getId()));
        return imageDTO;
    }

    public CommentDTO commentToCommentDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setUser(comment.getAuthor().getUser());
        commentDTO.setComment(comment.getComment());
        commentDTO.setCreated(comment.getCreated());
        return commentDTO;
    }

}
