package com.doggogram.backendsvc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ImageDTO {

    private Long id;
    private String filename;
    private String title;
    private String bio;
    private Long likes;
    private Date created;
    private String imageEncoded;

}
