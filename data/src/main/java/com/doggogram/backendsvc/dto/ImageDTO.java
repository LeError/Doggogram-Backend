package com.doggogram.backendsvc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageDTO {

    private Long id;
    private String image;
    private String user;
    private String userImage;
    private String title;
    private String bio;
    private Long likes;
    private Date created;
    private Long comments;

}
