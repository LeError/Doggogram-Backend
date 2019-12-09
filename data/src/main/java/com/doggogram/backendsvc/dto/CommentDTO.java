package com.doggogram.backendsvc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDTO {

    private Long id;
    private String user;
    private String comment;
    private Date created;

}
