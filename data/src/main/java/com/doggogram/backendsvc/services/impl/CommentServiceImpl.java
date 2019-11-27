package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.mapper.CommentMapper;
import com.doggogram.backendsvc.repositories.CommentRepository;
import com.doggogram.backendsvc.services.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentServiceImpl (CommentRepository commentRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override public Long count () {
        return commentRepository.countEntities();
    }

    @Override public List<CommentDTO> getAllItems () {
        return commentRepository.findAll().stream().map(commentMapper::commentToCommentDTO).collect(Collectors.toList());
    }

}
