package com.doggogram.backendsvc.dto;

import com.doggogram.backendsvc.domain.Image;
import com.doggogram.backendsvc.domain.User;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;
    private User user;
    private Image image;
    private String comment;

}
