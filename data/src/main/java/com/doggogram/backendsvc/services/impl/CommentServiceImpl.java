package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.domain.Comment;
import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.mapper.CommentMapper;
import com.doggogram.backendsvc.repositories.CommentRepository;
import com.doggogram.backendsvc.repositories.ImageRepository;
import com.doggogram.backendsvc.repositories.UserRepository;
import com.doggogram.backendsvc.services.CommentService;
import com.doggogram.backendsvc.util.exceptions.CommentOwnershipException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final ImageRepository imageRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl (ImageRepository imageRepository, UserRepository userRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override public Long count () {
        return commentRepository.countEntities();
    }

    @Override public List<CommentDTO> getAllItems () {
        return commentRepository.findAll().stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
    }

    @Override
    public void addComment (String user, String content, long imageId) {
        Comment comment = new Comment();
        comment.setComment(content);
        comment.setAuthor(userRepository.findUserByUser(user));
        Image image = imageRepository.findById(imageId);
        image.getComments().add(comment);
        imageRepository.save(image);
        commentRepository.save(comment);
    }

    @Override
    public void updateComment (String user, String content, long commentId) throws CommentOwnershipException {
        if(commentRepository.checkCommentOwnership(user, commentId) > 0) {
            Comment comment = commentRepository.findCommentById(commentId);
            comment.setComment(content);
            commentRepository.save(comment);
        } else {
            throw new CommentOwnershipException("Not Author of Comment!");
        }
    }

    @Override
    public void removeComment (String user, long commentId) throws CommentOwnershipException {
        if(commentRepository.checkCommentOwnership(user, commentId) > 0) {
            Image image = imageRepository.findImageByCommentId(commentId);
            Comment comment = commentRepository.findCommentById(commentId);
            image.getComments().remove(comment);
            imageRepository.save(image);
            commentRepository.deleteCommentById(commentId);
        } else {
            throw new CommentOwnershipException("Not Author of Comment!");
        }
    }

    @Override
    public CommentDTO getComment (long commentId) {
        return commentMapper.commentToCommentDTO(commentRepository.findCommentById(commentId));
    }

    @Override
    public List<CommentDTO> getCommentsOfImage (long imageId) {
        return commentRepository.findCommentsByImageId(imageId).stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
    }
}
