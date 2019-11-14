package com.doggogram.backendsvc.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity (name = "IMAGE")
public class Image {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "IMAGE_ID")
    private Long id;

    @Column (name = "IMAGE_FILE")
    private String filename;

    @Column (name = "IMAGE_TITLE")
    private String title;

    @Column (name = "IMAGE_BIO")
    private String bio;

    @Column (name = "IMAGE_LIKES")
    private Long likes;

    @Column (name = "IMAGE_CREATED")
    private Date created;

}
