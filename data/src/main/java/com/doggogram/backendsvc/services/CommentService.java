package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.CommentDTO;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CommentService extends RestService<List<CommentDTO>> {



}
