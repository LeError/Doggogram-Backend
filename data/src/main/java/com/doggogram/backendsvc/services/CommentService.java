package com.doggogram.backendsvc.services;

import com.doggogram.backendsvc.dto.CommentDTO;
import com.doggogram.backendsvc.util.exceptions.CommentOwnershipException;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface CommentService extends RestService<List<CommentDTO>> {

    void addComment(String user, String content, long imageId);
    void updateComment(String user, String content, long commentId) throws CommentOwnershipException;
    void removeComment(String user, long commentId) throws CommentOwnershipException;
    CommentDTO getComment(long commentId);
    List<CommentDTO> getCommentsOfImage (long imageId);
    Long countCommentsOnImage(long imageId);

}
