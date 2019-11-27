package com.doggogram.backendsvc.services.impl;

import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.repositories.CommentRepository;
import com.doggogram.backendsvc.services.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl (CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override public Long count () {
        return commentRepository.countEntities();
    }

    @Override public List<CommentDTO> getAllItems () {
        return null;
    }

}
