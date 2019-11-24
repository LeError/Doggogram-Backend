package com.doggogram.backendsvc.dto;

import com.doggogram.backendsvc.domain.User;
import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;
    private User user;
    private String comment;
    private Date created;

}
